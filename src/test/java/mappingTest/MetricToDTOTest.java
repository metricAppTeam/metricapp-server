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

import metricapp.utility.ModelMapperFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(BootApplication.class)
public class MetricToDTOTest {
	
	@Autowired
	public ModelMapperFactory modelMapperFactory;
	
	public Metric metric1;
	public MetricDTO dto;
	
	
	@Before
	public void mappingMetricToDTO() {
		metric1 = new Metric();
		try {
			metric1.randomAttributes();
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException | InstantiationException e) {
			e.printStackTrace();
			fail("randomize attribute error");
		}
		ModelMapper modelMapper= modelMapperFactory.getLooseModelMapper();
		dto = modelMapper.map(metric1, MetricDTO.class);
	}
	
	
	@Test
	public void testCreationDate(){
		assertEquals(	dto.getMetadata().getCreationDate() + metric1.getCreationDate().toString(),
				dto.getMetadata().getCreationDate(),metric1.getCreationDate().toString() );
	}
	
	@Test
	public void testDescription(){
		assertTrue(	dto.getDescription()+(metric1.getDescription()),
				dto.getDescription().equals(metric1.getDescription()) );
	}
	
	@Test
	public void testHasMax(){
		assertTrue(	dto.isHasMax()+"," + metric1.isHasMax(),
				dto.isHasMax() == metric1.isHasMax() );
	}
	
	@Test
	public void testHasMin(){
		assertTrue(	dto.isHasMin() +","+ metric1.isHasMin(),
				dto.isHasMin() == metric1.isHasMin() );
	}
	
	@Test
	public void testHasUserDefinedList(){
		assertTrue(	dto.isHasUserDefinedList() +","+metric1.isHasUserDefinedList(),
				dto.isHasUserDefinedList() == metric1.isHasUserDefinedList() );
	}
	
	@Test
	public void testId(){
		assertTrue(	dto.getMetadata().getId()+(metric1.getId()),
				dto.getMetadata().getId().equals(metric1.getId()) );
	}
	
	@Test
	public void testState(){
		assertTrue(	dto.getMetadata().getState().toString()+(metric1.getState()),
				dto.getMetadata().getState().equals(metric1.getState()) );
	}
	
	@Test
	public void testMax(){
		assertTrue(	dto.getMax() +","+ metric1.getMax(),
				dto.getMax() == metric1.getMax() );
	}
	
	@Test
	public void testMin(){
		assertTrue(	dto.getMin() +","+ metric1.getMin(),
				dto.getMin() == metric1.getMin() );
	}
	
	@Test
	public void testMetricatorId(){
		assertTrue(	dto.getMetricatorId()+","+(metric1.getMetricatorId()),
				dto.getMetricatorId().equals(metric1.getMetricatorId()));
	}
	
	@Test
	public void testName(){
		assertTrue(	dto.getName()+","+metric1.getName(),
				dto.getName().equals(metric1.getName()));
	}
	
	@Test
	public void testIsOrdered(){
		assertTrue(	dto.isOrdered() +","+ metric1.isOrdered() ,
				dto.isOrdered() == metric1.isOrdered());
	}
	
	@Test
	public void testReleaseNote(){
		assertTrue(	dto.getMetadata().getReleaseNote()+","+(metric1.getReleaseNote()),
				dto.getMetadata().getReleaseNote().equals(metric1.getReleaseNote()));
	}
	
	@Test
	public void testScaleType(){
		assertTrue(	dto.getScaleType().toString()+","+(metric1.getScaleType().toString()),
				dto.getScaleType().toString().equals(metric1.getScaleType().toString()));
	}
	
	@Test
	public void testSet(){
		assertTrue(	dto.getSet().toString()+","+(metric1.getSet().toString()),
				dto.getSet().toString().equals(metric1.getSet().toString()));
	}
	
	@Test
	public void testTags(){
		assertTrue(	dto.getMetadata().getTags()+","+metric1.getTags(),
				dto.getMetadata().getTags().equals(metric1.getTags()));
	}
	
	@Test
	public void testUnit(){
		assertTrue(	dto.getUnit()+","+(metric1.getUnit()),
				dto.getUnit().equals(metric1.getUnit()));
	}
	
	@Test
	public void testUserDefinedList(){
		assertTrue(	dto.getUserDefinedList()+","+(metric1.getUserDefinedList()),
				dto.getUserDefinedList().equals(metric1.getUserDefinedList()));
	}
	
	@Test
	public void testVersion(){
		assertTrue(	dto.getMetadata().getVersion()+","+(metric1.getVersion()),
				dto.getMetadata().getVersion().equals(metric1.getVersion()));
	}
	
	@Test
	public void testLastVersionDate(){
		assertTrue(	dto.getMetadata().getLastVersionDate()+","+metric1.getLastVersionDate().toString(),
				dto.getMetadata().getLastVersionDate().equals(metric1.getLastVersionDate().toString()));
	}
	
	@Test
	public void testEntity(){
		assertTrue(	dto.getMetadata().getEntityType()+","+metric1.getEntityType().toString(),
				dto.getMetadata().getEntityType().toString().equals(metric1.getEntityType().toString()));
	}
	
	

}
