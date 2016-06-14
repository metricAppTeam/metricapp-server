package mappingTest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import metricapp.BootApplication;
import metricapp.dto.MetadataDTO;
import metricapp.dto.measurementGoal.InterpretationModelDTO;
import metricapp.dto.measurementGoal.MeasurementGoalDTO;
import metricapp.entity.State;
import metricapp.entity.measurementGoal.MeasurementGoal;
import metricapp.service.spec.controller.ModelMapperFactoryInterface;

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
		
		InterpretationModelDTO interpretationModelDTO = new InterpretationModelDTO();
		interpretationModelDTO.setFunctionJavascript(randomString());
		interpretationModelDTO.setQueryNoSQL(randomString());
		
		MetadataDTO metadataDTO = new MetadataDTO();
		metadataDTO.setId(randomString());
		metadataDTO.setCreatorId(randomString());
		metadataDTO.setReleaseNote(randomString());
		metadataDTO.setState(State.Created);
		metadataDTO.setVersion(randomString());
		metadataDTO.setTags(new ArrayList<String>());
		metadataDTO.setCreationDate(LocalDate.now().toString());
		metadataDTO.setLastVersionDate(LocalDate.now().toString());
		
		this.measurementGoalDTO.setId(randomString());
		this.measurementGoalDTO.setFocus(randomString());
		//this.measurementGoalDTO.setMetricatorId(randomString());
		this.measurementGoalDTO.setName(randomString());
		this.measurementGoalDTO.setObject(randomString());
		this.measurementGoalDTO.setOrganizationalGoalId(randomString());
		this.measurementGoalDTO.setViewPoint(randomString());
		this.measurementGoalDTO.setInterpretationModel(interpretationModelDTO);
		this.measurementGoalDTO.setMetadata(metadataDTO);
		
		
		ModelMapper modelMapper = modelMapperFactory.getLooseModelMapper();
		this.measurementGoal = modelMapper.map(this.measurementGoalDTO, MeasurementGoal.class);
		
				
		assertEquals(this.measurementGoalDTO.getId(), this.measurementGoal.getId());
		assertEquals(this.measurementGoalDTO.getInterpretationModel().getFunctionJavascript(), this.measurementGoal.getInterpretationModel().getFunctionJavascript());
		assertEquals(this.measurementGoalDTO.getInterpretationModel().getQueryNoSQL(), this.measurementGoal.getInterpretationModel().getQueryNoSQL());
		assertEquals(this.measurementGoalDTO.getObject(), this.measurementGoal.getObject());
		assertEquals(this.measurementGoalDTO.getOrganizationalGoalId(), this.measurementGoal.getOrganizationalGoal().getId());
		assertEquals(this.measurementGoalDTO.getViewPoint(), this.measurementGoal.getViewPoint());
		assertEquals(this.measurementGoalDTO.getMetadata().getReleaseNote(), this.measurementGoal.getReleaseNote());
		assertEquals(this.measurementGoalDTO.getMetadata().getState(), this.measurementGoal.getState());
		assertEquals(this.measurementGoalDTO.getMetadata().getVersion(), this.measurementGoal.getVersion());
		assertEquals(this.measurementGoalDTO.getMetadata().getCreationDate(), this.measurementGoal.getCreationDate().toString());
		assertEquals(this.measurementGoalDTO.getMetadata().getVersion(), this.measurementGoal.getVersion());
		assertEquals(this.measurementGoalDTO.getMetadata().getCreationDate(), this.measurementGoal.getLastVersionDate().toString());
		
	}

}

