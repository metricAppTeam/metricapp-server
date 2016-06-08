package mappingTest;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import metricapp.BootApplication;
import metricapp.dto.metric.MetricDTO;
import metricapp.entity.metric.Metric;
import metricapp.service.ModelMapperFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(BootApplication.class)
public class DTOToMetricTest {

	@Autowired
	public ModelMapperFactory modelMapperFactory;
	
	MetricDTO dto;
	Metric metric1;
	
	@Before
	public void mappingDTOToMetric() {
		this.dto = new MetricDTO();
		try {
			dto.randomAttributes();
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException | InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelMapper modelMapper = modelMapperFactory.getLooseModelMapper();
		metric1 = modelMapper.map(dto, Metric.class);
	}

	
	@Test
	public void testCreationDate(){
		//assertTrue(	metric1.getCreationDate().toString() + dto.getMetadata().getCreationDate().toString(),
		//		metric1.getCreationDate().equals(dto.getMetadata().getCreationDate().toString()) );
		assertEquals(metric1.getCreationDate().toString(), this.dto.getMetadata().getCreationDate());
	}
	
	@Test
	public void testDescription(){
		assertTrue(	metric1.getDescription()+(dto.getDescription()),
				metric1.getDescription().equals(dto.getDescription()) );
	}
	
	@Test
	public void testHasMax(){
		assertTrue(	metric1.isHasMax()+"," + dto.isHasMax(),
				metric1.isHasMax() == dto.isHasMax() );
	}
	
	@Test
	public void testHasMin(){
		assertTrue(	metric1.isHasMin() +","+ dto.isHasMin(),
				metric1.isHasMin() == dto.isHasMin() );
	}
	
	@Test
	public void testHasUserDefinedList(){
		assertTrue(	metric1.isHasUserDefinedList() +","+dto.isHasUserDefinedList(),
				metric1.isHasUserDefinedList() == dto.isHasUserDefinedList() );
	}
	
	@Test
	public void testId(){
		assertTrue(	metric1.getId()+(dto.getMetadata().getId()),
				metric1.getId().equals(dto.getMetadata().getId()) );
	}
	
	@Test
	public void testMax(){
		assertTrue(	metric1.getMax() +","+ dto.getMax(),
				metric1.getMax() == dto.getMax() );
	}
	
	@Test
	public void testMin(){
		assertTrue(	metric1.getMin() +","+ dto.getMin(),
				metric1.getMin() == dto.getMin() );
	}
	
	@Test
	public void testMetricatorId(){
		assertTrue(	metric1.getMetricatorId()+","+(dto.getMetricatorId()),
				metric1.getMetricatorId().equals(dto.getMetricatorId()));
	}
	
	@Test
	public void testName(){
		assertTrue(	metric1.getName()+","+dto.getName(),
				metric1.getName().equals(dto.getName()));
	}
	
	@Test
	public void testIsOrdered(){
		assertTrue(	metric1.isOrdered() +","+ dto.isOrdered() ,
				metric1.isOrdered() == dto.isOrdered());
	}
	
	@Test
	public void testReleaseNote(){
		assertTrue(	metric1.getReleaseNote()+","+(dto.getMetadata().getReleaseNote()),
				metric1.getReleaseNote().equals(dto.getMetadata().getReleaseNote()));
	}
	
	@Test
	public void testScaleType(){
		assertTrue(	metric1.getScaleType().toString()+","+(dto.getScaleType().toString()),
				metric1.getScaleType().toString().equals(dto.getScaleType().toString()));
	}
	
	@Test
	public void testSet(){
		System.out.println(	metric1.getSet()+","+(dto.getSet()));
		assertTrue(	metric1.getSet()+","+(dto.getSet()),
				metric1.getSet().equals(dto.getSet()));
	}
	
	@Test
	public void testTags(){
		assertTrue(	metric1.getTags()+","+dto.getMetadata().getTags(),
				metric1.getTags().equals(dto.getMetadata().getTags()));
	}
	
	@Test
	public void testUnit(){
		assertTrue(	metric1.getUnit()+","+(dto.getUnit()),
				metric1.getUnit().equals(dto.getUnit()));
	}
	
	@Test
	public void testUserDefinedList(){
		assertTrue(	metric1.getUserDefinedList()+","+(dto.getUserDefinedList()),
				metric1.getUserDefinedList().equals(dto.getUserDefinedList()));
	}
	
	@Test
	public void testVersion(){
		assertTrue(	metric1.getVersion()+","+(dto.getMetadata().getVersion()),
				metric1.getVersion().equals(dto.getMetadata().getVersion()));
	}
	
	@Test
	public void testLastVersionDate(){
//		assertTrue(	metric1.getLastVersionDate()+","+(dto.getMetadata().getLastVersionDate().toString()),
//				metric1.getLastVersionDate().equals(dto.getMetadata().getLastVersionDate().toString()));

		assertEquals(metric1.getLastVersionDate().toString(), this.dto.getMetadata().getLastVersionDate());
	}
	
	@Test
	public void testEntity(){
		assertTrue(	dto.getMetadata().getEntityType()+","+metric1.getEntityType(),
				dto.getMetadata().getEntityType().equals(metric1.getEntityType()));
	}

}
