package foodbank.it.keycloak;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.jboss.logging.Logger;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputUpdater;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.models.GroupModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RoleModel;
import org.keycloak.models.UserCredentialModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.cache.CachedUserModel;
import org.keycloak.models.cache.OnUserCache;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;
import org.keycloak.storage.user.UserQueryProvider;
import org.keycloak.storage.user.UserRegistrationProvider;

import javax.ejb.Local;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Stateful
@Local(NewKeycloakFoodBankUserProvider.class)
public class NewKeycloakFoodBankUserProvider implements UserStorageProvider,
		UserLookupProvider,
		UserRegistrationProvider,
		UserQueryProvider,
		CredentialInputUpdater,
		CredentialInputValidator,
		OnUserCache
{
	private static final Logger logger = Logger.getLogger(NewKeycloakFoodBankUserProvider.class);
	public static final String PASSWORD_CACHE_KEY = UserAdapter.class.getName() + ".password";
	private static final BCrypt.Hasher HASHER = BCrypt.with(BCrypt.Version.VERSION_2Y);
	public static final int HASH_COST = 10;

	/* Password must contain at least one digit [0-9].
	Password must contain at least one lowercase Latin character [a-z].
	Password must contain at least one uppercase Latin character [A-Z].
	Password must contain a length of at least 8 characters and a maximum of 20 characters.
	*/
	private static final String PASSWORD_PATTERN =
			"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";

	private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

	@PersistenceContext
	protected EntityManager em;

	protected ComponentModel model;
	protected KeycloakSession session;

	public void setModel(ComponentModel model) {
		this.model = model;
	}

	public void setSession(KeycloakSession session) {
		this.session = session;
	}

	@Override
	public void preRemove(RealmModel realm) {

	}

	@Override
	public void preRemove(RealmModel realm, GroupModel group) {

	}

	@Override
	public void preRemove(RealmModel realm, RoleModel role) {

	}

	@Remove
	@Override
	public void close() {
	}

	@Override
	public UserModel getUserById(String id, RealmModel realm) {
		logger.info("getUserById: " + id);
		String persistenceId = StorageId.externalId(id);
		UserEntity entity = em.find(UserEntity.class, persistenceId);
		if (entity == null) {
			logger.info("could not find user by id: " + id);
			return null;
		}
		return new UserAdapter(session, realm, model, entity);
	}

	@Override
	public UserModel getUserByUsername(String username, RealmModel realm) {
		logger.info("getUserByUsername: " + username);
		TypedQuery<UserEntity> query = em.createNamedQuery("getUserByUsername", UserEntity.class);
		query.setParameter("username", username);
		List<UserEntity> result = query.getResultList();
		if (result.isEmpty()) {
			logger.info("could not find username: " + username);
			return null;
		}

		return new UserAdapter(session, realm, model, result.get(0));
	}

	@Override
	public UserModel getUserByEmail(String email, RealmModel realm) {
		TypedQuery<UserEntity> query = em.createNamedQuery("getUserByEmail", UserEntity.class);
		query.setParameter("email", email);
		List<UserEntity> result = query.getResultList();
		if (result.isEmpty()) return null;
		return new UserAdapter(session, realm, model, result.get(0));
	}

	@Override
	public UserModel addUser(RealmModel realm, String username) {
		UserEntity entity = new UserEntity();
		entity.setId(UUID.randomUUID().toString());
		entity.setFirstAndLastName(username);
		em.persist(entity);
		logger.info("added user: " + username);
		return new UserAdapter(session, realm, model, entity);
	}

	@Override
	public boolean removeUser(RealmModel realm, UserModel user) {
		String persistenceId = StorageId.externalId(user.getId());
		UserEntity entity = em.find(UserEntity.class, persistenceId);
		if (entity == null) return false;
		em.remove(entity);
		return true;
	}

	@Override
	public void onCache(RealmModel realm, CachedUserModel user, UserModel delegate) {
		String password = ((UserAdapter)delegate).getPassword();
		if (password != null) {
			user.getCachedWith().put(PASSWORD_CACHE_KEY, password);
		}
	}

	@Override
	public boolean supportsCredentialType(String credentialType) {
		return PasswordCredentialModel.TYPE.equals(credentialType);
	}

	@Override
	public boolean updateCredential(RealmModel realm, UserModel user, CredentialInput input) {
		if (!supportsCredentialType(input.getType()) || !(input instanceof UserCredentialModel)) return false;
		UserCredentialModel cred = (UserCredentialModel)input;
		UserAdapter adapter = getUserAdapter(user);

		String password = cred.getValue();
		Matcher matcher = pattern.matcher(password);
		logger.info("Checking password policy for password: " + password);
		if(!matcher.matches()) {
			logger.info("Password policy invalidates password: " + password);
			return false;
		};
		logger.info("Password policy has validated password: " + password);
		String hashedPassword = HASHER.hashToString(HASH_COST, password.toCharArray());
		adapter.setPassword(hashedPassword);

		return true;
	}

	public UserAdapter getUserAdapter(UserModel user) {
		if (user instanceof CachedUserModel) {
			CachedUserModel cachedUserModel = (CachedUserModel) user;
			return (UserAdapter) cachedUserModel.getDelegateForUpdate();
		}

		return  (UserAdapter)user;
	}

	@Override
	public void disableCredentialType(RealmModel realm, UserModel user, String credentialType) {
		if (!supportsCredentialType(credentialType)) return;

		getUserAdapter(user).setPassword(null);

	}

	@Override
	public Set<String> getDisableableCredentialTypes(RealmModel realm, UserModel user) {
		if (getUserAdapter(user).getPassword() != null) {
			Set<String> set = new HashSet<>();
			set.add(PasswordCredentialModel.TYPE);
			return set;
		} else {
			return Collections.emptySet();
		}
	}

	@Override
	public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {
		return supportsCredentialType(credentialType) && getPassword(user) != null;
	}

	@Override
	public boolean isValid(RealmModel realm, UserModel user, CredentialInput input) {
		logger.info("Validating user ...");
		if (!supportsCredentialType(input.getType()) || !(input instanceof UserCredentialModel)) return false;

		UserCredentialModel cred = (UserCredentialModel)input;
		String password = getPassword(user);
		String credentialValue = cred.getValue();

		BCrypt.Result verify = BCrypt.verifyer().verify(credentialValue.toCharArray(), password.toCharArray());
		return verify.verified;
	}

	public String getPassword(UserModel user) {
		String password = null;
		if (user instanceof CachedUserModel) {
			password = (String)((CachedUserModel)user).getCachedWith().get(PASSWORD_CACHE_KEY);
		} else if (user instanceof UserAdapter) {
			password = ((UserAdapter)user).getPassword();
		}
		return password;
	}

	@Override
	public int getUsersCount(RealmModel realm) {
		Object count = em.createNamedQuery("getUserCount")
				.getSingleResult();
		return ((Number)count).intValue();
	}

	@Override
	public List<UserModel> getUsers(RealmModel realm) {
		return getUsers(realm, -1, -1);
	}

	@Override
	public List<UserModel> getUsers(RealmModel realm, int firstResult, int maxResults) {

		TypedQuery<UserEntity> query = em.createNamedQuery("getAllUsers", UserEntity.class);
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}
		List<UserEntity> results = query.getResultList();
		List<UserModel> users = new LinkedList<>();
		for (UserEntity entity : results) users.add(new UserAdapter(session, realm, model, entity));
		return users;
	}

	@Override
	public List<UserModel> searchForUser(String search, RealmModel realm) {
		return searchForUser(search, realm, -1, -1);
	}

	@Override
	public List<UserModel> searchForUser(String search, RealmModel realm, int firstResult, int maxResults) {
		logger.info("Searching for user " + search);
		TypedQuery<UserEntity> query = em.createNamedQuery("searchForUser", UserEntity.class);
		query.setParameter("search", "%" + search.toLowerCase() + "%");
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}
		List<UserEntity> results = query.getResultList();
		List<UserModel> users = new LinkedList<>();
		for (UserEntity entity : results) users.add(new UserAdapter(session, realm, model, entity));
		return users;
	}

	@Override
	public List<UserModel> searchForUser(Map<String, String> params, RealmModel realm) {
		return Collections.EMPTY_LIST;
	}

	@Override
	public List<UserModel> searchForUser(Map<String, String> params, RealmModel realm, int firstResult, int maxResults) {
		return Collections.EMPTY_LIST;
	}

	@Override
	public List<UserModel> getGroupMembers(RealmModel realm, GroupModel group, int firstResult, int maxResults) {
		logger.info("Get group members " + group.getName());
		String groupNameLike = "%" + group.getName() + "%";
		TypedQuery<UserEntity> query = em.createNamedQuery("searchForGroupMembers", UserEntity.class);
		query.setParameter("search", groupNameLike);
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}
		List<UserEntity> results = query.getResultList();
		List<UserModel> users = new LinkedList<>();
		for (UserEntity entity : results) users.add(new UserAdapter(session, realm, model, entity));
		return users;
	}

	@Override
	public List<UserModel> getGroupMembers(RealmModel realm, GroupModel group) {
		return getGroupMembers(realm, group, -1, -1);
	}

	@Override
	public List<UserModel> searchForUserByUserAttribute(String attrName, String attrValue, RealmModel realm) {
		return Collections.EMPTY_LIST;
	}


}