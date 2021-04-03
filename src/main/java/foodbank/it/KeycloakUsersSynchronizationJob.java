package foodbank.it;

import foodbank.it.persistence.model.TUser;
import foodbank.it.persistence.repository.ITUserRepository;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class KeycloakUsersSynchronizationJob {

	private static final Logger LOG = LoggerFactory.getLogger(KeycloakUsersSynchronizationJob.class);

	public static final String REALM = "master";
	public static final String KEYCLOAK_SERVER_URL = "http://localhost:8083/auth";
	public static final String KEYCLOAK_USER = "admin";
	public static final String KEYCLOAK_ADMIN_PASSWORD = "admin";
	public static final String KEYCLOAK_ADMIN_CLIENT_ID = "security-admin-console";

	private final ITUserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public KeycloakUsersSynchronizationJob(ITUserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@EventListener
	@Async
	public void synchronizeUsers(ApplicationStartedEvent event) {
		synchronizeUsers();
	}

	@Scheduled(fixedRate = 5_000)
	@Async
	public void synchronizeUsers() {
		LOG.info("Synchronizing users...");
		Keycloak keycloak = Keycloak.getInstance(KEYCLOAK_SERVER_URL, REALM, KEYCLOAK_USER, KEYCLOAK_ADMIN_PASSWORD, KEYCLOAK_ADMIN_CLIENT_ID);

		UsersResource usersResource = keycloak.realm(REALM).users();
		List<UserRepresentation> keycloakUsers = usersResource.list();

		userRepository.findAll()
				.forEach(dbUser -> synchronize(usersResource, keycloakUsers, dbUser));

		LOG.info("Synchronized users!");
	}

	private void synchronize(UsersResource usersResource, List<UserRepresentation> keycloakUsers, TUser dbUser) {
		Optional<UserRepresentation> keycloakUserOptional = findUserInKeycloakList(keycloakUsers, dbUser);

		keycloakUserOptional.ifPresent(keycloakUsers::remove);

		if (!keycloakUserOptional.isPresent()) {
			UserRepresentation user = createKeycloakUser(dbUser);

			usersResource.create(user);
		}
	}

	private Optional<UserRepresentation> findUserInKeycloakList(List<UserRepresentation> keycloakUsers, TUser dbUser) {
		return keycloakUsers
				.stream()
				.filter(keycloakUser -> keycloakUser.getUsername().equals(dbUser.getUserName()))
				.findFirst();
	}

	private UserRepresentation createKeycloakUser(TUser dbUser) {
		CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
		credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
		credentialRepresentation.setValue(dbUser.getPassword().replaceAll("\\{.*\\}", ""));

		UserRepresentation user = new UserRepresentation();
		String[] userName = dbUser.getUserName().split(" ");
		user.setFirstName(userName[1]);
		user.setLastName(userName[0]);
		user.setUsername(dbUser.getIdUser());
		user.setEmail(dbUser.getEmail());
		user.setEnabled(dbUser.getActif() == 1);
		user.setCredentials(Collections.singletonList(credentialRepresentation));
		return user;
	}
}
