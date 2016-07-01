package metricapp.service.spec.repository;


import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import metricapp.entity.external.PointerBus;
import metricapp.entity.external.RichPointerBus;
import metricapp.exception.BusException;

public interface BusInterface {

    public ArrayList<String> read(PointerBus pointerBus) throws BusException, JsonProcessingException;


    public ArrayList<String> update(RichPointerBus pointerBus) throws BusException, JsonProcessingException;


    public ArrayList<String> create(RichPointerBus pointerBus) throws BusException, JsonProcessingException;


    public ArrayList<String> rollback(PointerBus pointerBus) throws BusException, JsonProcessingException;

    public ArrayList<String> delete(PointerBus pointerBus) throws BusException, JsonProcessingException;

}
