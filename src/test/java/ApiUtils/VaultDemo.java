package ApiUtils;

import java.util.Map;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import com.bettercloud.vault.response.LogicalResponse;

public class VaultDemo {

	public static void main(String[] args) throws VaultException {

		String serverIPAddress = System.getenv("VAULT_SERVER");
		String serverToken = System.getenv("VAULT_TOKEN");
		
		VaultConfig config = new VaultConfig().address(serverIPAddress).token(serverToken).build();

		Vault vault = new Vault(config);

		LogicalResponse logicalResponse = vault.logical().read("secret/phoenix/qa/database");

		Map<String, String> dataMap = logicalResponse.getData();

		dataMap.get("DB_URL");
		dataMap.get("DB_USERNAME");
		dataMap.get("DB_PASSWORD");

	}

}
