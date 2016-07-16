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
import metricapp.entity.stakeholders.User;
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

}
