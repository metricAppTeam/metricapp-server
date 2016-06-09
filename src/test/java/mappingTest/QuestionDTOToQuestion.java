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
import metricapp.service.ModelMapperFactory;
import metricapp.service.RandomGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(BootApplication.class)
public class QuestionDTOToQuestion {

	@Autowired
	private ModelMapperFactory modelMapperFactory;
	
	private QuestionDTO questionDTO;
	private Question question;
	
	public void initializeQuestionDTO(){
		this.questionDTO = new QuestionDTO();
		
		this.questionDTO.getMetadata().setId(RandomGenerator.randomString());
		this.questionDTO.getMetadata().setCreationDate(RandomGenerator.randomLocalDate().toString());
		this.questionDTO.getMetadata().setLastVersionDate(RandomGenerator.randomLocalDate().toString());
		this.questionDTO.getMetadata().setReleaseNote(RandomGenerator.randomString());
		this.questionDTO.getMetadata().setCreatorId(RandomGenerator.randomString());
		this.questionDTO.getMetadata().setState(State.OnUpdate_InternalRefinement);
		this.questionDTO.getMetadata().setTags(RandomGenerator.randomArrayList());
		
		this.questionDTO.setDescription(RandomGenerator.randomString());
		this.questionDTO.setFocus(RandomGenerator.randomString());
		this.questionDTO.setSubject(RandomGenerator.randomString());
		
	}
	
	@Before
	public void initialize(){
		initializeQuestionDTO();
		
		this.question = modelMapperFactory.getLooseModelMapper().map(this.questionDTO, Question.class); 
	}
	
	@Test
	public void testId() {
		assertTrue("Id true", question.getId().equals(questionDTO.getMetadata().getId()));
	}
	@Test
	public void testCreationDate() {
		assertTrue("CreationDate true", question.getCreationDate().toString().equals(questionDTO.getMetadata().getCreationDate()));
	}
	@Test
	public void testLastVersionDate() {
		assertTrue("LastVersionDate true", question.getLastVersionDate().toString().equals(questionDTO.getMetadata().getLastVersionDate()));
	}
	@Test
	public void testReleaseNote() {
		assertTrue("ReleaseNote true", question.getReleaseNote().equals(questionDTO.getMetadata().getReleaseNote()));
	}
	@Test
	public void testCreatorId() {
		assertTrue("CreatorId true", question.getCreatorId().equals(questionDTO.getMetadata().getCreatorId()));
	}
	@Test
	public void testState() {
		assertTrue("State true", question.getState().equals(questionDTO.getMetadata().getState()));
	}
	@Test
	public void testTags() {
		
		List<String> qDTOTags = questionDTO.getMetadata().getTags();
		List<String> qTags = question.getTags();
		
		assertTrue("Tags true", qTags.containsAll(qDTOTags));
	}
	@Test
	public void testDescription() {
		assertTrue("Description true", question.getDescription().equals(questionDTO.getDescription()));
	}
	@Test
	public void testFocus() {
		assertTrue("Focus true", question.getFocus().equals(questionDTO.getFocus()));
	}
	@Test
	public void testSubject() {
		assertTrue("Subject true", question.getSubject().equals(questionDTO.getSubject()));
	}
	

}
