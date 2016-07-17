package metricapp.entity.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import metricapp.utility.RandomGenerator;

@Data
@TypeAlias("metricapp.UserSimple")
@Document(collection = "users_gmarciani")
public class UserSimple {
	
	@Id
	private String id;
	@Indexed(unique = true)
	private String username;	
	
	public UserSimple randomSimpleUser() {
		UserSimple user = new UserSimple();
		user.setId(RandomGenerator.randomString());
		user.setUsername(RandomGenerator.randomString());
		return user;
	}

}
