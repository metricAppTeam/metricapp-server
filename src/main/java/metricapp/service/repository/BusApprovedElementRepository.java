package metricapp.service.repository;

import java.io.IOException;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

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

	/**
	 * This function send an approved Element like to Bus.
	 * There's a check for the state (must be Approved). It returns the updated MeasurementGoal
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

		Element result;

		// case: element is new, it is the first time it's approved, so these
		// fields are empty
		if (element.getVersionBus() == null && element.getSecretToken() == null) {
			result = mapper.fromJson(busRepository.create(fromElementToRichPointerBus(element)),
					clazz);
			return result;
		}
		//case: element is not new, update it
		if (element.getVersionBus() == null && element.getSecretToken() == null) {
			result = mapper.fromJson(busRepository.create(fromElementToRichPointerBus(element)),
					clazz);
			return result;
		}
		throw new BusException("error in sending Element");
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
	public <T extends Element> Element getApprovedElement(@Nonnull PointerBus pointerBus, Class<T> clazz) 
			throws BadInputException, BusException, IOException {

		T element = mapper.fromJson(busRepository.read(pointerBus), clazz);
		
		return element;
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
		pointerBus.setPayload(mapper.toJson(element));
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
			element = mapper.fromJson(pointer.getPayload(), MeasurementGoal.class);
			break;
		case Metric:
			element = mapper.fromJson(pointer.getPayload(), Metric.class);
			break;
		case Question:
			element = mapper.fromJson(pointer.getPayload(), Question.class);
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
