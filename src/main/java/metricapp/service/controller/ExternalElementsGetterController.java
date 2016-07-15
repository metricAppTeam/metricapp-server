package metricapp.service.controller;

import metricapp.dto.externalElements.*;
import metricapp.dto.metric.MetricDTO;
import metricapp.dto.question.QuestionDTO;
import metricapp.entity.external.*;
import metricapp.entity.measurementGoal.MeasurementGoal;
import metricapp.exception.BadInputException;
import metricapp.exception.BusException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.controller.ExternalElementsGetterInterface;
import metricapp.service.spec.controller.MetricCRUDInterface;
import metricapp.service.spec.controller.QuestionCRUDInterface;
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
import java.util.List;

@Service
public class ExternalElementsGetterController implements ExternalElementsGetterInterface{


    @Autowired
    private MeasurementGoalRepository measurementGoalRepository;

    @Autowired
    private ModelMapperFactoryInterface modelMapperFactory;

    @Autowired
    private ExternalElementsRepositoryInterface repository;
    
    @Autowired
    private MetricCRUDInterface metricController;
    
    @Autowired
    private QuestionCRUDInterface questionController;
    

    /**
     * this method fills the return dto with every external informations that are related to
     * a Measurement Goal. It's useful to show the metricator the view of that Measurement Goal
     *
     * @param measurementGoalId
     * @return ExternalElementsDTO a dto that contains organizational goal,
     * context factors and assumptions of that goal
     * @throws BadInputException 
     */
    @Override
    public ExternalElementsDTO getMeasurementGoalExternalElements(String measurementGoalId) throws IOException, BusException, BadInputException {

        //get measurement goal
        MeasurementGoal measurementGoal = this.measurementGoalRepository.findById(measurementGoalId);

        //get assumptions, contextfactors and organizational goal
        ArrayList<Assumption> assumptions = repository.getAssumptionsByPointerBusList(measurementGoal.getAssumptions());
        ArrayList<ContextFactor> contextFactors = repository.getContextFactorsByPointerBusList(measurementGoal.getContextFactors());;
        OrganizationalGoal organizationalGoal = repository.getOrganizationalGoalByIdAndVersion(measurementGoal.getOrganizationalGoalId().getInstance(), measurementGoal.getOrganizationalGoalId().getBusVersion());;
        InstanceProject instanceProject = repository.getInstanceProjectByIdAndVersion(organizationalGoal.getInstanceProjectId(), null);
        
        //get metrics and questions
        ArrayList<MetricDTO> metricsDTO =metricController.getMetricsByPointerBusList(measurementGoal.getMetrics());
        ArrayList<QuestionDTO> questionsDTO = questionController.getQuestionsByPointerBusList(measurementGoal.getQuestions());
        
        //new Array
        ArrayList<AssumptionDTO> assumptionDTOs ;
        ArrayList<ContextFactorDTO> contextFactorDTOs ;

        //conversion ArrayList<Assumption> to ArrayList<AssumptionDTO> -- see ModelMapper Doc
        assumptionDTOs = fromArrayListToAssumptionArrayListDTO(assumptions);

        //conversion ArrayList<ContextFactor> to ArrayList<ContextFactorDTO> -- see ModelMapper Doc
        contextFactorDTOs = fromArrayListToContextFactorArrayListDTO(contextFactors );

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
        dto.setMetrics(metricsDTO);
        dto.setQuestions(questionsDTO);

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
    @Override
    public<Z,T> ArrayList<T> fromArrayListToArrayListDTO(ArrayList<Z> input, Class<T>clazz){

        //get modelMapper instance
        ModelMapper modelMapper = modelMapperFactory.getLooseModelMapper();

        //-- see ModelMapper Doc
        Type listType = new TypeToken<List<T>>() {}.getType();
        ArrayList<T> dTOs = modelMapper.map(input, listType);
        input=null;
        System.out.println(" to" + clazz.getName());
        System.out.println(dTOs.toString());

        return dTOs;
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
    @Override
    public<Z> ArrayList<AssumptionDTO> fromArrayListToAssumptionArrayListDTO(ArrayList<Z> input){

        //get modelMapper instance
        ModelMapper modelMapper = modelMapperFactory.getLooseModelMapper();

        //-- see ModelMapper Doc
        Type listType = new TypeToken<List<AssumptionDTO>>() {}.getType();
        ArrayList<AssumptionDTO> dTOs = modelMapper.map(input, listType);
        input=null;
        System.out.println(dTOs.toString());

        return dTOs;
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
    @Override
    public<Z> ArrayList<ContextFactorDTO> fromArrayListToContextFactorArrayListDTO(ArrayList<Z> input){

        //get modelMapper instance
        ModelMapper modelMapper = modelMapperFactory.getLooseModelMapper();

        //-- see ModelMapper Doc
        Type listType = new TypeToken<List<ContextFactorDTO>>() {}.getType();
        ArrayList<ContextFactorDTO> dTOs = modelMapper.map(input, listType);
        input=null;
        return dTOs;
    }
    
    @Override
    public AssumptionDTO getAssumptionByPointerBus(PointerBus pointerBus) throws IOException, BusException, NotFoundException {
        try {
            return modelMapperFactory.getLooseModelMapper().map(
                    repository.getAssumptionByIdAndVersion(pointerBus.getInstance(), pointerBus.getBusVersion()),
                    AssumptionDTO.class);
        }catch(Exception e){
            throw new NotFoundException();
        }
    }
    @Override
    public ContextFactorDTO getContextFactorByPointerBus(PointerBus pointerBus) throws IOException, BusException, NotFoundException {
        try {
            return modelMapperFactory.getLooseModelMapper().map(
                    repository.getContextFactorByIdAndVersion(pointerBus.getInstance(), pointerBus.getBusVersion()),
                    ContextFactorDTO.class);
        }catch(Exception e){
            throw new NotFoundException();
        }
    }
    @Override
    public OrganizationalGoalDTO getOrganizationalGoalByPointerBus(PointerBus pointerBus) throws IOException, BusException, NotFoundException {
        try {
            return modelMapperFactory.getLooseModelMapper().map(
                    repository.getOrganizationalGoalByIdAndVersion(pointerBus.getInstance(), pointerBus.getBusVersion()),
                    OrganizationalGoalDTO.class);
        }catch(Exception e){
            throw new NotFoundException();
        }
    }
    @Override
    public InstanceProjectDTO getInstanceProjectByPointerBus(PointerBus pointerBus) throws IOException, BusException, NotFoundException {
        try {
            return modelMapperFactory.getLooseModelMapper().map(
                    repository.getInstanceProjectByIdAndVersion(pointerBus.getInstance(), pointerBus.getBusVersion()),
                    InstanceProjectDTO.class);
        }catch(Exception e){
            throw new NotFoundException();
        }
    }
    @Override
    public ArrayList<ContextFactorDTO> getAllContextFactors() throws BusException, IOException, BadInputException{
    	return fromArrayListToArrayListDTO(repository.getAllContextFactors(), ContextFactorDTO.class);
    }
    @Override
    public ArrayList<InstanceProjectDTO> getAllInstanceProjects() throws BusException, IOException, BadInputException{
    	return fromArrayListToArrayListDTO(repository.getAllInstanceProjects(), InstanceProjectDTO.class);
    }
    @Override
    public ArrayList<OrganizationalGoalDTO> getAllOrganizationalGoals() throws BusException, IOException, BadInputException{
    	return fromArrayListToArrayListDTO(repository.getAllOrganizationalGoals(), OrganizationalGoalDTO.class);
    }
    @Override
    public ArrayList<AssumptionDTO> getAllAssumptions() throws BusException, IOException, BadInputException{
    	return fromArrayListToArrayListDTO(repository.getAllAssumptions(), AssumptionDTO.class);
    }
}
