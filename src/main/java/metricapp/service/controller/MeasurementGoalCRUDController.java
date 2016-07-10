package metricapp.service.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.Data;
import metricapp.dto.measurementGoal.MeasurementGoalCrudDTO;
import metricapp.dto.measurementGoal.MeasurementGoalDTO;
import metricapp.entity.Entity;
import metricapp.entity.State;
import metricapp.entity.measurementGoal.MeasurementGoal;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.controller.MeasurementGoalCRUDInterface;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.repository.MeasurementGoalRepository;
import metricapp.service.spec.repository.MetricRepository;
import metricapp.utility.stateTransitionUtils.AbstractStateTransitionFactory;

@Data
@Service("MeasurementGoalCRUDController")
public class MeasurementGoalCRUDController implements MeasurementGoalCRUDInterface{

	@Autowired
	private MeasurementGoalRepository measurementGoalRepository;
	
	@Autowired
	private MetricRepository metricRepository;

	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory;
		
	private boolean debug = false;
	
	private MeasurementGoalDTO measurementGoalToDTO(MeasurementGoal goal){
		ModelMapper modelMapper = modelMapperFactory.getStandardModelMapper();
		//modelMapper.addMappings(new MeasurementGoalMap());
		MeasurementGoalDTO dto = modelMapper.map(goal, MeasurementGoalDTO.class);

		return dto;
	}

	@Override
	public MeasurementGoalCrudDTO getMeasurementGoalById(String id) throws BadInputException, NotFoundException{
		if (id == null) {
			throw new BadInputException("MeasurementGoal id cannot be null");
		}
		MeasurementGoal measurementGoal = measurementGoalRepository.findById(id);
		if (measurementGoal == null) {
			throw new NotFoundException("MeasurementGoal with id " + id + "is not available");
		}
		MeasurementGoalCrudDTO dto = new MeasurementGoalCrudDTO();
		dto.setRequest("MeasurementGoal, id=" + id);
		dto.addMeasurementGoalToList(measurementGoalToDTO(measurementGoal));
		return dto;
	}
	
	@Override
	public long countMeasurementGoalByState(String state, String userId) throws BadInputException, NotFoundException{
		if (state == null) {
			throw new BadInputException("State cannot be null");
		}
		return measurementGoalRepository.countByStateAndMetricatorId(State.valueOf(state), userId);
	}
	
	@Override
	public MeasurementGoalCrudDTO getMeasurementGoalByIdAndLastApprovedVersion(String id) throws BadInputException, NotFoundException {
		if (id == null) {
			throw new BadInputException("MeasurementGoal id cannot be null");
		}
		// TODO get from bus
		//MeasurementGoal last =busApprovedElementRepository.getLastApprovedElement(id, MeasurementGoal.class); 
		MeasurementGoal measurementGoal = null;
		if (measurementGoal == null) {
			throw new NotFoundException("Approved MeasurementGoal with id " + id + "is not available");
		}
		
		@SuppressWarnings("unused")
		MeasurementGoalCrudDTO dto = new MeasurementGoalCrudDTO();
		dto.setRequest("MeasurementGoal, id=" + id + ";state=Approved");
		dto.addMeasurementGoalToList(measurementGoalToDTO(measurementGoal));
		return dto;
	}
	
//	@Override
//	public MeasurementGoalDTO getMeasurementGoal(MeasurementGoalDTO dto) throws BadInputException, NotFoundException{
//		return measurementGoalToDTO(getMeasurementGoalById(dto.getId()));
//	}
	

	@Override
	public MeasurementGoalCrudDTO getMeasurementGoalByIdAndVersion(String id, String version)
			throws BadInputException, NotFoundException {
		if (id == null || version == null) {
			throw new BadInputException("Metric id,version cannot be null");
		}
		MeasurementGoal measurementGoal = measurementGoalRepository.findByIdAndVersion(id, version);
		if (measurementGoal == null) {
			throw new NotFoundException("Metric with id " + id + " and version " + version + "is not available");
		}
		MeasurementGoalCrudDTO dto = new MeasurementGoalCrudDTO();
		dto.addMeasurementGoalToList(modelMapperFactory.getStandardModelMapper().map(measurementGoal, MeasurementGoalDTO.class));
		return dto;
	}
	
