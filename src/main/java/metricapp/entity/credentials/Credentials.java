package metricapp.entity.credentials;

import lombok.Data;
import metricapp.utility.RandomGenerator;

@Data
public class Credentials {
	
	private String username;
	private String password;
	
	public static Credentials randomCredentials() {
		Credentials credentials = new Credentials();
		credentials.setUsername(RandomGenerator.randomString());
		credentials.setPassword(RandomGenerator.randomString());
		return credentials;
	}

}
