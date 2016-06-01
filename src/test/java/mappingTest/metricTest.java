package mappingTest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import metricapp.BootApplication;
import metricapp.dto.metric.MetricDTO;
import metricapp.entity.State;
import metricapp.entity.metric.Metric;
import metricapp.entity.metric.ScaleType;
import metricapp.entity.metric.Set;
import metricapp.service.ModelMapperFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(BootApplication.class)
public class metricTest {
	
	@Autowired
	public ModelMapperFactory modelMapperFactory;
	
	public Random rnd = new Random();
	
	public Metric metric1;
	public Metric metric2;
	public MetricDTO dto;
	
	LocalDate creationDate;
	String creatorId;
	String description;
	boolean hasMax;
	boolean hasMin;
	boolean hasUserDefinedList;
	String id;
	LocalDate lastVersionDate;
	double max;
	String metricatorId;
	double min;
	String name;
	boolean isOrdered;
	String releaseNote;
	ScaleType scaleType;
	Set set;
	State state;
	ArrayList<String> tags;
	String unit;
	ArrayList<String> strings;
	String version;
	
	boolean resultMetricToDTO;
	boolean resultDTOToMetric;
	
	//it's safe use a uuid like a random strings(we assume there're no 
	//problems specifically with simbols or chars over F)
	public String randomString(){
		return UUID.randomUUID().toString();
	}
	
	public boolean randomBoolean(){
		return rnd.nextBoolean();
	}
	
	public double randomDouble(){
		return rnd.nextDouble();
	}
	
	public ArrayList<String> randomArrayList(){
		ArrayList<String> list = new ArrayList<String>();
		int n=rnd.nextInt(50);
		while(n>0){
			list.add(randomString());
		}
		return list;
	}
	
	
	public void randomizeAttributes(){
		creatorId = randomString();
		description = randomString();
		id = randomString();
		metricatorId = randomString();
		name = randomString();
		releaseNote = randomString();
		unit = randomString();
		version = randomString();
		
		creationDate = LocalDate.now();
		lastVersionDate= LocalDate.now();
		
		hasMax = randomBoolean();
		hasMin= randomBoolean();
		hasUserDefinedList= randomBoolean();
		isOrdered= randomBoolean();
		
		
		max=randomDouble();
		min=randomDouble();
		
		scaleType = ScaleType.ordinalScale;
		set = Set.reals;
		state = State.OnUpdate;
		
		tags = randomArrayList();
		strings = randomArrayList();

	}
	
	@Before
	public void initializeElement(){
		
		randomizeAttributes();
		
		this.metric1 = new Metric();
		
		this.metric1.setCreationDate(creationDate);
		this.metric1.setCreatorId(creatorId);
		this.metric1.setDescription(description);
		this.metric1.setHasMax(hasMax);
		this.metric1.setHasMin(hasMin);
		this.metric1.setHasUserDefinedList(hasUserDefinedList);
		this.metric1.setId(id);
		this.metric1.setLastVersionDate(lastVersionDate);
		this.metric1.setMax(max);
		this.metric1.setMetricatorId(metricatorId);
		this.metric1.setMin(min);
		this.metric1.setName(name);
		this.metric1.setOrdered(isOrdered);
		this.metric1.setReleaseNote(releaseNote);
		this.metric1.setScaleType(scaleType);
		this.metric1.setSet(set);
		this.metric1.setState(state);
		this.metric1.setTags(tags);
		this.metric1.setUnit(unit);
		this.metric1.setUserDefinedList(strings);
		this.metric1.setVersion(version);
	}
	
	
	@Test
	public void mapping() {
		metricToDTOTest();
		dTOtoMetricTest();
		
		assertTrue(metric1.toString() + "\n\n\n" + dto.toString(), resultDTOToMetric && resultMetricToDTO);
	}
	
	public void metricToDTOTest(){
		ModelMapper modelMapper= modelMapperFactory.getLooseModelMapper();
		dto = modelMapper.map(metric1, MetricDTO.class);
		
		if(
			dto.getMetadata().getCreationDate().equals(creationDate.toString()) &&
			dto.getMetadata().getCreatorId().equals(creatorId) &&
			dto.getDescription().equals(description) &&
			dto.isHasMax() == hasMax &&
			dto.isHasMin() == hasMin &&
			dto.isHasUserDefinedList() == hasUserDefinedList &&
			dto.getMetadata().getId().equals(id) &&
			dto.getMetadata().getLastVersionDate().equals(lastVersionDate.toString()) &&
			dto.getMax() == max &&
			dto.getMetricatorId().equals(metricatorId) &&
			dto.getMin() == min &&
			dto.getName().equals(name) &&
			dto.isOrdered() == isOrdered &&
			dto.getMetadata().getReleaseNote().equals(releaseNote) &&
			dto.getScaleType().toString().equals(scaleType.toString()) &&
			dto.getSet().toString().equals(set.toString()) &&
			dto.getMetadata().getTags().equals(tags) &&
			dto.getUnit().equals(unit) &&
			dto.getUserDefinedList().equals(strings) &&
			dto.getMetadata().getVersion().equals(version)
			){
			resultMetricToDTO = true;
		}
		else{
			resultMetricToDTO = false;
		}
	}
	
	public void dTOtoMetricTest(){
		ModelMapper modelMapper= modelMapperFactory.getLooseModelMapper();
		metric2 = modelMapper.map(dto, Metric.class);
		
		if(
				metric2.getCreationDate().toString().equals(dto.getMetadata().getCreationDate()) &&
				metric2.getCreatorId().equals(dto.getMetadata().getCreatorId()) &&
				metric2.getDescription().equals(dto.getDescription()) &&
				metric2.isHasMax() == dto.isHasMax() &&
				metric2.isHasMin() == dto.isHasMin() &&
				metric2.isHasUserDefinedList() == dto.isHasUserDefinedList() &&
				metric2.getId().equals(dto.getMetadata().getId()) &&
				metric2.getLastVersionDate().toString().equals(dto.getMetadata().getLastVersionDate()) &&
				metric2.getMax() == dto.getMax() &&
				metric2.getMetricatorId().equals(dto.getMetricatorId()) &&
				metric2.getMin() == dto.getMin() &&
				metric2.getName().equals(dto.getName()) &&
				metric2.isOrdered() == dto.isOrdered() &&
				metric2.getReleaseNote().equals(dto.getMetadata().getReleaseNote()) &&
				metric2.toString().equals(dto.getScaleType()) &&
				metric2.getSet().toString().equals(dto.getSet()) &&
				metric2.getTags().equals(dto.getMetadata().getTags()) &&
				metric2.getUnit().equals(dto.getUnit()) &&
				metric2.getUserDefinedList().equals(dto.getUserDefinedList()) &&
				metric2.getVersion().equals(dto.getMetadata().getVersion())
				){
				resultDTOToMetric = true;
			}
			else{
				resultDTOToMetric = false;
			}
	}

}
