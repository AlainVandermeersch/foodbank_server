package foodbank.it.keycloak;

import org.keycloak.common.util.MultivaluedHashMap;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.GroupModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RoleModel;
import org.keycloak.storage.adapter.AbstractUserAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FoodbankMysqlUser extends AbstractUserAdapter {

	private final Logger LOG = LoggerFactory.getLogger(FoodbankMysqlUser.class);

	private final String username;
	private final String firstName;
	private final String lastName;
	private final String email;
	private final String groups;

	public FoodbankMysqlUser(KeycloakSession session, RealmModel realm, ComponentModel storageProviderModel, String username, String firstName, String lastName, String email, String groups) {
		super(session, realm, storageProviderModel);
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.groups = groups;
	}

	public static CustomUserBuilder customUser(KeycloakSession keycloakSession, RealmModel realmModel, ComponentModel componentModel, String username) {
		return new CustomUserBuilder(keycloakSession, realmModel, componentModel, username);
	}

	@Override
	public Map<String, List<String>> getAttributes() {
		LOG.info("Getting user attributes...");
		MultivaluedHashMap<String, String> attributes = new MultivaluedHashMap<>();
		attributes.add(USERNAME, getUsername());
		attributes.add(EMAIL, getEmail());
		attributes.add(FIRST_NAME, getFirstName());
		attributes.add(LAST_NAME, getLastName());
		LOG.info(attributes.toString());
		return attributes;
	}

	@Override
	public boolean isMemberOf(GroupModel group) {
		return super.isMemberOf(group);
	}

	@Override
	public Set<GroupModel> getGroupsInternal() {
		LOG.info("Groups is: " + groups);
		List<String> groupNames = Arrays.asList(groups.toLowerCase().split(" "));
		Set<GroupModel> groupModelSet = realm.getGroupsStream()
				.filter(groupModel -> groupNames.contains(groupModel.getName().toLowerCase()))
				.collect(Collectors.toSet());
		LOG.info("Result: " + groupModelSet.stream().map(GroupModel::getName).collect(Collectors.joining(", ")));

		return groupModelSet;
	}

	@Override protected Set<RoleModel> getRoleMappingsInternal() {
		return super.getRoleMappingsInternal();
	}

	@Override
	public boolean hasRole(RoleModel role) {
		return super.hasRole(role);
	}

	public static class CustomUserBuilder {
		private final KeycloakSession keycloakSession;
		private final RealmModel realmModel;
		private final ComponentModel componentModel;
		private final String username;
		private String firstName;
		private String lastName;
		private String email;
		private String groups;

		public CustomUserBuilder(KeycloakSession keycloakSession, RealmModel realmModel, ComponentModel componentModel, String username) {
			this.keycloakSession = keycloakSession;
			this.realmModel = realmModel;
			this.componentModel = componentModel;
			this.username = username;
		}

		public FoodbankMysqlUser build() {
			return new FoodbankMysqlUser(keycloakSession, realmModel, componentModel, username, firstName, lastName, email, groups);
		}

		public CustomUserBuilder withFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public CustomUserBuilder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public CustomUserBuilder withEmail(String email) {
			this.email = email;
			return this;
		}

		public CustomUserBuilder withGroups(String groups) {
			this.groups = groups;
			return this;
		}
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public String getEmail() {
		return email;
	}
}
