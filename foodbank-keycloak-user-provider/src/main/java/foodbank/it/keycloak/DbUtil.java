package foodbank.it.keycloak;

import org.keycloak.component.ComponentModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static foodbank.it.keycloak.CustomUserStorageProviderConstants.*;

public class DbUtil {

	public static Connection getConnection(ComponentModel config) throws SQLException {
		String driverClass = config.get(CustomUserStorageProviderConstants.CONFIG_KEY_JDBC_DRIVER);

		try {
			Class.forName(driverClass);
		}
		catch(ClassNotFoundException nfe) {
			// ... error handling omitted
		}

		String url = config.get(CONFIG_KEY_JDBC_URL);
		String user = config.get(CONFIG_KEY_DB_USERNAME);
		String password = config.get(CONFIG_KEY_DB_PASSWORD);

		return DriverManager.getConnection(url, user, password);
	}
}
