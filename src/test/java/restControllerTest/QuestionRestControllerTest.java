package restControllerTest;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import metricapp.BootApplication;
import metricapp.dto.question.QuestionDTO;
import metricapp.entity.State;
import metricapp.service.RandomGenerator;
import metricapp.service.controller.QuestionCRUDController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BootApplication.class)
@WebAppConfiguration
public class QuestionRestControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private QuestionCRUDController questionCRUDController;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	private QuestionDTO questionDTO1;
	private QuestionDTO questionDTO2;
	
//	@Autowired
//	private RandomGenerator rndGen;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	private HttpMessageConverter<?> mappingJackson2HttpMessageConverter;
	
	@Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                new Predicate<HttpMessageConverter<?>>() {
					@Override
					public boolean test(HttpMessageConverter<?> hmc) {
						return hmc instanceof MappingJackson2HttpMessageConverter;
					}
				}).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
	
	private void initQuestionDTO(QuestionDTO questionDTO){
		questionDTO.getMetadata().setId(RandomGenerator.randomString());
		questionDTO.getMetadata().setCreationDate("2016-01-01");
		questionDTO.getMetadata().setLastVersionDate("2016-03-01");
		questionDTO.getMetadata().setReleaseNote(RandomGenerator.randomString());
		questionDTO.getMetadata().setCreatorId(RandomGenerator.randomString());
		questionDTO.getMetadata().setState(State.OnUpdate_InternalRefinement);
		questionDTO.getMetadata().setTags(RandomGenerator.randomArrayList());
		
		questionDTO.setDescription(RandomGenerator.randomString());
		questionDTO.setFocus(RandomGenerator.randomString());
		questionDTO.setSubject(RandomGenerator.randomString());
	}
	
	@Before
	public void setup(){
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		
		questionDTO1 = new QuestionDTO();
		questionDTO2 = new QuestionDTO();
		
		initQuestionDTO(questionDTO1);
		initQuestionDTO(questionDTO2);
		
		questionCRUDController.createQuestion(questionDTO1);
		questionCRUDController.createQuestion(questionDTO2);
	}
	
	@Test
	public void testGet(){
		try {
			MvcResult result = this.mockMvc
					.perform(get("/question?id=" + this.questionDTO1.getMetadata().getId()))
					.andExpect(status().isOk())
					.andReturn();
			
			assertTrue(HttpStatus.valueOf(result.getResponse().getStatus()) == HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
