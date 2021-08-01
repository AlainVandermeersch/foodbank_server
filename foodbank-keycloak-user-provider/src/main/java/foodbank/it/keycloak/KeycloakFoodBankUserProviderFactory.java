package foodbank.it.keycloak;

import org.jboss.logging.Logger;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;

import javax.naming.InitialContext;

public class KeycloakFoodBankUserProviderFactory  implements UserStorageProviderFactory<NewKeycloakFoodBankUserProvider> {

	public static final String PROVIDER_ID = "foodbank-mysql-user-provider";

	private static final Logger logger = Logger.getLogger(NewKeycloakFoodBankUserProvider.class);

	@Override
	public NewKeycloakFoodBankUserProvider create(KeycloakSession session, ComponentModel model) {
		try {
			InitialContext ctx = new InitialContext();
			String name = "java:global/" + PROVIDER_ID + "/" + NewKeycloakFoodBankUserProvider.class.getSimpleName();
			NewKeycloakFoodBankUserProvider provider = (NewKeycloakFoodBankUserProvider)ctx.lookup(name);
			provider.setModel(model);
			provider.setSession(session);
			return provider;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getId() {
		return PROVIDER_ID;
	}

	@Override
	public String getHelpText() {
		return "Foodbank Mysql User Storage Provider";
	}

	@Override
	public void close() {
		logger.info("<<<<<< Closing factory");

	}
}
