package metricapp.service.spec.repository;


import com.fasterxml.jackson.core.JsonProcessingException;
import metricapp.entity.external.PointerBus;
import metricapp.exception.BusException;

public interface BusInterface {

    public String read(PointerBus pointerBusDTO) throws BusException, JsonProcessingException;


    public String update(PointerBus pointerBusDTO) throws BusException, JsonProcessingException;


    public String create(PointerBus pointerBusDTO) throws BusException, JsonProcessingException;


    public String rollback(PointerBus pointerBusDTO) throws BusException, JsonProcessingException;

    public String delete(PointerBus pointerBusDTO) throws BusException, JsonProcessingException;

}
