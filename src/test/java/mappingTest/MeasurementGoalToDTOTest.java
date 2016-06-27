package mappingTest;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import metricapp.BootApplication;
import metricapp.dto.measurementGoal.MeasurementGoalDTO;
import metricapp.entity.external.OrganizationalGoal;
import metricapp.entity.measurementGoal.InterpretationModel;
import metricapp.entity.measurementGoal.MeasurementGoal;
import metricapp.service.spec.ModelMapperFactoryInterface;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(BootApplication.class)
public class MeasurementGoalToDTOTest {
	private MeasurementGoal measurementGoal;
	private MeasurementGoalDTO measurementGoalDTO;
	
	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory;
	
	public String randomString(){
		return UUID.randomUUID().toString();
	}
	
	public LocalDate generateLocalDate(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//return LocalDate.parse("2016-01-04", formatter);
		return LocalDate.parse("2000-09-09",formatter);//LocalDate.now().format(formatter),formatter);
	}
	
	@Test
	public void mapToDTO() {

		InterpretationModel interpretationModel = new InterpretationModel();
		interpretationModel.setFunctionJavascript(randomString());
		interpretationModel.setQueryNoSQL(randomString());
		
		OrganizationalGoal organizationalGoal = new OrganizationalGoal();
		try {
			organizationalGoal.randomAttributes();
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException | InstantiationException e) {
			e.printStackTrace();
			fail("random attribute creation error");
		}
		
		this.measurementGoal = new MeasurementGoal();
		try {
			measurementGoal.randomAttributes();
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException | InstantiationException e) {
			e.printStackTrace();
			fail("random attribute creation error");
		}

		measurementGoal.setInterpretationModel(interpretationModel);
		
		ModelMapper modelMapper = modelMapperFactory.getStandardModelMapper();
		this.measurementGoalDTO = modelMapper.map(this.measurementGoal, MeasurementGoalDTO.class);

		
		assertEquals(this.measurementGoal.getId(), this.measurementGoalDTO.getMetadata().getId());
		assertEquals(this.measurementGoal.getInterpretationModel().getFunctionJavascript(), this.measurementGoalDTO.getInterpretationModel().getFunctionJavascript());
		assertEquals(this.measurementGoal.getInterpretationModel().getQueryNoSQL(), this.measurementGoalDTO.getInterpretationModel().getQueryNoSQL());
		assertEquals(this.measurementGoal.getObject(), this.measurementGoalDTO.getObject());
		assertEquals(this.measurementGoal.getOrganizationalGoalId(), this.measurementGoalDTO.getOrganizationalGoalId());
		assertEquals(this.measurementGoal.getViewPoint(), this.measurementGoalDTO.getViewPoint());
		assertEquals(this.measurementGoal.getReleaseNote(), this.measurementGoalDTO.getMetadata().getReleaseNote());
		assertEquals(this.measurementGoal.getState(), this.measurementGoalDTO.getMetadata().getState());
		assertEquals(this.measurementGoal.getVersion(), this.measurementGoalDTO.getMetadata().getVersion());
		assertEquals(this.measurementGoal.getCreationDate().toString(), this.measurementGoalDTO.getMetadata().getCreationDate());
		assertEquals(this.measurementGoal.getVersion(), this.measurementGoalDTO.getMetadata().getVersion());
		assertEquals(this.measurementGoal.getVersionBus(), this.measurementGoalDTO.getMetadata().getVersionBus());
		assertEquals(this.measurementGoal.getLastVersionDate().toString(), this.measurementGoalDTO.getMetadata().getLastVersionDate());
		assertEquals(this.measurementGoalDTO.getAssumptions(),this.measurementGoal.getAssumptions());
		assertEquals(this.measurementGoalDTO.getContextFactors(),this.measurementGoal.getContextFactors());
		assertEquals(this.measurementGoalDTO.getMetrics(),this.measurementGoal.getMetrics());
		assertEquals(this.measurementGoalDTO.getQuestions(),this.measurementGoal.getQuestions());
		assertEquals(this.measurementGoalDTO.getQuestionersId(),this.measurementGoal.getQuestionersId());
	}

}
