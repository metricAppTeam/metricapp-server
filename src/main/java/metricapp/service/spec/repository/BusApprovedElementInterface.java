package metricapp.service.spec.repository;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.core.JsonProcessingException;

import metricapp.entity.Element;
import metricapp.entity.external.PointerBus;
import metricapp.entity.external.RichPointerBus;
import metricapp.exception.BadInputException;
import metricapp.exception.BusException;



public interface BusApprovedElementInterface {

		public <T extends Element> Element sendApprovedElement(@Nonnull Element element, Class<T> clazz) 
				throws BadInputException, BusException, IOException ;

		public <T extends Element> Element getApprovedElement(@Nonnull PointerBus pointerBus, Class<T> clazz)throws BadInputException, BusException, IOException ; 

		public <T extends Element> ArrayList<T> getApprovedElements(@Nonnull PointerBus pointerBus, Class<T> clazz) throws BadInputException, BusException, IOException; 
		
		public RichPointerBus fromElementToRichPointerBus(Element element) throws JsonProcessingException ;

		public Element fromRichPointerBusToElement(RichPointerBus pointer) throws BusException, IOException ;

		public <T extends Element> T getLastApprovedElement(@Nonnull String id, Class<T> clazz) 
				throws BadInputException, BusException, IOException;
}
