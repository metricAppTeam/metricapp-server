package metricapp.dto.credentials;

import java.io.Serializable;

import lombok.Data;

@Data
public class CredentialsDTO implements Serializable {
	
	private static final long serialVersionUID = -5659470161268420949L;
	
	public String username;
	public String password;
	
}
