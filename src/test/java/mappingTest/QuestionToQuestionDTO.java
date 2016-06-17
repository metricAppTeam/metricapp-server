package mappingTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import metricapp.BootApplication;
import metricapp.dto.question.QuestionDTO;
import metricapp.entity.State;
import metricapp.entity.question.Question;
import metricapp.utility.ModelMapperFactory;
import metricapp.utility.RandomGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(BootApplication.class)
public class QuestionToQuestionDTO {

	@Autowired
	private ModelMapperFactory modelMapperFactory;
	
	private QuestionDTO questionDTO;
	private Question question;
	
	public void initializeQuestion(){
		this.question = new Question();
		
		this.question.setId(RandomGenerator.randomString());
		this.question.setCreationDate(RandomGenerator.randomLocalDate());
		this.question.setLastVersionDate(RandomGenerator.randomLocalDate());
		this.question.setReleaseNote(RandomGenerator.randomString());
		this.question.setCreatorId(RandomGenerator.randomString());
		this.question.setState(State.Created);
		this.question.setTags(RandomGenerator.randomArrayList());
		this.question.setCreatorId(RandomGenerator.randomString());
		this.question.setDescription(RandomGenerator.randomString());
		this.question.setFocus(RandomGenerator.randomString());
		this.question.setSubject(RandomGenerator.randomString());
		
	}
	
	@Before
	public void initialize(){
		initializeQuestion();
		
		this.questionDTO = modelMapperFactory.getLooseModelMapper().map(this.question, QuestionDTO.class); 
	}
	
	@Test
	public void testId() {
		assertTrue("Id true", questionDTO.getMetadata().getId().equals(question.getId()));
	}
	@Test
	public void testCreationDate() {
		assertTrue("CreationDate true", questionDTO.getMetadata().getCreationDate().equals(question.getCreationDate().toString()));
	}
	@Test
	public void testLastVersionDate() {
		assertTrue("LastVersionDate true", questionDTO.getMetadata().getLastVersionDate().equals(question.getLastVersionDate().toString()));
	}
	@Test
	public void testReleaseNote() {
		assertTrue("ReleaseNote true", questionDTO.getMetadata().getReleaseNote().equals(question.getReleaseNote()));
	}
	@Test
	public void testCreatorId() {
		assertTrue("CreatorId true", questionDTO.getMetadata().getCreatorId().equals(question.getCreatorId()));
	}
	@Test
	public void testState() {
		assertTrue("State true", questionDTO.getMetadata().getState().equals(question.getState()));
	}
	@Test
	public void testTags() {
		
		List<String> qDTOTags = questionDTO.getMetadata().getTags();
		List<String> qTags = questionDTO.getMetadata().getTags();
		
		assertTrue("Tags true", qTags.containsAll(qDTOTags));
	}
	@Test
	public void testDescription() {
		assertTrue("Description true", questionDTO.getDescription().equals(question.getDescription()));
	}
	@Test
	public void testFocus() {
		assertTrue("Focus true", questionDTO.getFocus().equals(question.getFocus()));
	}
	@Test
	public void testSubject() {
		assertTrue("Subject true", questionDTO.getSubject().equals(question.getSubject()));
	}
	

}
