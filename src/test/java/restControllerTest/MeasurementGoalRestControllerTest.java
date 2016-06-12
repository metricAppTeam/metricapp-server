package restControllerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import metricapp.BootApplication;
import metricapp.dto.measurementGoal.MeasurementGoalDTO;
import metricapp.entity.State;
import metricapp.entity.measurementGoal.MeasurementGoal;
import metricapp.service.spec.repository.MeasurementGoalRepository;
import metricapp.utility.ModelMapperFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BootApplication.class)
@WebAppConfiguration
public class MeasurementGoalRestControllerTest {

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

		Assert.assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}
	
	private MockMvc mockMvc;

	@Autowired
	private MeasurementGoalRepository measurementGoalRepository;

	@Autowired
	private ModelMapperFactory modelMapperFactory;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MeasurementGoalDTO measurementGoalDTO1;
	private MeasurementGoal measurementGoal1;
	private MeasurementGoal measurementGoal2;
	private MeasurementGoal measurementGoal3;

	@SuppressWarnings("unchecked")
	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testDelete() {
		// create a measurement goal
		measurementGoal1 = MeasurementGoal.randomMeasurementGoal();
		measurementGoal1.setId(null);
		// set state to suspended, only a suspended entity could be deleted
		measurementGoal1.setState(State.Suspended);
		// insert measurement goal into the db
		measurementGoal1 = measurementGoalRepository.save(measurementGoal1);
		// check if the instance was correctly in created in db
		measurementGoal2 = measurementGoalRepository.findOne(measurementGoal1.getId());
		if (measurementGoal2 == null) {
			fail("error in insert, db connection fail");
		}

		// delete it from rest controller
		try {
			this.mockMvc.perform(delete("/measurementGoal?id=" + measurementGoal2.getId())).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
			fail("error in use of mock");
		}

		// check if the delete was done
		measurementGoal3= measurementGoalRepository.findOne(measurementGoal2.getId());
		assertEquals(measurementGoal3, null);
	}

	@Test
	public void testPost() {
		// create a measurementGoal
		measurementGoal1 = MeasurementGoal.randomMeasurementGoal();
		measurementGoal1.setId(null);
		measurementGoal1.setVersion(null);
		// convert to dto
		measurementGoalDTO1 = modelMapperFactory.getLooseModelMapper().map(measurementGoal1, MeasurementGoalDTO.class);

		MvcResult result = null;
		String newId = null;
		try {
			// create a measurement goal with rest controller
			result = this.mockMvc.perform(post("/measurementGoal").contentType(contentType).content(json(measurementGoalDTO1)))
					.andExpect(status().isCreated()).andReturn();

			// get id of the new MeasurementGoal
			JsonNode dto = new ObjectMapper().readTree(result.getResponse().getContentAsString());
			newId = dto.path("measurementGoalssDTO").path(0).path("metadata").path("id").asText();

		} catch (Exception e) {
			e.printStackTrace();
			fail("error in use of mock");
		}

		// check if it exists
		Assert.assertNotEquals("assert failed on creation", null, measurementGoalRepository.findOne(newId));

		// clean db
		measurementGoalRepository.delete(newId);
		assertTrue(true);

	}

	@Test
	public void testPut() throws IOException {
		// create a measurement goal
		measurementGoal1 = MeasurementGoal.randomMeasurementGoal();
		measurementGoal1.setId(null);
		measurementGoal1.setVersion(null);

		// insert in db
		measurementGoal1 = measurementGoalRepository.save(measurementGoal1);
		measurementGoalDTO1 = modelMapperFactory.getLooseModelMapper().map(measurementGoal1, MeasurementGoalDTO.class);
		// we can assert that every field is used in the same way during the
		// update
//		measurementGoalDTO1.setDescription("Updated Description");
		measurementGoalDTO1.getMetadata().setId(measurementGoal1.getId());
		String s = setIdOnJacksonJson(json(measurementGoalDTO1), measurementGoal1.getId());
		try {
			mockMvc.perform(put("/measurementGoal").contentType(contentType).content(s))
					.andExpect(status().isOk()).andReturn();
		} catch (IOException e) {
			e.printStackTrace();
			fail("error in put");
		} catch (Exception e) {
			e.printStackTrace();
			fail("error in put");
		}

		// get the new updated version
		measurementGoal2 = measurementGoalRepository.findOne(measurementGoal1.getId());
		// compare updated field
//		Assert.assertNotEquals("error in update", measurementGoal1.getDescription(), measurementGoal2.getDescription());

		// clean db
		measurementGoalRepository.delete(measurementGoal1.getId());
		assertTrue(true);
	}

	@Test
	public void getById() {
		// create a measurementGoal
		measurementGoal1 = MeasurementGoal.randomMeasurementGoal();
		measurementGoal1.setId(null);
		measurementGoal1.setVersion(null);

		// insert in db
		measurementGoal1 = measurementGoalRepository.save(measurementGoal1);
		measurementGoalDTO1 = modelMapperFactory.getLooseModelMapper().map(measurementGoal1, MeasurementGoalDTO.class);
		
		try {
			MvcResult result = mockMvc.perform(get("/measurementGoal?id="+ measurementGoal1.getId()).contentType(contentType).content(json(measurementGoalDTO1)))
					.andExpect(status().isOk()).andReturn();
			
			// get id of the new MeasurementGoal
			JsonNode dto = new ObjectMapper().readTree(result.getResponse().getContentAsString());
			Assert.assertEquals("error in get", measurementGoal1.getId(),dto.path("measurementGoalsDTO").path(0).path("metadata").path("id").asText());
		} catch (IOException e) {
			e.printStackTrace();
			fail("error in get");
		} catch (Exception e) {
			e.printStackTrace();
			fail("error in get");
		}finally{
			// clean db
			measurementGoalRepository.delete(measurementGoal1.getId());
			assertTrue(true);
		}
		
		
	}
	
	//due to something in Jackson Implementation, field id cannot be mapped
	private String setIdOnJacksonJson(String json, String id){
		return json.substring(0, 19) + "\"id\":\""+id+"\","+json.substring(19);
	}

}

