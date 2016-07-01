package busTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;



import metricapp.BootApplication;

import metricapp.entity.Entity;
import metricapp.entity.State;
import metricapp.entity.external.PointerBus;

import metricapp.entity.metric.Metric;
import metricapp.exception.BadInputException;
import metricapp.exception.BusException;
import metricapp.service.spec.repository.BusApprovedElementInterface;
import metricapp.service.spec.repository.MetricRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BootApplication.class)
@WebAppConfiguration
@TestPropertySource("/test.properties")
public class SendTest {
	Metric metric1;

	@Autowired
	private MetricRepository metricRepository;
	
	@Autowired
	private BusApprovedElementInterface busApprovedElementRepository;
	
	@Before
	public void prepareElement(){		
	
		// create a metric
		metric1 = new Metric();
		try {
			metric1.randomAttributes();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		
		metric1.setEntityType(Entity.Metric);
	
	}
	
	@Test
	public void createOnBus() throws IOException, BadInputException, BusException {
		metric1.setState(State.Approved);
		metric1.setVersionBus(null);
		
		Metric metric2 = (Metric) busApprovedElementRepository.sendApprovedElement(metric1, Metric.class);
		
		PointerBus pointerBus = new PointerBus();
		pointerBus.setBusVersion(metric2.getVersionBus());
		pointerBus.setInstance(metric2.getId());
		pointerBus.setTypeObj(Entity.Metric.name());
		Metric metric3 = (Metric) busApprovedElementRepository.getApprovedElement(pointerBus, Metric.class);
		// clean db
		metricRepository.delete(metric1.getId());
		
		assertEquals(metric3, metric2);
	}

	@Test
	public void updateOnBus() throws IOException, BadInputException, BusException {
		metric1.setState(State.Approved);
		metric1.setVersionBus(null);
		
		//send an elemnt to bus
		Metric metric2 = (Metric) busApprovedElementRepository.sendApprovedElement(metric1, Metric.class);
		
		//change something
		metric2.setDescription("descr");
		
		//update
		Metric metric3 = (Metric) busApprovedElementRepository.sendApprovedElement(metric2, Metric.class);
		assertNotEquals(metric1.toString() +"\n"+ metric3.toString(),metric1, metric3);
		assertTrue(metric3.getDescription().equals("descr"));
	}
	
	@Test
	public void approvedCheck(){
		metric1.setState(State.OnUpdate);
		
		try {
			busApprovedElementRepository.sendApprovedElement(metric1, Metric.class);
		} catch (BadInputException e) {
			assertTrue(true);
			return;
			
		} catch (BusException e) {
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		fail();
	}
	
}