	@Override
	public MeasurementGoalCrudDTO getMeasurementGoalByQuestionerId(String questionerId) throws BadInputException, NotFoundException{
		
		if(questionerId == null){
			throw new BadInputException("Questioner id cannot be null");
		}
		
		MeasurementGoalCrudDTO measurementGoalCrudDTO = new MeasurementGoalCrudDTO();
		
		Iterator<MeasurementGoal> measurementGoalIter = measurementGoalRepository.findByQuestionerId(questionerId).iterator();
		
		if(!measurementGoalIter.hasNext()){
			throw new NotFoundException("There are not measurement goals for the questioner");
		}
		
		while(measurementGoalIter.hasNext()){
			measurementGoalCrudDTO.addMeasurementGoalToList(modelMapperFactory.getLooseModelMapper().map(measurementGoalIter.next(), MeasurementGoalDTO.class));
		}	
		
		return measurementGoalCrudDTO;
	}
	
	@Override
	public MeasurementGoalCrudDTO getMeasurementGoalByUser(String userId) throws NotFoundException, BadInputException {
		if (userId == null) {
			throw new BadInputException("MeasurementGoal userId cannot be null");
		}
		ArrayList<MeasurementGoal> measurementGoals = measurementGoalRepository.findByMetricatorId(userId);
		if (measurementGoals.size() == 0) {
			throw new NotFoundException("User " + userId + " has no Measurement Goals");
		}
		
		MeasurementGoalCrudDTO dto = new MeasurementGoalCrudDTO();
		dto.setRequest("MeasurementGoal of " + userId);
		Iterator<MeasurementGoal> measurementGoalIter = measurementGoals.iterator();
		while (measurementGoalIter.hasNext()) {
			dto.addMeasurementGoalToList(modelMapperFactory.getStandardModelMapper().map(measurementGoalIter.next(), MeasurementGoalDTO.class));
		}
		
		return dto;
	}
	
	@Override
	public MeasurementGoalCrudDTO getMeasurementGoalByState(String state, String userId) throws NotFoundException, BadInputException {
		if (state == null) {
			throw new BadInputException("MeasurementGoal state cannot be null");
		}
		ArrayList<MeasurementGoal> measurementGoals = measurementGoalRepository.findByStateAndMetricatorId(State.valueOf(state),userId);
		if (measurementGoals.size() == 0) {
			throw new NotFoundException("State " + state + " has no Measurement Goals");
		}
		
		MeasurementGoalCrudDTO dto = new MeasurementGoalCrudDTO();
		dto.setRequest("MeasurementGoal of " + userId);
		Iterator<MeasurementGoal> measurementGoalIter = measurementGoals.iterator();
		while (measurementGoalIter.hasNext()) {
			dto.addMeasurementGoalToList(modelMapperFactory.getStandardModelMapper().map(measurementGoalIter.next(), MeasurementGoalDTO.class));
		}
		
		return dto;
	}
	
	public MeasurementGoal createMeasurementGoal(MeasurementGoal goal){
		return measurementGoalRepository.save(goal);
	}
	
