package foodbank.it.keycloak;

import org.jboss.logging.Logger;
import org.keycloak.common.util.MultivaluedHashMap;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.GroupModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.adapter.AbstractUserAdapterFederatedStorage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class UserAdapter extends AbstractUserAdapterFederatedStorage {
	private static final Logger logger = Logger.getLogger(UserAdapter.class);
	protected UserEntity entity;
	protected String keycloakId;

	public UserAdapter(KeycloakSession session, RealmModel realm, ComponentModel model, UserEntity entity) {
		super(session, realm, model);
		this.entity = entity;
		keycloakId = StorageId.keycloakId(model, entity.getId());
	}

	@Override
	protected Set<GroupModel> getGroupsInternal() {
		String rights = entity.getRights();
		logger.info("Groups is: " + rights);
		List<String> groupNames = Arrays.asList(rights.toLowerCase().split(" "));
		Set<GroupModel> groupModelSet = realm.getGroupsStream()
				.filter(groupModel -> groupNames.contains(groupModel.getName().toLowerCase()))
				.collect(Collectors.toSet());
		logger.info("Result: " + groupModelSet.stream().map(GroupModel::getName).collect(Collectors.joining(", ")));

		return groupModelSet;
	}

	public String getPassword() {
		return entity.getPassword();
	}

	public void setPassword(String password) {
		entity.setPassword(password);
	}

	@Override
	public String getUsername() {
		return entity.getId();
	}

	@Override
	public void setUsername(String username) {
		entity.setId(username);

	}

	@Override
	public void setEmail(String email) {
		entity.setEmail(email);
	}

	@Override
	public String getEmail() {
		return entity.getEmail();
	}

	@Override
	public String getId() {
		return keycloakId;
	}

	@Override
	public Map<String, List<String>> getAttributes() {
		MultivaluedHashMap<String, String> attrs = getFederatedStorage().getAttributes(realm, this.getId());
		if (attrs == null) {
			attrs = new MultivaluedHashMap<>();
		}
		String firstAndLastName = entity.getFirstAndLastName();
		String[] splitNames = firstAndLastName.split(" ");
		String firstName = splitNames.length > 0 ? splitNames[0] : firstAndLastName;
		attrs.add(UserModel.FIRST_NAME, firstName);
		String lastName = splitNames.length > 1 ? splitNames[1] : firstAndLastName;
		attrs.add(UserModel.LAST_NAME, lastName);
		attrs.add(UserModel.EMAIL, getEmail());
		attrs.add(UserModel.USERNAME, getUsername());

		MultivaluedHashMap<String, String> all = new MultivaluedHashMap<>();
		all.putAll(attrs);

		return all;
	}

	@Override
	public void joinGroup(GroupModel group) {
		if (!getGroupsInternal().contains(group)) {
			String rights = entity.getRights();
			String newRights = String.join(" ", rights, group.getName());
			entity.setRights(newRights);
		}
	}

	@Override
	public void leaveGroup(GroupModel group) {
		if (getGroupsInternal().contains(group)) {
			String rights = entity.getRights();
			String newRights = Arrays.stream(rights.split(" "))
					.filter(right -> !right.equalsIgnoreCase(group.getName()))
					.collect(Collectors.joining(","));

			entity.setRights(newRights);
		}
	}

}