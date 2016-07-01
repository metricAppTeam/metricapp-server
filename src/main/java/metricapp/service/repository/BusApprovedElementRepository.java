package metricapp.service.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import metricapp.entity.Element;
import metricapp.entity.Entity;
import metricapp.entity.State;
import metricapp.entity.external.PointerBus;
import metricapp.entity.external.RichPointerBus;
import metricapp.entity.measurementGoal.MeasurementGoal;
import metricapp.entity.metric.Metric;
import metricapp.entity.question.Question;
import metricapp.exception.BadInputException;
import metricapp.exception.BusException;
import metricapp.service.spec.repository.BusApprovedElementInterface;
import metricapp.service.spec.repository.BusInterface;
import metricapp.utility.JacksonMapper;

/**
 * This repository offers method to send and grab our Elements to and from BUS.
 * <p>
 * Supported items:
 * <ul>
 * <li>Metric
 * <li>Question
 * <li>MeasurementGoal
 * </ul>
 * 
 * Bus Storage contains only Approved Elements, and only Approved Elements can
 * be sent to Bus.
 *
 */
@Service
public class BusApprovedElementRepository implements BusApprovedElementInterface {

	@Autowired
	private BusInterface busRepository;

	@Autowired
	private JacksonMapper mapper;
	
	@Autowired
	static private BusApprovedElementRepository instance;
	
	
	/**
	 * Spring implementation of Singleton Pattern
	 */
	public BusApprovedElementRepository(){
		instance = this;
	}
	
	/**
	 * this getter has to be used only when is not possible the IoD of Spring, like commands of State Transition Utils.
	 * @return
	 */
	public static BusApprovedElementRepository getInstance(){
		return instance;
	}

	/**
	 * This function send an approved Element like to Bus.
	 * There's a check for the state (must be Approved). It returns the updated Element.
	 * If the element has null busversion the element is created on bus, otherwise is updated.
	 * @param element the Element to send to bus
	 * @param clazz is the class of the element, like MeasurementGoal or Question
	 * @return the Element returned by the bus
	 * @throws BadInputException state not "Approved"
	 * @throws BusException error in interation with bus
	 * @throws IOException generic error mapping entities 
	 */
	public <T extends Element> Element sendApprovedElement(@Nonnull Element element, Class<T> clazz) 
			throws BadInputException, BusException, IOException {
		
		// state check control
		if (!element.getState().equals(State.Approved)) {
			throw new BadInputException("Element MUST be in state of Approved to be sent to bus");
		}
		
		String content;
		if(element.getVersionBus() == null){
			//send element to bus and receive it from bus
			content = busRepository.create(fromElementToRichPointerBus(element)).get(0);
		}else{
			content = busRepository.update(fromElementToRichPointerBus(element)).get(0);
		}
		//map received json to new element of the class clazz
		T el = mapper.fromJson(mapper.getMapper().readTree(content).get("payload").toString(), clazz);
		//set the updated version bus
		el.setVersionBus(mapper.getMapper().readTree(content).get("busVersion").asText());
		
		return el;
	}
	
	/**
	 * This function gathers Elements like MeasurementGoal, Metric and Question from the bus.
	 * If the pointerBus has null version, the last one is returned
	 * @param pointerBus the pointer of the entity requested
	 * @param clazz the class of the element to gather, like MeasurementGoal.class, Metric.class
	 * @return return the elements returned by the bus. No null check control implemented
	 * @throws BadInputException if the pointer is not correct
	 * @throws BusException error in interation with bus
	 * @throws IOException generic mapping error.
	 */
	public <T extends Element> T getApprovedElement(@Nonnull PointerBus pointerBus, Class<T> clazz) 
			throws BadInputException, BusException, IOException {
		
		//read from the bus
		String content = busRepository.read(pointerBus).get(0);

		//map received json to new element of the class clazz
		T el = mapper.fromJson(mapper.getMapper().readTree(content).get(0).get("payload").toString(), clazz);
		//set the correct version
		el.setVersionBus(mapper.getMapper().readTree(content).get(0).get("busVersion").asText());
		
		return el;
	}
	
