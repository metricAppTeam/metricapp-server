package metricapp.service.controller;

import metricapp.dto.externalElements.*;
import metricapp.entity.external.*;
import metricapp.entity.measurementGoal.MeasurementGoal;
import metricapp.exception.BusException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.controller.ExternalElementsGetterInterface;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.repository.ExternalElementsRepositoryInterface;
import metricapp.service.spec.repository.MeasurementGoalRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

@Service
public class ExternalElementsGetterController implements ExternalElementsGetterInterface{


    @Autowired
    private MeasurementGoalRepository measurementGoalRepository;

    @Autowired
    private ModelMapperFactoryInterface modelMapperFactory;

    @Autowired
    private ExternalElementsRepositoryInterface repository;


    /**
     * this method fills the return dto with every external informations that are related to
     * a Measurement Goal. It's useful to show the metricator the view of that Measurement Goal
     *
     * @param measurementGoalId
     * @return ExternalElementsDTO a dto that contains organizational goal,
     * context factors and assumptions of that goal
     */
    public ExternalElementsDTO getMeasurementGoalExternalElements(String measurementGoalId) throws IOException, BusException {

        //get measurement goal
        MeasurementGoal measurementGoal = this.measurementGoalRepository.findById(measurementGoalId);

        //get assumptions, contextfactors and organizational goal
        ArrayList<Assumption> assumptions = repository.getAssumptionsByPointerBusList(measurementGoal.getAssumptions());
        ArrayList<ContextFactor> contextFactors = repository.getContextFactorsByPointerBusList(measurementGoal.getContextFactors());;
        OrganizationalGoal organizationalGoal = repository.getOrganizationalGoalByIdAndVersion(measurementGoal.getOrganizationalGoalId().getObjIdLocalToPhase(), measurementGoal.getOrganizationalGoalId().getBusVersion());;
        InstanceProject instanceProject = repository.getInstanceProjectByIdAndVersion(organizationalGoal.getInstanceProjectId(), null);

        //new Array
        ArrayList<AssumptionDTO> assumptionDTOs ;
        ArrayList<ContextFactorDTO> contextFactorDTOs ;

        //conversion ArrayList<Assumption> to ArrayList<AssumptionDTO> -- see ModelMapper Doc
        assumptionDTOs = fromArrayListToArrayListDTO(assumptions,AssumptionDTO.class);

        //conversion ArrayList<ContextFactor> to ArrayList<ContextFactorDTO> -- see ModelMapper Doc
        contextFactorDTOs = fromArrayListToArrayListDTO(contextFactors,ContextFactorDTO.class );

        //get modelMapper instance
        ModelMapper modelMapper = modelMapperFactory.getLooseModelMapper();

        //conversion of organizational goal
        OrganizationalGoalDTO organizationalGoalDTO = modelMapper.map(organizationalGoal, OrganizationalGoalDTO.class);

        //conversion of instance project
        InstanceProjectDTO instanceProjectDTO = modelMapper.map(instanceProject, InstanceProjectDTO.class);

        //fill ExternalElementsDTO
        ExternalElementsDTO dto = new ExternalElementsDTO();
        dto.setAssumptions(assumptionDTOs);
        dto.setContextFactors(contextFactorDTOs);
        dto.setInstanceProject(instanceProjectDTO);
        dto.setMeasurementGoalId(measurementGoalId);
        dto.setOrganizationalGoal(organizationalGoalDTO);

        return dto;
    }

    /**
     * This function converts ArrayList of entities in ArrayList of DTO, and viceversa.
     * Implementation is derived by ModelMapper Documentation.
     * <p>
     * e.g. fromArrayListToArrayListDTO(listOfAssumption, AssumptionDTO.class)
     * @param input is the arraylist to map
     * @param clazz is the parameter of the new arrayList to fill up
     * @param <T> T is the type of parameter for the new array
     * @param <Z> Z is the type of parameter for the old array
     * @return
     */
    public<T,Z> ArrayList<T> fromArrayListToArrayListDTO(ArrayList<Z> input, Class<T>clazz){

        //get modelMapper instance
        ModelMapper modelMapper = modelMapperFactory.getLooseModelMapper();

        //-- see ModelMapper Doc
        Type listType = new TypeToken<ArrayList<T>>() {}.getType();
        ArrayList<T> dTOs = modelMapper.map(input, listType);

        return dTOs;
    }

    public AssumptionDTO getAssumptionByPointerBus(PointerBus pointerBus) throws IOException, BusException, NotFoundException {
        try {
            return modelMapperFactory.getLooseModelMapper().map(
                    repository.getAssumptionByIdAndVersion(pointerBus.getObjIdLocalToPhase(), pointerBus.getBusVersion()),
                    AssumptionDTO.class);
        }catch(Exception e){
            throw new NotFoundException();
        }
    }

    public ContextFactorDTO getContextFactorByPointerBus(PointerBus pointerBus) throws IOException, BusException, NotFoundException {
        try {
            return modelMapperFactory.getLooseModelMapper().map(
                    repository.getContextFactorByIdAndVersion(pointerBus.getObjIdLocalToPhase(), pointerBus.getBusVersion()),
                    ContextFactorDTO.class);
        }catch(Exception e){
            throw new NotFoundException();
        }
    }

    public OrganizationalGoalDTO getOrganizationalGoalByPointerBus(PointerBus pointerBus) throws IOException, BusException, NotFoundException {
        try {
            return modelMapperFactory.getLooseModelMapper().map(
                    repository.getOrganizationalGoalByIdAndVersion(pointerBus.getObjIdLocalToPhase(), pointerBus.getBusVersion()),
                    OrganizationalGoalDTO.class);
        }catch(Exception e){
            throw new NotFoundException();
        }
    }

    public InstanceProjectDTO getInstanceProjectByPointerBus(PointerBus pointerBus) throws IOException, BusException, NotFoundException {
        try {
            return modelMapperFactory.getLooseModelMapper().map(
                    repository.getInstanceProjectByIdAndVersion(pointerBus.getObjIdLocalToPhase(), pointerBus.getBusVersion()),
                    InstanceProjectDTO.class);
        }catch(Exception e){
            throw new NotFoundException();
        }
    }


}
