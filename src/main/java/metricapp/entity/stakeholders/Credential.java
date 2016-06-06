package metricapp.entity.stakeholders;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Credential {
	@Id
	private String username;
	private String password;
}
