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

import metricapp.dto.measurementGoal.InterpretationModelDTO;
import metricapp.dto.measurementGoal.MeasurementGoalDTO;

import metricapp.entity.measurementGoal.MeasurementGoal;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.utility.RandomGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(BootApplication.class)
public class DTOToMeasurementGoalTest {
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
	public void mapToEntity() {
		
		this.measurementGoalDTO = new MeasurementGoalDTO();
		
		try {
			this.measurementGoalDTO.randomAttributes();
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException | InstantiationException e) {
			e.printStackTrace();
			fail("error in random Generator");
		}
		InterpretationModelDTO interpretationModel = new InterpretationModelDTO();
		interpretationModel.setFunctionJavascript(RandomGenerator.randomString());
		interpretationModel.setQueryNoSQL(RandomGenerator.randomString());
		this.measurementGoalDTO.setInterpretationModel(interpretationModel);
		
		ModelMapper modelMapper = modelMapperFactory.getStandardModelMapper();
		this.measurementGoal = modelMapper.map(this.measurementGoalDTO, MeasurementGoal.class);
		System.out.println(measurementGoalDTO.toString());
		System.out.println(measurementGoal.toString());
				
		assertEquals(this.measurementGoalDTO.getMetadata().getId(), this.measurementGoal.getId());
		assertEquals(this.measurementGoalDTO.getInterpretationModel().getFunctionJavascript(), this.measurementGoal.getInterpretationModel().getFunctionJavascript());
		assertEquals(this.measurementGoalDTO.getInterpretationModel().getQueryNoSQL(), this.measurementGoal.getInterpretationModel().getQueryNoSQL());
		assertEquals(this.measurementGoalDTO.getObject(), this.measurementGoal.getObject());
		assertEquals(this.measurementGoalDTO.getOrganizationalGoalId(), this.measurementGoal.getOrganizationalGoalId());
		assertEquals(this.measurementGoalDTO.getViewPoint(), this.measurementGoal.getViewPoint());
		assertEquals(this.measurementGoalDTO.getMetadata().getReleaseNote(), this.measurementGoal.getReleaseNote());
		assertEquals(this.measurementGoalDTO.getMetadata().getState(), this.measurementGoal.getState());
		assertEquals(this.measurementGoalDTO.getMetadata().getVersion(), this.measurementGoal.getVersion());
		assertEquals(this.measurementGoalDTO.getMetadata().getCreationDate(), this.measurementGoal.getCreationDate().toString());
		assertEquals(this.measurementGoalDTO.getMetadata().getVersion(), this.measurementGoal.getVersion());
		assertEquals(this.measurementGoalDTO.getMetadata().getLastVersionDate(), this.measurementGoal.getLastVersionDate().toString());
		assertEquals(this.measurementGoalDTO.getAssumptions(),this.measurementGoal.getAssumptions());
		assertEquals(this.measurementGoalDTO.getContextFactors(),this.measurementGoal.getContextFactors());
		assertEquals(this.measurementGoalDTO.getMetrics(),this.measurementGoal.getMetrics());
		assertEquals(this.measurementGoalDTO.getQuestions(),this.measurementGoal.getQuestions());
		assertEquals(this.measurementGoalDTO.getQuestionersId(),this.measurementGoal.getQuestionersId());
		assertEquals(this.measurementGoalDTO.getMetadata().getVersionBus(),this.measurementGoal.getVersionBus());
	}

}

