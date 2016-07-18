package metricapp.entity.OLD.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import metricapp.entity.stakeholders.Role;
import metricapp.entity.stakeholders.User.Gender;
import metricapp.utility.RandomGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor()
@TypeAlias("metricapp.User")
@Document(collection = "users_gmarciani")
public class User {
	
	@Id
	private String id;
	@Indexed(unique = true)
	private String username;
	private String password;
	
	public String firstname;
	public String lastname;
	public Gender gender;
	public String birthday;
	public String pic;
	public String website;
	public String email;
	public String mobile;
	public String online;
	public String bio;
	public Role role;
	
	public User randomUser() {
		User user = new User();
		user.setId(RandomGenerator.randomString());
		user.setUsername(RandomGenerator.randomString());
		user.setPassword(RandomGenerator.randomString());
		user.setFirstname(RandomGenerator.randomString());
		user.setLastname(RandomGenerator.randomString());
		user.setGender(RandomGenerator.randomEnum(Gender.class));
		user.setBirthday(RandomGenerator.randomString());
		user.setPic(RandomGenerator.randomString());
		user.setWebsite(RandomGenerator.randomString());
		user.setEmail(RandomGenerator.randomString());
		user.setMobile(RandomGenerator.randomString());
		user.setOnline(RandomGenerator.randomString());
		user.setBio(RandomGenerator.randomString());
		user.setRole(RandomGenerator.randomEnum(Role.class));
		return user;
	}

}
