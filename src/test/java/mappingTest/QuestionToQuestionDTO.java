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
public class QuestionToQuestionDTO {

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
	
	public void initializeQuestion(){
		this.question = new Question();
		
		this.question.setId(Integer.toString(random.nextInt()));
//		this.question.setCreationDate(getRandomDate());
//		this.question.setLastVersionDate(getRandomDate());
		this.question.setReleaseNote(getRandomString());
		this.question.setCreatorId(Integer.toString(random.nextInt()));
		this.question.setState(State.OnUpdate_InternalRefinement);
		this.question.setTags(getRandomListOfStrings());
		
		this.question.setDescription(getRandomString());
		this.question.setFocus(getRandomString());
		this.question.setSubject(getRandomString());
		
	}
	
	@Before
	public void initialize(){
		random = new Random();
		initializeQuestion();
		
		this.questionDTO = modelMapperFactory.getLooseModelMapper().map(this.question, QuestionDTO.class); 
	}
	
	@Test
	public void testId() {
		assertTrue("Id true", questionDTO.getMetadata().getId().equals(question.getId()));
	}
	@Test
	public void testCreationDate() {
		assertTrue("CreationDate true", questionDTO.getMetadata().getCreationDate().equals(question.getCreationDate()));
	}
	@Test
	public void testLastVersionDate() {
		assertTrue("LastVersionDate true", questionDTO.getMetadata().getLastVersionDate().equals(question.getLastVersionDate()));
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
