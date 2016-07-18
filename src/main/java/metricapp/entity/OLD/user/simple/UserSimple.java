package metricapp.entity.OLD.user.simple;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import metricapp.utility.RandomGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor()
@TypeAlias("metricapp.UserSimple")
@Document(collection = "users_gmarciani")
public class UserSimple {
	
	@Id
	private String id;
	@Indexed(unique = true)
	private String username;
	private String password;
	
	public UserSimple randomSimpleUser() {
		UserSimple user = new UserSimple();
		user.setId(RandomGenerator.randomString());
		user.setUsername(RandomGenerator.randomString());
		user.setPassword(RandomGenerator.randomString());
		return user;
	}

}
