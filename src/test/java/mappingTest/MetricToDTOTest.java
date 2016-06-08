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
public class MetricToDTOTest {
	
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
			n--;
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
	
	public void initializeElementMetric(){

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
	
	
	
	
	@Before
	public void mappingMetricToDTO() {
		randomizeAttributes();
		initializeElementMetric();
		ModelMapper modelMapper= modelMapperFactory.getLooseModelMapper();
		dto = modelMapper.map(metric1, MetricDTO.class);
	}
	
	
	@Test
	public void testCreationDate(){
		assertEquals(	dto.getMetadata().getCreationDate() + creationDate.toString(),
				dto.getMetadata().getCreationDate(),creationDate.toString() );
	}
	
	@Test
	public void testDescription(){
		assertTrue(	dto.getDescription()+(description),
				dto.getDescription().equals(description) );
	}
	
	@Test
	public void testHasMax(){
		assertTrue(	dto.isHasMax()+"," + hasMax,
				dto.isHasMax() == hasMax );
	}
	
	@Test
	public void testHasMin(){
		assertTrue(	dto.isHasMin() +","+ hasMin,
				dto.isHasMin() == hasMin );
	}
	
	@Test
	public void testHasUserDefinedList(){
		assertTrue(	dto.isHasUserDefinedList() +","+hasUserDefinedList,
				dto.isHasUserDefinedList() == hasUserDefinedList );
	}
	
	@Test
	public void testId(){
		assertTrue(	dto.getMetadata().getId()+(id),
				dto.getMetadata().getId().equals(id) );
	}
	
	@Test
	public void testMax(){
		assertTrue(	dto.getMax() +","+ max,
				dto.getMax() == max );
	}
	
	@Test
	public void testMin(){
		assertTrue(	dto.getMin() +","+ min,
				dto.getMin() == min );
	}
	
	@Test
	public void testMetricatorId(){
		assertTrue(	dto.getMetricatorId()+","+(metricatorId),
				dto.getMetricatorId().equals(metricatorId));
	}
	
	@Test
	public void testName(){
		assertTrue(	dto.getName()+","+name,
				dto.getName().equals(name));
	}
	
	@Test
	public void testIsOrdered(){
		assertTrue(	dto.isOrdered() +","+ isOrdered ,
				dto.isOrdered() == isOrdered);
	}
	
	@Test
	public void testReleaseNote(){
		assertTrue(	dto.getMetadata().getReleaseNote()+","+(releaseNote),
				dto.getMetadata().getReleaseNote().equals(releaseNote));
	}
	
	@Test
	public void testScaleType(){
		assertTrue(	dto.getScaleType().toString()+","+(scaleType.toString()),
				dto.getScaleType().toString().equals(scaleType.toString()));
	}
	
	@Test
	public void testSet(){
		assertTrue(	dto.getSet().toString()+","+(set.toString()),
				dto.getSet().toString().equals(set.toString()));
	}
	
	@Test
	public void testTags(){
		assertTrue(	dto.getMetadata().getTags()+","+tags,
				dto.getMetadata().getTags().equals(tags));
	}
	
	@Test
	public void testUnit(){
		assertTrue(	dto.getUnit()+","+(unit),
				dto.getUnit().equals(unit));
	}
	
	@Test
	public void testUserDefinedList(){
		assertTrue(	dto.getUserDefinedList()+","+(strings),
				dto.getUserDefinedList().equals(strings));
	}
	
	@Test
	public void testVersion(){
		assertTrue(	dto.getMetadata().getVersion()+","+(version),
				dto.getMetadata().getVersion().equals(version));
	}
	
	@Test
	public void testLastVersionDate(){
		assertTrue(	dto.getMetadata().getLastVersionDate()+","+lastVersionDate.toString(),
				dto.getMetadata().getLastVersionDate().equals(lastVersionDate.toString()));
	}
	
	

}