	public MeasurementGoalCrudDTO createMeasurementGoal(MeasurementGoalDTO dto) throws BadInputException{
		
		if (dto.getMetadata().getCreatorId() == null) {
			throw new BadInputException("Bad Input");
		}
		if (dto.getMetadata().getId() != null) {
			throw new BadInputException("New Measurement Goal cannot have ID");
		}
//		if (dto.getInterpretationModel() == null) {
//			throw new BadInputException("New Measurement Goal must have an Interpretation Model");
//		}
		
//		if (dto.getOrganizationalGoalId() == null){
//			throw new BadInputException("New Measurement Goal must have a link to an Organizational Goal");
//		}
		
//		if (dto.getContextFactorIdList() == null){
//			throw new BadInputException("New Measurement Goal must have a Context Factor list");
//		}
//		if (dto.getAssumptionIdList() == null) {
//			throw new BadInputException("New Measurement Goal must have an Assumption List");
//		}
//		if (dto.getMetricIdList() == null){
//			throw new BadInputException("New Measurement Goal must have at least a metric");
//		}
//		if (dto.getQuestionIdList() == null){
//			throw new BadInputException("New Measurement Goal must have at least a question");
//		}
		
		//TODO Check if id of metricator exists into the db
		//TODO Check if questioner exists into the db
		
//		if (dto.getMetadata().getState() != State.Created){
//			throw new BadInputException("MeasurementGoal must be in CREATED state");
//		}
		
		dto.getMetadata().setCreationDate(LocalDate.now());
		dto.getMetadata().setLastVersionDate(LocalDate.now());
		
		if(debug){
			System.out.println("\n\n--- Using Model mapper ---\n\n");
			
			System.out.println("id: " + dto.getMetadata().getId() + "\n");
			System.out.println("object: " + dto.getObject() + "\n");
			System.out.println("viewPoint: " + dto.getViewPoint() + "\n");
			System.out.println("qualityFocus: " + dto.getFocus() + "\n");
			System.out.println("releaseNote: " + dto.getMetadata().getReleaseNote() + "\n");
			System.out.println("purpose: " + dto.getPurpose() + "\n");
			System.out.println("version: " + dto.getMetadata().getVersion() + "\n");
			System.out.println("creationDate: " + dto.getMetadata().getCreationDate() + "\n");
			System.out.println("lastVersionDate: " + dto.getMetadata().getLastVersionDate() + "\n");
			System.out.println("metricsIdList: " + dto.getMetrics() + "\n");
			System.out.println("questionIdList: " + dto.getQuestions() + "\n");
			System.out.println("metricatorCredentialUsername: "+dto.getMetricatorId() + "\n");
		}
			/*
			PropertyMap<MeasurementGoalDTO, MeasurementGoal> measurementGoalDTOMap = new PropertyMap<MeasurementGoalDTO, MeasurementGoal>() {
			  protected void configure() {
			    map().getInterpretationModel().setFunctionJavascript(source.getInterpretationModel().getFunctionJavascript());
			    map(source.getAddress().city, destination.city);
			  }
			};

			modelMapper.addMappings(personMap);
			*/
		
		ModelMapper modelMapper = modelMapperFactory.getStandardModelMapper();
		MeasurementGoal goal = modelMapper.map(dto, MeasurementGoal.class);
		
		goal.setEntityType(Entity.MeasurementGoal);
		goal.setVersion("0");
		
		if(debug){
			System.out.println("\n\n--- Model mapper in use ---\n\n");
			
			System.out.println("id: " + goal.getId() + "\n");
			System.out.println("object: " + goal.getObject() + "\n");
			System.out.println("purpose: " + goal.getPurpose() + "\n");
			System.out.println("qualityFocus: " + goal.getQualityFocus() + "\n");
			System.out.println("releaseNote: " + goal.getReleaseNote() + "\n");
			System.out.println("version: " + goal.getVersion() + "\n");
			System.out.println("viewPoint: " + goal.getViewPoint() + "\n");
			System.out.println("creationDate: " + goal.getCreationDate() + "\n");
			System.out.println("lastVersionDate: " + goal.getLastVersionDate() + "\n");
			System.out.println("metricsIdList: " + goal.getMetrics() + "\n");
			System.out.println("questionIdList: " + goal.getQuestions() + "\n");
			System.out.println("tags: " + goal.getTags() + "\n");
			System.out.println("organizationalGoalId: " + goal.getOrganizationalGoalId() + "\n");
			
			System.out.println("functionJavaScript: " + goal.getInterpretationModel().getFunctionJavascript() +  "\n");
			System.out.println("queryNoSQL: " + goal.getInterpretationModel().getQueryNoSQL() + "\n");
			
			System.out.println("metricatorId: " + goal.getMetricatorId() + "\n");
		}		
		MeasurementGoalCrudDTO dtoCrud = new MeasurementGoalCrudDTO();
		dtoCrud.setRequest("create MeasurementGoal");
		dtoCrud.addMeasurementGoalToList(measurementGoalToDTO(createMeasurementGoal(goal)));
		return dtoCrud;
	}
	
