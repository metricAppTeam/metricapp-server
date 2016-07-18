package mappingTest;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import metricapp.BootApplication;
import metricapp.dto.user.UserBus;
import metricapp.entity.stakeholders.Role;
import metricapp.entity.stakeholders.User;
import metricapp.entity.stakeholders.User.Gender;
import metricapp.utility.ModelMapperFactory;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(BootApplication.class)
public class UserToUserBusTest {

	@Autowired
	public ModelMapperFactory modelMapperFactory;
	
	UserBus userBus;
	User user;
	
	@Before
	public void before(){
		user = new User();
		user.setPassword("password");
		user.setRole(Role.Questioner);
		user.setGender(Gender.Male);
		user.setLastname("surname");
		//user.setBirthday(LocalDate.parse("2007-12-03"));
		user.setFirstname("name");
		user.setPic("pic");
		user.setUsername("username");
		
	}
	
	@Test
	public void test() {
		
		userBus = modelMapperFactory.getStandardModelMapper().map(user, UserBus.class);
		assertEquals("password", userBus.getPassword());
		assertEquals(Role.Questioner,userBus.getRole() );
		assertEquals(User.Gender.Male.name(), userBus.getGender());
		assertEquals("surname", userBus.getLastname());
		assertEquals(LocalDate.parse("2007-12-03").toString(), userBus.getBirthday());
		assertEquals("name", userBus.getFirstname());
		assertEquals("pic", userBus.getPic());
		assertEquals("username", userBus.getUsername());
	}

}
