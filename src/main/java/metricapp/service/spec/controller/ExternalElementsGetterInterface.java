package metricapp.service.spec.controller;

import metricapp.dto.externalElements.*;
import metricapp.entity.external.*;
import metricapp.exception.BusException;
import metricapp.exception.NotFoundException;

import java.io.IOException;
import java.util.ArrayList;

public interface ExternalElementsGetterInterface {
    ExternalElementsDTO getMeasurementGoalExternalElements(String measurementGoalId) throws IOException, BusException;

    public<T,Z> ArrayList<T> fromArrayListToArrayListDTO(ArrayList<Z> input, Class<T>clazz);

    public AssumptionDTO getAssumptionByPointerBus(PointerBus pointerBus) throws IOException, BusException, NotFoundException;

    public ContextFactorDTO getContextFactorByPointerBus(PointerBus pointerBus) throws IOException, BusException, NotFoundException;

    public OrganizationalGoalDTO getOrganizationalGoalByPointerBus(PointerBus pointerBus) throws IOException, BusException, NotFoundException;

    public InstanceProjectDTO getInstanceProjectByPointerBus(PointerBus pointerBus) throws IOException, BusException, NotFoundException;

}
