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
import java.lang.reflect.InvocationTargetException;
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
import metricapp.dto.metric.MetricDTO;
import metricapp.entity.State;
import metricapp.entity.metric.Metric;
import metricapp.service.spec.repository.MetricRepository;
import metricapp.utility.ModelMapperFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BootApplication.class)
@WebAppConfiguration
public class MetricRestControllerTest {

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

		Assert.assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}
	
	private MockMvc mockMvc;

	@Autowired
	private MetricRepository metricRepository;

	@Autowired
	private ModelMapperFactory modelMapperFactory;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MetricDTO metricDTO1;
	private Metric metric1;
	private Metric metric2;
	private Metric metric3;

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
		// create a metric
		metric1 = new Metric();
		try {
			metric1.randomAttributes();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		metric1.setId(null);
		// set state to suspended, only a suspended entity could be deleted
		metric1.setState(State.Suspended);
		// insert metric in db
		metric1 = metricRepository.save(metric1);
		// check if the instance was correctly in created in db
		metric2 = metricRepository.findMetricById(metric1.getId());
		if (metric2 == null) {
			fail("error in insert, db connection fail");
		}

		// delete it from rest controller
		try {
			this.mockMvc.perform(delete("/metric?id=" + metric2.getId())).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
			fail("error in use of mock");
		}

		// check if the delete was done
		metric3 = metricRepository.findMetricById(metric2.getId());
		assertEquals(metric3, null);
	}

	@Test
	public void testPost() {
		// create a metric
		metric1 = new Metric();
		try {
			metric1.randomAttributes();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		metric1.setId(null);
		metric1.setState(State.Created);
		metric1.setVersion(null);
		// convert to dto
		metricDTO1 = modelMapperFactory.getLooseModelMapper().map(metric1, MetricDTO.class);

		MvcResult result = null;
		String newId = null;
		try {
			// create a metric with rest controller
			result = this.mockMvc.perform(post("/metric").contentType(contentType).content(json(metricDTO1)))
					.andExpect(status().isCreated()).andReturn();

			// get id of the new Metric
			JsonNode dto = new ObjectMapper().readTree(result.getResponse().getContentAsString());
			newId = dto.path("metricsDTO").path(0).path("metadata").path("id").asText();

		} catch (Exception e) {
			e.printStackTrace();
			fail("error in use of mock");
		}

		// check if it exists
		Assert.assertNotEquals("assert failed on creation", null, metricRepository.findMetricById(newId));

		// clean db
		metricRepository.delete(newId);
		assertTrue(true);

	}

	@Test
	public void testPut() throws IOException {
		// create a metric
		metric1 = new Metric();
		try {
			metric1.randomAttributes();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		metric1.setId(null);
		metric1.setVersion(null);

		// insert in db
		metric1 = metricRepository.save(metric1);
		metricDTO1 = modelMapperFactory.getLooseModelMapper().map(metric1, MetricDTO.class);
		// we can assert that every field is used in the same way during the
		// update
		metricDTO1.setDescription("Updated Description");
		metricDTO1.getMetadata().setId(metric1.getId());
		String s = setIdOnJacksonJson(json(metricDTO1), metric1.getId());
		try {
			mockMvc.perform(put("/metric").contentType(contentType).content(s))
					.andExpect(status().isOk()).andReturn();
		} catch (IOException e) {
			e.printStackTrace();
			fail("error in put");
		} catch (Exception e) {
			e.printStackTrace();
			fail("error in put");
		}

		// get the new updated version
		metric2 = metricRepository.findMetricById(metric1.getId());
		// compare updated field
		Assert.assertNotEquals("error in update", metric1.getDescription(), metric2.getDescription());

		// clean db
		metricRepository.delete(metric1.getId());
		assertTrue(true);
	}

	@Test
	public void getById() {
		// create a metric
		metric1 = new Metric();
		try {
			metric1.randomAttributes();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		metric1.setId(null);
		metric1.setVersion(null);

		// insert in db
		metric1 = metricRepository.save(metric1);
		metricDTO1 = modelMapperFactory.getLooseModelMapper().map(metric1, MetricDTO.class);
		
		try {
			MvcResult result = mockMvc.perform(get("/metric?id="+ metric1.getId()).contentType(contentType).content(json(metricDTO1)))
					.andExpect(status().isOk()).andReturn();
			
			// get id of the new Metric
			JsonNode dto = new ObjectMapper().readTree(result.getResponse().getContentAsString());
			Assert.assertEquals("error in get", metric1.getId(),dto.path("metricsDTO").path(0).path("metadata").path("id").asText());
		} catch (IOException e) {
			e.printStackTrace();
			fail("error in get");
		} catch (Exception e) {
			e.printStackTrace();
			fail("error in get");
		}finally{
			// clean db
			metricRepository.delete(metric1.getId());
			assertTrue(true);
		}
		
		
	}
	
	//due to something in Jackson Implementation, field id cannot be mapped
	private String setIdOnJacksonJson(String json, String id){
		return json.substring(0, 19) + "\"id\":\""+id+"\","+json.substring(19);
	}

}