	/**
	 * this function returns the last approved version of element with a id in the bus
	 * @param id
	 * @param clazz
	 * @return
	 * @throws BadInputException
	 * @throws BusException
	 * @throws IOException
	 */
	public <T extends Element> T getLastApprovedElement(@Nonnull String id, Class<T> clazz) 
			throws BadInputException, BusException, IOException {
		PointerBus pointer=new PointerBus();
		pointer.setInstance(id);
		return getApprovedElement(pointer, clazz);
	}
	
	/**
	 * This function gathers an Array of Elements like MeasurementGoal, Metric and Question from the bus.
	 * If the pointerBus has null version, the last one is returned
	 * @param pointerBus the pointer of the entity requested
	 * @param clazz the class of the element to gather, like MeasurementGoal.class, Metric.class
	 * @return return the elements returned by the bus. No null check control implemented
	 * @throws BadInputException if the pointer is not correct
	 * @throws BusException error in interation with bus
	 * @throws IOException generic mapping error.
	 */
	public <T extends Element> ArrayList<T> getApprovedElements(@Nonnull PointerBus pointerBus, Class<T> clazz) 
			throws BadInputException, BusException, IOException {
		
		//read from the bus
		String content = busRepository.read(pointerBus).get(0);
		
		//extract the tree of json
		JsonNode node = mapper.getMapper().readTree(content);
		
		//get an iterator
		Iterator<JsonNode> iterator =node.iterator();
		
		//create Array
		ArrayList<T> list = new ArrayList<T>();
		
		while(iterator.hasNext()){
			JsonNode actual = iterator.next();
			
			//map received json to new element of the class clazz
			T el = mapper.fromJson(actual.get("payload").toString(), clazz);
			//set the correct version
			el.setVersionBus(actual.get("busVersion").asText());
		}
		return list;
	}
	
	
	/**
	 * This method maps Element to RichPointerBus to send it to bus, with a create or a update.
	 * <p>
	 * TODO: change secure key in something safer (my idea is make a secret password in file /resource/application.properties. To generate this secret token we can do MD5(password XOR id)) In this way we have only to know the password, and not all the secret tokens.
	 * 
	 * @param element typically is of type MeasurementGoal, Metric or Question. Cast to Element is not necessary.
	 * @return RichPointerBus ready to be sent to bus
	 * @throws JsonProcessingException if the ObjectToString mapping is not possible
	 */
	public RichPointerBus fromElementToRichPointerBus(Element element) throws JsonProcessingException {
		RichPointerBus pointerBus;

		pointerBus = new RichPointerBus();
		pointerBus.setPayload(element);
		pointerBus.setBusTags(element.getTags());
		//TODO change this, tags must be useful to do queries. 
		pointerBus.setInstance(element.getId());
		// TODO change secure key in something safer (my idea is make a secret password in file /resource/application.properties. To generate this secret token we can do MD5(password XOR id)) In this way we have only to know the password, and not all the secret tokens.
		pointerBus.setObjIdLocalToPhase(element.getId());
		pointerBus.setTypeObj(element.getEntityType().name());

		return pointerBus;
	}

	/**
	 * this method gets from RichPointerBus, typically received from the bus after an Update or a create action,
	 * and maps it to an Element like MeasurementGoal, Metric, Question.
	 * This method provides to save fields like secretToken in Element
	 * @param pointer, typically received from bus. It Must contain fields getTypeObj and payload not null 
	 * @return the element, you have to cast it to your Element. Class type is described like Enum in elementReturned.getEntityType()
	 * @throws BusException if typeobject is not correct, or supported. Exception message contains the value that triggers exception
	 * @throws IOException if the StringToObject is not possible
	 */
	public Element fromRichPointerBusToElement(RichPointerBus pointer) throws BusException, IOException {

		Element element;
		switch (Entity.valueOf(pointer.getTypeObj())) {

		case MeasurementGoal:
			element = (MeasurementGoal) pointer.getPayload();
			break;
		case Metric:
			element = (Metric) pointer.getPayload();
			break;
		case Question:
			element = (Question) pointer.getPayload();
			break;
		default:
			throw new BusException("Bus sent entity of type: " + pointer.getTypeObj());
		}

		element.setSecretToken(pointer.getObjIdLocalToPhase());
		element.setEntityType(Entity.valueOf(pointer.getTypeObj()));
		element.setTags(pointer.getBusTags());
		element.setVersionBus(pointer.getBusVersion());
		return element;
	}
}
