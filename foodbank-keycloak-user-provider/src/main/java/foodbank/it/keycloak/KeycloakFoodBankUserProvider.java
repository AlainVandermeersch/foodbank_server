package foodbank.it.keycloak;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.models.*;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;
import org.keycloak.storage.user.UserQueryProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KeycloakFoodBankUserProvider implements UserStorageProvider, UserLookupProvider, CredentialInputValidator, UserQueryProvider {

	private static final Logger LOG = LoggerFactory.getLogger(KeycloakFoodBankUserProvider.class);

	public static final String OFFSET_LIMIT = " OFFSET ? LIMIT ?";
	public static final String USER_COUNT = "select count(*) from t_user";
	public static final String PASSWORD_BY_USERNAME = "select password from t_user where id_user = ?";
	public static final String USERS = "select id_user, user_name, email, rights from t_user";
	public static final String USERS_LOOKUP = USERS + " where id_user like ? or user_name like ? or email like ?";
	public static final String USERS_LOOKUP_PAGE = USERS_LOOKUP + OFFSET_LIMIT;
	public static final String USERS_BY_RIGHTS = USERS + " where rights like ?";
	public static final String USERS_BY_RIGHTS_PAGE = USERS_BY_RIGHTS + OFFSET_LIMIT;
	public static final String USER_BY_ID = USERS + " where id_user = ?";
	public static final String USER_BY_USERNAME = USERS + " where id_user = ?";
	public static final String USER_BY_EMAIL = USERS + " where email = ?";
	public static final String USERS_PAGE = USERS + OFFSET_LIMIT;
	public static final int COST = 10;

	private final KeycloakSession ksession;
	private final ComponentModel model;
	private final BCrypt.Hasher bcryptHasher;

	public KeycloakFoodBankUserProvider(KeycloakSession ksession, ComponentModel model, BCrypt.Hasher bcryptHasher) {
		this.ksession = ksession;
		this.model = model;
		this.bcryptHasher = bcryptHasher;
	}

	@Override
	public boolean supportsCredentialType(String credentialType) {
		return PasswordCredentialModel.TYPE.endsWith(credentialType);
	}

	@Override
	public boolean isConfiguredFor(RealmModel realmModel, UserModel userModel, String credentialType) {
		return supportsCredentialType(credentialType);
	}

	@Override
	public boolean isValid(RealmModel realmModel, UserModel userModel, CredentialInput credentialInput) {
		LOG.info("Validating user ...");
		if (!this.supportsCredentialType(credentialInput.getType())) {
			return false;
		}

		StorageId sid = new StorageId(userModel.getId());
		String username = sid.getExternalId();

		try (Connection c = DbUtil.getConnection(this.model)) {
			PreparedStatement st = c.prepareStatement(PASSWORD_BY_USERNAME);
			st.setString(1, username);
			st.execute();
			ResultSet rs = st.getResultSet();
			if (rs.next()) {
				String pwd = rs.getString(1);

				BCrypt.Result verify = BCrypt.verifyer().verify(credentialInput.getChallengeResponse().toCharArray(), pwd.toCharArray());
				return verify.verified;
			} else {
				return false;
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Database error: " + ex.getMessage(), ex);
		}
	}

	@Override
	public void close() {

	}

	@Override
	public UserModel getUserById(String id, RealmModel realm) {
		LOG.info("Getting user by id {}...", id);
		try (Connection c = DbUtil.getConnection(this.model)) {
			PreparedStatement st = c.prepareStatement(USER_BY_ID);
			String mysqlId = id.substring(id.lastIndexOf(':') + 1);
			st.setString(1, mysqlId);
			st.execute();
			ResultSet rs = st.getResultSet();
			if (rs.next()) {
				return mapUser(realm, rs);
			} else {
				return null;
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Database error: " + ex.getMessage(), ex);
		}
	}

	@Override
	public UserModel getUserByUsername(String username, RealmModel realm) {
		LOG.info("Getting user by username {}...", username);
		try (Connection c = DbUtil.getConnection(this.model)) {
			PreparedStatement st = c.prepareStatement(USER_BY_USERNAME);
			st.setString(1, username);
			st.execute();
			ResultSet rs = st.getResultSet();
			if (rs.next()) {
				return mapUser(realm, rs);
			} else {
				return null;
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Database error: " + ex.getMessage(), ex);
		}
	}

	private UserModel mapUser(RealmModel realm, ResultSet rs) throws SQLException {
		String[] firstlastname = rs.getString("user_name").split(" ");

		return FoodbankMysqlUser.customUser(ksession, realm, model, rs.getString("id_user"))
				.withEmail(rs.getString("email"))
				.withFirstName(firstlastname[1])
				.withLastName(firstlastname[0])
				.withGroups(rs.getString("rights"))
				.build();
	}

	@Override
	public UserModel getUserByEmail(String email, RealmModel realm) {
		LOG.info("Getting user by email {}...", email);
		try (Connection c = DbUtil.getConnection(this.model)) {
			PreparedStatement st = c.prepareStatement(USER_BY_EMAIL);
			st.setString(1, email);
			st.execute();
			ResultSet rs = st.getResultSet();
			if (rs.next()) {
				return mapUser(realm, rs);
			} else {
				return null;
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Database error: " + ex.getMessage(), ex);
		}
	}

	@Override
	public int getUsersCount(RealmModel realmModel) {
		LOG.info("Getting user count}...");
		try (Connection c = DbUtil.getConnection(this.model)) {
			PreparedStatement st = c.prepareStatement(USER_COUNT);
			st.execute();
			ResultSet rs = st.getResultSet();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Database error: " + ex.getMessage(), ex);
		}
	}

	@Override
	public List<UserModel> getUsers(RealmModel realm) {
		LOG.info("Getting all users...");
		try (Connection c = DbUtil.getConnection(this.model)) {
			PreparedStatement st = c.prepareStatement(USERS);

			st.execute();
			ResultSet rs = st.getResultSet();

			List<UserModel> result = new ArrayList<>();

			while (rs.next()) {
				UserModel userModel = mapUser(realm, rs);
				result.add(userModel);
			}

			return result;

		} catch (SQLException ex) {
			LOG.error("Database error", ex);
			throw new RuntimeException("Database error: " + ex.getMessage(), ex);
		}
	}

	@Override
	public List<UserModel> getUsers(RealmModel realm, int firstResult, int maxResults) {
		LOG.info("Getting users page...");
		try (Connection c = DbUtil.getConnection(this.model)) {
			PreparedStatement st = c.prepareStatement(USERS_PAGE);
			st.setInt(1, firstResult);
			st.setInt(2, maxResults);
			st.execute();
			ResultSet rs = st.getResultSet();

			List<UserModel> result = new ArrayList<>();

			while (rs.next()) {
				UserModel userModel = mapUser(realm, rs);
				result.add(userModel);
			}

			return result;

		} catch (SQLException ex) {
			throw new RuntimeException("Database error: " + ex.getMessage(), ex);
		}
	}

	@Override
	public List<UserModel> searchForUser(String search, RealmModel realm) {
		LOG.info("Searching user '{}'...", search);
		try (Connection c = DbUtil.getConnection(this.model)) {
			PreparedStatement st = c.prepareStatement(USERS_LOOKUP);
			String searchLike = "%" + search + "%";
			st.setString(1, searchLike);
			st.setString(2, searchLike);
			st.setString(3, searchLike);
			st.execute();
			ResultSet rs = st.getResultSet();

			List<UserModel> result = new ArrayList<>();

			while (rs.next()) {
				UserModel userModel = mapUser(realm, rs);
				result.add(userModel);
			}

			return result;

		} catch (SQLException ex) {
			LOG.error("Database error: ", ex);
			throw new RuntimeException("Database error: " + ex.getMessage(), ex);
		}
	}

	@Override
	public List<UserModel> searchForUser(String search, RealmModel realm, int firstResult, int maxResults) {
		LOG.info("Searching user '{}'...", search);
		try (Connection c = DbUtil.getConnection(this.model)) {
			PreparedStatement st = c.prepareStatement(USERS_LOOKUP_PAGE);
			String searchLike = "%" + search + "%";
			st.setString(1, searchLike);
			st.setString(2, searchLike);
			st.setString(3, searchLike);
			st.setInt(4, firstResult);
			st.setInt(5, maxResults);
			st.execute();
			ResultSet rs = st.getResultSet();

			List<UserModel> result = new ArrayList<>();

			while (rs.next()) {
				UserModel userModel = mapUser(realm, rs);
				result.add(userModel);
			}

			return result;

		} catch (SQLException ex) {
			LOG.error("Database error", ex);
			throw new RuntimeException("Database error: " + ex.getMessage(), ex);
		}
	}

	@Override
	public List<UserModel> searchForUser(Map<String, String> map, RealmModel realmModel) {
		LOG.info("Search for user {}...", map);
		return null;
	}

	@Override
	public List<UserModel> searchForUser(Map<String, String> map, RealmModel realmModel, int firstResult, int maxResults) {
		LOG.info("paginated Search for user {}...", map);
		return null;
	}

	@Override
	public List<UserModel> getGroupMembers(RealmModel realm, GroupModel groupModel) {
		LOG.info("Get group members {}...", groupModel.getName());
		String groupNameLike = "%" + groupModel.getName() + "%";
		try (Connection c = DbUtil.getConnection(this.model)) {
			PreparedStatement st = c.prepareStatement(USERS_BY_RIGHTS);

			st.setString(1, groupNameLike);
			st.execute();
			ResultSet rs = st.getResultSet();

			List<UserModel> result = new ArrayList<>();

			while (rs.next()) {
				UserModel userModel = mapUser(realm, rs);
				result.add(userModel);
			}

			return result;

		} catch (SQLException ex) {
			LOG.error("Database error", ex);
			throw new RuntimeException("Database error: " + ex.getMessage(), ex);
		}
	}

	@Override
	public List<UserModel> getGroupMembers(RealmModel realm, GroupModel groupModel, int firstResult, int maxResults) {
		LOG.info("Get group members {}...", groupModel.getName());
		String groupNameLike = "%" + groupModel.getName() + "%";
		try (Connection c = DbUtil.getConnection(this.model)) {
			PreparedStatement st = c.prepareStatement(USERS_BY_RIGHTS_PAGE);

			st.setString(1, groupNameLike);
			st.setInt(2, firstResult);
			st.setInt(3, maxResults);
			st.execute();
			ResultSet rs = st.getResultSet();

			List<UserModel> result = new ArrayList<>();

			while (rs.next()) {
				UserModel userModel = mapUser(realm, rs);
				result.add(userModel);
			}

			return result;

		} catch (SQLException ex) {
			LOG.error("Database error", ex);
			throw new RuntimeException("Database error: " + ex.getMessage(), ex);
		}
	}

	@Override
	public List<UserModel> searchForUserByUserAttribute(String attribute, String value, RealmModel realmModel) {
		LOG.info("Search by attribute {} with value {}...", attribute, value);
		return null;
	}
}
