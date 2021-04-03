package foodbank.it.keycloak;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.keycloak.common.util.MultivaluedHashMap;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.storage.adapter.AbstractUserAdapter;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
public class CustomUser extends AbstractUserAdapter {

	private final String username;
	private final String firstName;
	private final String lastName;
	private final String email;
	private final Date birthDate;

	public CustomUser(KeycloakSession session, RealmModel realm, ComponentModel storageProviderModel, String username, String firstName, String lastName, String email, Date birthDate) {
		super(session, realm, storageProviderModel);
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
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
		attributes.add("birthDate", getBirthDate().toString());
		return attributes;
	}

	@RequiredArgsConstructor
	public static class CustomUserBuilder {
		private final KeycloakSession keycloakSession;
		private final RealmModel realmModel;
		private final ComponentModel componentModel;
		private final String username;
		private String firstName;
		private String lastName;
		private String email;
		private Date birthDate;

		public CustomUser build() {
			return new CustomUser(keycloakSession, realmModel, componentModel, username, firstName, lastName, email, birthDate);
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

		public CustomUserBuilder withBirthDate(Date birthDate) {
			this.birthDate = birthDate;
			return this;
		}
	}
}
