package mappingTest;

import static org.junit.Assert.*;

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
import metricapp.entity.OrganizationalGoal;
import metricapp.entity.State;
import metricapp.entity.measurementGoal.InterpretationModel;
import metricapp.entity.measurementGoal.MeasurementGoal;
import metricapp.service.spec.controller.ModelMapperFactoryInterface;

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
		organizationalGoal.setCreatorId(randomString());
		organizationalGoal.setFocus(randomString());
		organizationalGoal.setId(randomString());
		organizationalGoal.setMagnitude(randomString());
		organizationalGoal.setObject(randomString());
		organizationalGoal.setRelationship(randomString());
		organizationalGoal.setReleaseNote(randomString());
		organizationalGoal.setOrganizationalScope(randomString());
		organizationalGoal.setCreationDate(LocalDate.now());//generateLocalDate());
		organizationalGoal.setLastVersionDate(LocalDate.now());//generateLocalDate());
		//organizationalGoal.setTags(new List<String>());
		organizationalGoal.setState(State.Created);
		organizationalGoal.setTimeframe(randomString());
		organizationalGoal.setVersion(randomString());
		
		this.measurementGoal = new MeasurementGoal();

		this.measurementGoal.setCreatorId(randomString());
		this.measurementGoal.setId(randomString());
		this.measurementGoal.setInterpretationModel(interpretationModel);
		//this.measurementGoal.setMetricIdList();
		this.measurementGoal.setOrganizationalGoal(organizationalGoal);
		this.measurementGoal.setPurpose(randomString());
		this.measurementGoal.setQualityFocus(randomString());
		this.measurementGoal.setReleaseNote(randomString());
		this.measurementGoal.setVersion(randomString());
		this.measurementGoal.setViewPoint(randomString());
		this.measurementGoal.setCreationDate(LocalDate.now());
		this.measurementGoal.setLastVersionDate(LocalDate.now());

		ModelMapper modelMapper = modelMapperFactory.getLooseModelMapper();
		this.measurementGoalDTO = modelMapper.map(this.measurementGoal, MeasurementGoalDTO.class);

		
		assertEquals(this.measurementGoal.getId(), this.measurementGoalDTO.getId());
		assertEquals(this.measurementGoal.getInterpretationModel().getFunctionJavascript(), this.measurementGoalDTO.getInterpretationModel().getFunctionJavascript());
		assertEquals(this.measurementGoal.getInterpretationModel().getQueryNoSQL(), this.measurementGoalDTO.getInterpretationModel().getQueryNoSQL());
		assertEquals(this.measurementGoal.getObject(), this.measurementGoalDTO.getObject());
		assertEquals(this.measurementGoal.getOrganizationalGoal().getId(), this.measurementGoalDTO.getOrganizationalGoalId());
		assertEquals(this.measurementGoal.getViewPoint(), this.measurementGoalDTO.getViewPoint());
		assertEquals(this.measurementGoal.getReleaseNote(), this.measurementGoalDTO.getMetadata().getReleaseNote());
		assertEquals(this.measurementGoal.getState(), this.measurementGoalDTO.getMetadata().getState());
		assertEquals(this.measurementGoal.getVersion(), this.measurementGoalDTO.getMetadata().getVersion());
		assertEquals(this.measurementGoal.getCreationDate().toString(), this.measurementGoalDTO.getMetadata().getCreationDate());
		assertEquals(this.measurementGoal.getVersion(), this.measurementGoalDTO.getMetadata().getVersion());
		assertEquals(this.measurementGoal.getLastVersionDate().toString(), this.measurementGoalDTO.getMetadata().getCreationDate());
		

		System.out.println("id: " + this.measurementGoalDTO.getId() + "\n");
		System.out.println("object: " + this.measurementGoalDTO.getObject() + "\n");
		System.out.println("viewPoint: " + this.measurementGoalDTO.getViewPoint() + "\n");
		System.out.println("qualityFocus: " + this.measurementGoalDTO.getFocus() + "\n");
		System.out.println("releaseNote: " + this.measurementGoalDTO.getMetadata().getReleaseNote() + "\n");
		//System.out.println("purpose: " + goal.getPurpose + "\n");
		System.out.println("version: " + this.measurementGoalDTO.getMetadata().getVersion() + "\n");
		System.out.println("creationDate: " + this.measurementGoalDTO.getMetadata().getCreationDate() + "\n");
		System.out.println("lastVersionDate: " + this.measurementGoalDTO.getMetadata().getLastVersionDate() + "\n");
		System.out.println("metricsIdList: " + this.measurementGoalDTO.getMetricIdList() + "\n");
		System.out.println("questionIdList: " + this.measurementGoalDTO.getQuestionIdList() + "\n");
		
	}

}
