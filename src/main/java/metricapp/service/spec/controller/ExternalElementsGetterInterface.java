package metricapp.service.spec.controller;

import metricapp.dto.externalElements.*;
import metricapp.entity.external.*;
import metricapp.exception.BadInputException;
import metricapp.exception.BusException;
import metricapp.exception.NotFoundException;

import java.io.IOException;
import java.util.ArrayList;

public interface ExternalElementsGetterInterface {
    ExternalElementsDTO getMeasurementGoalExternalElements(String measurementGoalId) throws IOException, BusException, BadInputException;

    public<T,Z> ArrayList<T> fromArrayListToArrayListDTO(ArrayList<Z> input, Class<T>clazz);

    public AssumptionDTO getAssumptionByPointerBus(PointerBus pointerBus) throws IOException, BusException, NotFoundException;

    public ContextFactorDTO getContextFactorByPointerBus(PointerBus pointerBus) throws IOException, BusException, NotFoundException;

    public OrganizationalGoalDTO getOrganizationalGoalByPointerBus(PointerBus pointerBus) throws IOException, BusException, NotFoundException;

    public InstanceProjectDTO getInstanceProjectByPointerBus(PointerBus pointerBus) throws IOException, BusException, NotFoundException;

    public ArrayList<ContextFactorDTO> getAllContextFactors() throws BusException, IOException, BadInputException;
    
    public ArrayList<InstanceProjectDTO> getAllInstanceProjects() throws BusException, IOException, BadInputException;
    
    public ArrayList<OrganizationalGoalDTO> getAllOrganizationalGoals() throws BusException, IOException, BadInputException;
    
    public ArrayList<AssumptionDTO> getAllAssumptions() throws BusException, IOException, BadInputException;
}
