package metricapp.service.spec.repository;


import com.fasterxml.jackson.core.JsonProcessingException;
import metricapp.entity.external.PointerBus;
import metricapp.entity.external.RichPointerBus;
import metricapp.exception.BusException;

public interface BusInterface {

    public String read(PointerBus pointerBus) throws BusException, JsonProcessingException;


    public String update(RichPointerBus pointerBus) throws BusException, JsonProcessingException;


    public String create(RichPointerBus pointerBus) throws BusException, JsonProcessingException;


    public String rollback(PointerBus pointerBus) throws BusException, JsonProcessingException;

    public String delete(PointerBus pointerBus) throws BusException, JsonProcessingException;

}
