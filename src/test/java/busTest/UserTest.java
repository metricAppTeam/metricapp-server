package busTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import metricapp.BootApplication;
import metricapp.entity.stakeholders.Role;
import metricapp.entity.stakeholders.User;
import metricapp.entity.stakeholders.User.Gender;
import metricapp.exception.BusException;
import metricapp.service.spec.repository.BusUserRepositoryInterface;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BootApplication.class)
@WebAppConfiguration
@TestPropertySource("/test.properties")
public class UserTest {

	@Autowired
	private BusUserRepositoryInterface busUser;
	
	@Test
	public void userTest() throws BusException{
		User user = busUser.findUserByUsername("admin");
		assertEquals(user.getUsername(), "admin");
		}
	
	// TODO do not execute this test, there's no way to delete users
	public void createTest() throws BusException{
		
		User user = new User();
		user.setPassword("password");
		user.setBirthday("1990-01-01");
		user.setRole(Role.Questioner);
		user.setGender(Gender.Male);
		user.setFirstname("Marco");
		user.setLastname("Piu");
		user.setUsername("questioner-mp");
		user.setPic("/pic");
		
		User user2 = busUser.registerUser(user);
		
		assertEquals(user2, user);
		}

}
