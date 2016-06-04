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
public class DTOToMetricTest {

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

	// it's safe use a uuid like a random strings(we assume there're no
	// problems specifically with simbols or chars over F)
	public String randomString() {
		return UUID.randomUUID().toString();
	}

	public boolean randomBoolean() {
		return rnd.nextBoolean();
	}

	public double randomDouble() {
		return rnd.nextDouble();
	}

	public ArrayList<String> randomArrayList() {
		ArrayList<String> list = new ArrayList<String>();
		int n = rnd.nextInt(50);
		while (n > 0) {
			list.add(randomString());
			n--;
		}
		return list;
	}

	public void randomizeAttributes() {

		creatorId = randomString();
		description = randomString();
		id = randomString();
		metricatorId = randomString();
		name = randomString();
		releaseNote = randomString();
		unit = randomString();
		version = randomString();

		creationDate = LocalDate.now();
		lastVersionDate = LocalDate.now();

		hasMax = randomBoolean();
		hasMin = randomBoolean();
		hasUserDefinedList = randomBoolean();
		isOrdered = randomBoolean();

		max = randomDouble();
		min = randomDouble();

		scaleType = ScaleType.ordinalScale;
		set = Set.reals;
		state = State.OnUpdate;

		tags = randomArrayList();
		strings = randomArrayList();

	}

	public void initializeDTO() {

		this.dto = new MetricDTO();

		this.dto.getMetadata().setCreationDate(creationDate.toString());
		this.dto.getMetadata().setCreatorId(creatorId);
		this.dto.setDescription(description);
		this.dto.setHasMax(hasMax);
		this.dto.setHasMin(hasMin);
		this.dto.setHasUserDefinedList(hasUserDefinedList);
		this.dto.getMetadata().setId(id);
		this.dto.getMetadata().setLastVersionDate(lastVersionDate.toString());
		this.dto.setMax(max);
		this.dto.setMetricatorId(metricatorId);
		this.dto.setMin(min);
		this.dto.setName(name);
		this.dto.setOrdered(isOrdered);
		this.dto.getMetadata().setReleaseNote(releaseNote);
		this.dto.setScaleType(scaleType);
		this.dto.setSet(set.toString());
		this.dto.getMetadata().setState(state);
		this.dto.getMetadata().setTags(tags);
		this.dto.setUnit(unit);
		this.dto.setUserDefinedList(strings);
		this.dto.getMetadata().setVersion(version);

	}

	@Before
	public void mappingDTOToMetric() {
		randomizeAttributes();
		initializeDTO();
		ModelMapper modelMapper = modelMapperFactory.getLooseModelMapper();
		metric1 = modelMapper.map(dto, Metric.class);
	}

	
	@Test
	public void testCreationDate(){
		assertTrue(	metric1.getCreationDate().toString() + creationDate.toString(),
				metric1.getCreationDate().equals(creationDate.toString()) );
	}
	
	@Test
	public void testDescription(){
		assertTrue(	metric1.getDescription()+(description),
				metric1.getDescription().equals(description) );
	}
	
	@Test
	public void testHasMax(){
		assertTrue(	metric1.isHasMax()+"," + hasMax,
				metric1.isHasMax() == hasMax );
	}
	
	@Test
	public void testHasMin(){
		assertTrue(	metric1.isHasMin() +","+ hasMin,
				metric1.isHasMin() == hasMin );
	}
	
	@Test
	public void testHasUserDefinedList(){
		assertTrue(	metric1.isHasUserDefinedList() +","+hasUserDefinedList,
				metric1.isHasUserDefinedList() == hasUserDefinedList );
	}
	
	@Test
	public void testId(){
		assertTrue(	metric1.getId()+(id),
				metric1.getId().equals(id) );
	}
	
	@Test
	public void testMax(){
		assertTrue(	metric1.getMax() +","+ max,
				metric1.getMax() == max );
	}
	
	@Test
	public void testMin(){
		assertTrue(	metric1.getMin() +","+ min,
				metric1.getMin() == min );
	}
	
	@Test
	public void testMetricatorId(){
		assertTrue(	metric1.getMetricatorId()+","+(metricatorId),
				metric1.getMetricatorId().equals(metricatorId));
	}
	
	@Test
	public void testName(){
		assertTrue(	metric1.getName()+","+name,
				metric1.getName().equals(name));
	}
	
	@Test
	public void testIsOrdered(){
		assertTrue(	metric1.isOrdered() +","+ isOrdered ,
				metric1.isOrdered() == isOrdered);
	}
	
	@Test
	public void testReleaseNote(){
		assertTrue(	metric1.getReleaseNote()+","+(releaseNote),
				metric1.getReleaseNote().equals(releaseNote));
	}
	
	@Test
	public void testScaleType(){
		assertTrue(	metric1.getScaleType().toString()+","+(scaleType.toString()),
				metric1.getScaleType().toString().equals(scaleType.toString()));
	}
	
	@Test
	public void testSet(){
		assertTrue(	metric1.getSet().toString()+","+(set.toString()),
				metric1.getSet().toString().equals(set.toString()));
	}
	
	@Test
	public void testTags(){
		assertTrue(	metric1.getTags()+","+tags,
				metric1.getTags().equals(tags));
	}
	
	@Test
	public void testUnit(){
		assertTrue(	metric1.getUnit()+","+(unit),
				metric1.getUnit().equals(unit));
	}
	
	@Test
	public void testUserDefinedList(){
		assertTrue(	metric1.getUserDefinedList()+","+(strings),
				metric1.getUserDefinedList().equals(strings));
	}
	
	@Test
	public void testVersion(){
		assertTrue(	metric1.getVersion()+","+(version),
				metric1.getVersion().equals(version));
	}
	
	@Test
	public void testLastVersionDate(){
		assertTrue(	metric1.getLastVersionDate()+","+(lastVersionDate.toString()),
				metric1.getLastVersionDate().equals(lastVersionDate.toString()));
	}

}
