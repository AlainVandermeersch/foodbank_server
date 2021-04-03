package foodbank.it.keycloak;

import lombok.RequiredArgsConstructor;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class KeycloakFoodBankUserProvider implements UserStorageProvider,
		UserLookupProvider,
		CredentialInputValidator,
		UserQueryProvider {

	public static final String USER_BY_ID = "select username, firstName, lastName, email, birthDate from users where id = ?";
	public static final String USER_BY_USERNAME = "select username, firstName, lastName, email, birthDate from users where username = ?";
	public static final String USER_BY_EMAIL = "select username, firstName, lastName, email, birthDate from users where email = ?";
	public static final String USER_COUNT = "select count(*) from users";
	public static final String USERS = "select username, firstName, lastName, email, birthDate from users";
	public static final String USERS_PAGE = "select username, firstName, lastName, email, birthDate from users OFFSET ? LIMIT ?";
	public static final String PASSWORD_BY_USERNAME = "select password from users where username = ?";

	private final KeycloakSession ksession;
	private final ComponentModel model;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

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
				String credentialHash = bCryptPasswordEncoder.encode(credentialInput.getChallengeResponse());
				return pwd.equals(credentialHash);
			} else {
				return false;
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Database error:" + ex.getMessage(), ex);
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
			throw new RuntimeException("Database error:" + ex.getMessage(), ex);
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
			throw new RuntimeException("Database error:" + ex.getMessage(), ex);
		}
	}

	private UserModel mapUser(RealmModel realm, ResultSet rs) throws SQLException {
		return CustomUser.customUser(ksession, realm, model, rs.getString("username"))
				.withEmail(rs.getString("email"))
				.withFirstName(rs.getString("firstName"))
				.withLastName(rs.getString("lastName"))
				.withBirthDate(rs.getDate("birthDate"))
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
			throw new RuntimeException("Database error:" + ex.getMessage(), ex);
		}
	}

	@Override
	public int getUsersCount(RealmModel realmModel) {
		try (Connection c = DbUtil.getConnection(this.model)) {
			PreparedStatement st = c.prepareStatement(USER_COUNT);
			st.execute();
			ResultSet rs = st.getResultSet();
			if (rs.next()) {
				return rs.getInt(0);
			} else {
				return 0;
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Database error:" + ex.getMessage(), ex);
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
			throw new RuntimeException("Database error:" + ex.getMessage(), ex);
		}
	}

	@Override
	public List<UserModel> getUsers(RealmModel realm, int firstResult, int maxResults) {
		try (Connection c = DbUtil.getConnection(this.model)) {
			PreparedStatement st = c.prepareStatement(USERS_PAGE);
			st.setInt(0, firstResult);
			st.setInt(1, maxResults);
			st.execute();
			ResultSet rs = st.getResultSet();

			List<UserModel> result = new ArrayList<>();

			while (rs.next()) {
				UserModel userModel = mapUser(realm, rs);
				result.add(userModel);
			}

			return result;

		} catch (SQLException ex) {
			throw new RuntimeException("Database error:" + ex.getMessage(), ex);
		}
	}

	@Override
	public List<UserModel> searchForUser(String s, RealmModel realmModel) {
		return null;
	}

	@Override
	public List<UserModel> searchForUser(String s, RealmModel realmModel, int i, int i1) {
		return null;
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
