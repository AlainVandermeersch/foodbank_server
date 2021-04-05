package foodbank.it.keycloak;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.models.GroupModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;
import org.keycloak.storage.user.UserQueryProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KeycloakFoodBankUserProvider implements UserStorageProvider, UserLookupProvider, CredentialInputValidator, UserQueryProvider {

	public static final String OFFSET_LIMIT = " OFFSET ? LIMIT ?";
	public static final String USER_COUNT = "select count(*) from t_user";
	public static final String PASSWORD_BY_USERNAME = "select password from t_user where id_user = ?";
	public static final String USERS = "select id_user, user_name, email from t_user";
	public static final String USERS_LOOKUP = USERS + " where id_user like ? or user_name like ? or email like ?";
	public static final String USERS_LOOKUP_PAGE = USERS_LOOKUP + OFFSET_LIMIT;
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
		try (Connection c = DbUtil.getConnection(this.model)) {
			PreparedStatement st = c.prepareStatement(USER_BY_ID);
			st.setString(1, id);
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

		return CustomUser.customUser(ksession, realm, model, rs.getString("id_user"))
				.withEmail(rs.getString("email"))
				.withFirstName(firstlastname[1])
				.withLastName(firstlastname[0])
				.build();
	}

	@Override
	public UserModel getUserByEmail(String email, RealmModel realm) {
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
			throw new RuntimeException("Database error: " + ex.getMessage(), ex);
		}
	}

	@Override
	public List<UserModel> getUsers(RealmModel realm, int firstResult, int maxResults) {
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
			throw new RuntimeException("Database error: " + ex.getMessage(), ex);
		}
	}

	@Override
	public List<UserModel> searchForUser(String search, RealmModel realm, int firstResult, int maxResults) {
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
			throw new RuntimeException("Database error: " + ex.getMessage(), ex);
		}
	}

	@Override
	public List<UserModel> searchForUser(Map<String, String> map, RealmModel realmModel) {
		return null;
	}

	@Override
	public List<UserModel> searchForUser(Map<String, String> map, RealmModel realmModel, int i, int i1) {
		return null;
	}

	@Override
	public List<UserModel> getGroupMembers(RealmModel realmModel, GroupModel groupModel) {
		return null;
	}

	@Override
	public List<UserModel> getGroupMembers(RealmModel realmModel, GroupModel groupModel, int i, int i1) {
		return null;
	}

	@Override
	public List<UserModel> searchForUserByUserAttribute(String s, String s1, RealmModel realmModel) {
		return null;
	}
}
