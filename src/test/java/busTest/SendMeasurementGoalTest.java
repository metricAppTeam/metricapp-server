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
import metricapp.entity.measurementGoal.MeasurementGoal;
import metricapp.exception.BadInputException;
import metricapp.exception.BusException;
import metricapp.service.spec.repository.BusApprovedElementInterface;
import metricapp.service.spec.repository.MeasurementGoalRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BootApplication.class)
@WebAppConfiguration
@TestPropertySource("/test.properties")
public class SendMeasurementGoalTest {
	MeasurementGoal measurementGoal;

	@Autowired
	private MeasurementGoalRepository measurementGoalRepository;
	
	@Autowired
	private BusApprovedElementInterface busApprovedElementRepository;
	
	@Before
	public void prepareElement(){		
	
		// create a metric
		measurementGoal = new MeasurementGoal();
		try {
			measurementGoal.randomAttributes();
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
		
		measurementGoal.setEntityType(Entity.MeasurementGoal);
	
	}
	
	@Test
	public void createOnBus() throws IOException, BadInputException, BusException {
		measurementGoal.setState(State.Approved);
		measurementGoal.setVersionBus(null);
		
		MeasurementGoal measurementGoal2 = (MeasurementGoal) busApprovedElementRepository.sendApprovedElement(measurementGoal, MeasurementGoal.class);
		
		PointerBus pointerBus = new PointerBus();
		pointerBus.setBusVersion(measurementGoal2.getVersionBus());
		pointerBus.setInstance(measurementGoal2.getId());
		pointerBus.setTypeObj(Entity.MeasurementGoal.name());
		MeasurementGoal measurementGoal3 = (MeasurementGoal) busApprovedElementRepository.getApprovedElement(pointerBus, MeasurementGoal.class);
		// clean db
		measurementGoalRepository.delete(measurementGoal.getId());
		
		assertEquals(measurementGoal3, measurementGoal2);
	}

	@Test
	public void updateOnBus() throws IOException, BadInputException, BusException {
		measurementGoal.setState(State.Approved);
		measurementGoal.setVersionBus(null);
		
		//send an elemnt to bus
		MeasurementGoal measurementGoal2 = (MeasurementGoal) busApprovedElementRepository.sendApprovedElement(measurementGoal, MeasurementGoal.class);
		
		//change something
		measurementGoal2.setPurpose("descr");
		
		//update
		MeasurementGoal measurementGoal3 = (MeasurementGoal) busApprovedElementRepository.sendApprovedElement(measurementGoal2, MeasurementGoal.class);
		assertNotEquals(measurementGoal.toString() +"\n"+ measurementGoal3.toString(),measurementGoal, measurementGoal3);
		assertTrue(measurementGoal3.getPurpose().equals("descr"));
	}
	
	@Test
	public void approvedCheck(){
		measurementGoal.setState(State.OnUpdate);
		
		try {
			busApprovedElementRepository.sendApprovedElement(measurementGoal, MeasurementGoal.class);
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
