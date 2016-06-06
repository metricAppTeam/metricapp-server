package mappingTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(BootApplication.class)
public class QuestionDTOToQuestion {

	@Autowired
	private ModelMapperFactory modelMapperFactory;
	
	private QuestionDTO questionDTO;
	private Question question;
	
	private Random random;

	public String getRandomString() {
        String RANDOMCHARS = "abcdefghijklmnopqrstuvwxyz";
        String randomString = "";
        
        for(int i=0; i<8; i++){
            randomString += RANDOMCHARS.charAt(random.nextInt(26));
        }
        
        return randomString;

    }
	
	public List<String> getRandomListOfStrings(){
		ArrayList<String> listOfStrings = new ArrayList<String>();
		
		int max = random.nextInt(10) + 2;
		
		for(int i=0; i<max; i++){
			listOfStrings.add(getRandomString());
		}
		
		return listOfStrings;
	}
	
	public String getRandomDate(){
		
		String randomDate = "";
		randomDate +=  Integer.toString(random.nextInt(31)) + "/" +
						Integer.toString(random.nextInt(13)) + "/" +
						Integer.toString(random.nextInt(2017));
		
		return randomDate;
	}
	
	public void initializeQuestionDTO(){
		this.questionDTO = new QuestionDTO();
		
		this.questionDTO.getMetadata().setId(Integer.toString(random.nextInt()));
		this.questionDTO.getMetadata().setCreationDate(getRandomDate());
		this.questionDTO.getMetadata().setLastVersionDate(getRandomDate());
		this.questionDTO.getMetadata().setReleaseNote(getRandomString());
		this.questionDTO.getMetadata().setCreatorId(Integer.toString(random.nextInt()));
		this.questionDTO.getMetadata().setState(State.OnUpdate_InternalRefinement);
		this.questionDTO.getMetadata().setTags(getRandomListOfStrings());
		
		this.questionDTO.setDescription(getRandomString());
		this.questionDTO.setFocus(getRandomString());
		this.questionDTO.setSubject(getRandomString());
		
	}
	
	@Before
	public void initialize(){
		random = new Random();
		initializeQuestionDTO();
		
		this.question = modelMapperFactory.getLooseModelMapper().map(this.questionDTO, Question.class); 
	}
	
	@Test
	public void testId() {
		assertTrue("Id true", question.getId().equals(questionDTO.getMetadata().getId()));
	}
	@Test
	public void testCreationDate() {
		assertTrue("CreationDate true", question.getCreationDate().equals(questionDTO.getMetadata().getCreationDate()));
	}
	@Test
	public void testLastVersionDate() {
		assertTrue("LastVersionDate true", question.getLastVersionDate().equals(questionDTO.getMetadata().getLastVersionDate()));
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
