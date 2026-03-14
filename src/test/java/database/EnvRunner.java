package database;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvRunner {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();
		String dburl = dotenv.get("DB_URL");
		String dbus = dotenv.get("DB_USERNAME");
		String dbpw = dotenv.get("DB_PASSWORD");
		System.out.println(dburl);
		System.out.println(dbus);
		System.out.println(dbpw);

	}

}
