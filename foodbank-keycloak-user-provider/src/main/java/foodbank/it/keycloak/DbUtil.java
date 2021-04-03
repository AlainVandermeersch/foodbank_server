package foodbank.it.keycloak;

import org.keycloak.component.ComponentModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

	public static Connection getConnection(ComponentModel config) throws SQLException {
		String driverClass = config.get(CustomUserStorageProviderConstants.CONFIG_KEY_JDBC_DRIVER);
		try {
			Class.forName(driverClass);
		}
		catch(ClassNotFoundException nfe) {
			// ... error handling omitted
		}

		return DriverManager.getConnection(
				config.get(CustomUserStorageProviderConstants.CONFIG_KEY_JDBC_URL),
				config.get(CustomUserStorageProviderConstants.CONFIG_KEY_DB_USERNAME),
				config.get(CustomUserStorageProviderConstants.CONFIG_KEY_DB_PASSWORD));
	}
}
