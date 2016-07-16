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
import metricapp.utility.ModelMapperFactory;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(BootApplication.class)
public class UserBusToUserTest {

	@Autowired
	public ModelMapperFactory modelMapperFactory;
	
	UserBus userBus;
	User user;
	
	@Before
	public void before(){
		userBus = new UserBus("password", "[role, Metricator]", "Male", "surname", "2012-02-12", "name", "pic", "username", "instance", "busVersion", "typeObj");
	}
	
	@Test
	public void test() {
		
		user = modelMapperFactory.getStandardModelMapper().map(userBus, User.class);
		assertEquals("password", user.getPassword());
		assertEquals(Role.Metricator, user.getRole());
		assertEquals(User.Gender.Male, user.getGender());
		assertEquals("surname", user.getLastname());
		assertEquals(LocalDate.parse("2012-02-12"), user.getBirthday());
		assertEquals("name", user.getFirstname());
		assertEquals("pic", user.getPic());
		assertEquals("username", user.getUsername());
	}

}
