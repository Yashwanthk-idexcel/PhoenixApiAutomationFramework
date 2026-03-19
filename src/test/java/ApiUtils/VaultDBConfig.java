package ApiUtils;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import com.bettercloud.vault.response.LogicalResponse;

public class VaultDBConfig {

	private static VaultConfig config;
	private static Vault vault;
	private static final Logger LOGGER = LogManager.getLogger(VaultDBConfig.class);

	static {
		try {
			// SERVER IP Address & Token info are stored in System Environment Variables
			config = new VaultConfig().address(System.getenv("VAULT_SERVER")).token(System.getenv("VAULT_TOKEN"))
					.build();
			vault = new Vault(config);
		} catch (VaultException e) {
			LOGGER.error("Something went wrong reading the vault response", e);
			e.printStackTrace();
		}
	}

	private VaultDBConfig() {

	}

	public static String getSecret(String key) {

		LogicalResponse response = null;

		try {
			response = vault.logical().read("secret/phoenix/qa/database");

		} catch (VaultException e) {
			e.printStackTrace();
			return null; // if something goes wrong with Vault returning null
		}

		Map<String, String> mapData = response.getData();

		LOGGER.info("Secret found in the vault");
		return mapData.get(key);

	}

}