	@Override
	public MeasurementGoal updateMeasurementGoal(MeasurementGoal goal){
		return measurementGoalRepository.save(goal);		
	}
	@Override
	public MeasurementGoalCrudDTO updateMeasurementGoal(MeasurementGoalDTO dto) throws DBException, NotFoundException, BadInputException, IllegalStateTransitionException{
		
		ModelMapper modelMapper = modelMapperFactory.getStandardModelMapper();
		MeasurementGoal newGoal = modelMapper.map(dto, MeasurementGoal.class);
		MeasurementGoal oldGoal = measurementGoalRepository.findById(newGoal.getId());
		stateTransition(oldGoal, newGoal);
		
		MeasurementGoalCrudDTO dtoCrud = new MeasurementGoalCrudDTO();
		dtoCrud.setRequest("update MeasurementGoal id" + dto.getMetadata().getId());
		if (oldGoal == null) {
			throw new NotFoundException();
		}

		if (dto.getOrganizationalGoalId() == null){
			throw new BadInputException("Measurement Goal must have a link to an Organizational Goal");
		}
		
		try {
			dtoCrud.addMeasurementGoalToList(
					measurementGoalToDTO(updateMeasurementGoal(newGoal)));
		} catch (Exception e) {
			throw new DBException("Error in saving, tipically your version is not the last");
		}

		return dtoCrud;
	}
	
	@Override
	public void deleteMeasurementGoalById(String id) throws BadInputException, IllegalStateTransitionException{
		if (id == null) {
			throw new BadInputException("Bad Input");
		}
//		if (!measurementGoalRepository.findById(id).getState().equals(State.Suspended)) {
//			throw new IllegalStateTransitionException("A MeasurementGoal must be Suspended before delete");
//		}
		
		measurementGoalRepository.delete(id);
	}
	@Override
	public void deleteMeasurementGoal(MeasurementGoalDTO dto) throws BadInputException, IllegalStateTransitionException{
		deleteMeasurementGoalById(dto.getMetadata().getId());
	}
	
	private void stateTransition(MeasurementGoal oldGoal, MeasurementGoal newGoal)
			throws IllegalStateTransitionException, NotFoundException {
		
		newGoal.setLastVersionDate(LocalDate.now());
		System.out.println(oldGoal.getState().toString()+"------>"+newGoal.getState().toString());
		if (oldGoal.getState().equals(newGoal.getState())) {
			 return;
			 }
		try {
			AbstractStateTransitionFactory.getFactory(Entity.MeasurementGoal).transition(oldGoal, newGoal).execute();
		} catch (Exception e) {
			throw new IllegalStateTransitionException(e);
		}
	
	}

	@Override
	public MeasurementGoalCrudDTO changeStateMeasurementGoal(MeasurementGoalDTO dto)
			throws BadInputException, IllegalStateTransitionException, NotFoundException, DBException {
		if (dto == null) {
			throw new BadInputException("Bad Input");
		}
		if (dto.getMetadata().getId() == null) {
			throw new BadInputException("MeasurementGoals cannot have null ID");
		}

		if (dto.getOrganizationalGoalId() == null){
			throw new BadInputException("Measurement Goal must have a link to an Organizational Goal");
		}
		

		MeasurementGoal oldGoal = new MeasurementGoal();
		MeasurementGoal newGoal =measurementGoalRepository.findById(dto.getMetadata().getId());
		oldGoal.setState(newGoal.getState());
		
		newGoal.setState(dto.getMetadata().getState());
		newGoal.setReleaseNote(dto.getMetadata().getReleaseNote());
		oldGoal.setVersion(newGoal.getVersion());

		stateTransition(oldGoal, newGoal);
		
		MeasurementGoalCrudDTO dtoCrud = new MeasurementGoalCrudDTO();
		dtoCrud.setRequest("update MeasurementGoal id" + dto.getMetadata().getId());

		try {
			dtoCrud.addMeasurementGoalToList(measurementGoalToDTO(updateMeasurementGoal(newGoal)));
		} catch (Exception e) {
			throw new DBException("Error in saving, tipically your version is not the last");
		}

		return dtoCrud;
	}

}
