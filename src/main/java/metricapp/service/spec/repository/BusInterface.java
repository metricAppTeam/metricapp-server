package metricapp.service.spec.repository;


import metricapp.dto.bus.PointerBusDTO;
import metricapp.exception.BusException;

public interface BusInterface {

    public String read(PointerBusDTO pointerBusDTO) throws BusException;


    public String update(PointerBusDTO pointerBusDTO) throws BusException;


    public String create(PointerBusDTO pointerBusDTO) throws BusException;


    public String rollback(PointerBusDTO pointerBusDTO) throws BusException;


    public String delete(PointerBusDTO pointerBusDTO) throws BusException;

}
