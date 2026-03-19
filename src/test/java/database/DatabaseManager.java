package database;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import ApiUtils.ConfigManager;
import ApiUtils.EnvUtil;
import ApiUtils.VaultDBConfig;

public class DatabaseManager {

	// This should be kept first, since it's be used in loanSecrets()
	private static final Logger LOGGER = LogManager.getLogger(DatabaseManager.class);

	// Getting DB Creds from the .env file using dot-env java library
	private static boolean isVaultUp = true;
	private static final String DB_URL = loadSecrets("DB_URL");
	private static final String DB_USERNAME = loadSecrets("DB_USERNAME");
	private static final String DB_PASSWORD = loadSecrets("DB_PASSWORD");

	private static final int MAXIMUM_POOL_SIZE = Integer.parseInt(ConfigManager.getProperty("MAXIMUM_POOL_SIZE"));
	private static final int MINIMUM_IDLE_COUNT = Integer.parseInt(ConfigManager.getProperty("MINIMUM_IDLE_COUNT"));
	private static final int CONNECTION_TIMEOUT_IN_SECS = Integer
			.parseInt(ConfigManager.getProperty("CONNECTION_TIMEOUT_IN_SECS"));
	private static final int IDLE_TIMEOUT_IN_SECS = Integer.parseInt(ConfigManager.getProperty("IDLE_TIMEOUT_IN_SECS"));
	private static final int MAX_LIFE_TIME_IN_MINS = Integer
			.parseInt(ConfigManager.getProperty("MAX_LIFE_TIME_IN_MINS"));
	private static final String HIKARI_CP_POOL_NAME = ConfigManager.getProperty("HIKARI_CP_POOL_NAME");

	private static HikariConfig hikariConfig;
	private volatile static HikariDataSource hikariDataSource = null;
	private static Connection connection = null;

	private DatabaseManager() {

	}

	private static String loadSecrets(String key) {
		String value = null;

		if (isVaultUp) {
			value = VaultDBConfig.getSecret(key);

			if (value == null) {
				LOGGER.error("Vault Server is Down!! or Something went wrong with Vault");
				LOGGER.info("Reading Value from the .env file");
				value = EnvUtil.getValue(key);
				isVaultUp = false;
			} else {
				LOGGER.info("Reading Value for the key - {} from the vault server", key);
				return value;
			}
		}

		return value;
	}

	private static void initializePool() {

		if (hikariDataSource == null) { // First check - All Parallel threads will enter
			LOGGER.warn("Database connection is not available, Creating HIKARI Datasource");
			
			synchronized (DatabaseManager.class) {
				if (hikariDataSource == null) {
					hikariConfig = new HikariConfig();
					hikariConfig.setJdbcUrl(DB_URL);
					hikariConfig.setUsername(DB_USERNAME);
					hikariConfig.setPassword(DB_PASSWORD);
					hikariConfig.setMaximumPoolSize(MAXIMUM_POOL_SIZE);
					hikariConfig.setMinimumIdle(MINIMUM_IDLE_COUNT);
					hikariConfig.setConnectionTimeout(CONNECTION_TIMEOUT_IN_SECS * 1000);
					hikariConfig.setIdleTimeout(IDLE_TIMEOUT_IN_SECS * 1000);
					hikariConfig.setMaxLifetime(MAX_LIFE_TIME_IN_MINS * 60 * 1000); // 30min
					hikariConfig.setPoolName(HIKARI_CP_POOL_NAME);

					hikariDataSource = new HikariDataSource(hikariConfig);
					LOGGER.info("Created HIKARI Datasource");
				}
			}
		}
	}

	public static Connection getConnection() throws SQLException {

		if (hikariDataSource == null) {
			LOGGER.info("Initializing the Database connection using HIKARICP");
			initializePool();
		}
		else if (hikariDataSource.isClosed()) {
			LOGGER.info("HIKARICP Datasource is closed");
			throw new SQLException("HIKARI DATA SOURCE IS CLOSED.");
		}
		
		connection = hikariDataSource.getConnection();

		return connection;
	}

}
