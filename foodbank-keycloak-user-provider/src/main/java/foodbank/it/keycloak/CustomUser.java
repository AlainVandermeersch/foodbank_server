package foodbank.it.keycloak;

import org.keycloak.common.util.MultivaluedHashMap;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.storage.adapter.AbstractUserAdapter;

import java.util.List;
import java.util.Map;

public class CustomUser extends AbstractUserAdapter {

	private final String username;
	private final String firstName;
	private final String lastName;
	private final String email;

	public CustomUser(KeycloakSession session, RealmModel realm, ComponentModel storageProviderModel, String username, String firstName, String lastName, String email) {
		super(session, realm, storageProviderModel);
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public static CustomUserBuilder customUser(KeycloakSession keycloakSession, RealmModel realmModel, ComponentModel componentModel, String username) {
		return new CustomUserBuilder(keycloakSession, realmModel, componentModel, username);
	}

	@Override
	public Map<String, List<String>> getAttributes() {
		MultivaluedHashMap<String, String> attributes = new MultivaluedHashMap<>();
		attributes.add(USERNAME, getUsername());
		attributes.add(EMAIL, getEmail());
		attributes.add(FIRST_NAME, getFirstName());
		attributes.add(LAST_NAME, getLastName());
		return attributes;
	}

	public static class CustomUserBuilder {
		private final KeycloakSession keycloakSession;
		private final RealmModel realmModel;
		private final ComponentModel componentModel;
		private final String username;
		private String firstName;
		private String lastName;
		private String email;

		public CustomUserBuilder(KeycloakSession keycloakSession, RealmModel realmModel, ComponentModel componentModel, String username) {
			this.keycloakSession = keycloakSession;
			this.realmModel = realmModel;
			this.componentModel = componentModel;
			this.username = username;
		}

		public CustomUser build() {
			return new CustomUser(keycloakSession, realmModel, componentModel, username, firstName, lastName, email);
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
