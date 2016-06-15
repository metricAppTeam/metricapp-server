package metricapp.service.spec.repository;


import com.fasterxml.jackson.core.JsonProcessingException;
import metricapp.dto.bus.PointerBusDTO;
import metricapp.exception.BusException;

public interface BusInterface {

    public String read(PointerBusDTO pointerBusDTO) throws BusException, JsonProcessingException;


    public String update(PointerBusDTO pointerBusDTO) throws BusException, JsonProcessingException;


    public String create(PointerBusDTO pointerBusDTO) throws BusException, JsonProcessingException;


    public String rollback(PointerBusDTO pointerBusDTO) throws BusException, JsonProcessingException;

    public String delete(PointerBusDTO pointerBusDTO) throws BusException, JsonProcessingException;

}
