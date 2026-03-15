package ApiUtils;

import Constants.Role;

public class AuthTokenDemoRunner {

	public static void main(String[] args) throws InterruptedException {

		for (int i = 0; i <= 10; i++) {
			String token = AuthTokenProvider.getToken(Role.FD);
			System.out.println(token);
		}
		
	}

}
