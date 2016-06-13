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
import metricapp.service.spec.controller.ModelMapperFactoryInterface;
import metricapp.service.spec.repository.AssumptionRepository;
import metricapp.service.spec.repository.ContextRepository;
import metricapp.service.spec.repository.MeasurementGoalRepository;
import metricapp.service.spec.repository.MetricRepository;

@Data
@Service("MeasurementGoalCRUDController")
public class MeasurementGoalCRUDController implements MeasurementGoalCRUDInterface{

	// TODO check user rights in requests
		
	@Autowired
	private MeasurementGoalRepository measurementGoalRepository;
	
	@Autowired
	private MetricRepository metricRepository;

	@Autowired
	private ContextRepository contextRepository;

	@Autowired
	private AssumptionRepository assumptionRepository;

	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory;
		
	private boolean debug = false;
	
	private MeasurementGoalDTO measurementGoalToDTO(MeasurementGoal goal){
		//MeasurementGoalCrudDTO dto = new MeasurementGoalCrudDTO();
		if(debug){
			System.out.println("\n\n--- Using Model mapper to DTO---\n\n");
			
			System.out.println("id: " + goal.getId() + "\n");
			System.out.println("object: " + goal.getObject() + "\n");
			System.out.println("purpose: " + goal.getPurpose() + "\n");
			System.out.println("qualityFocus: " + goal.getQualityFocus() + "\n");
			System.out.println("releaseNote: " + goal.getReleaseNote() + "\n");
			System.out.println("version: " + goal.getVersion() + "\n");
			System.out.println("viewPoint: " + goal.getViewPoint() + "\n");
			System.out.println("creationDate: " + goal.getCreationDate() + "\n");
			System.out.println("lastVersionDate: " + goal.getLastVersionDate() + "\n");
			System.out.println("metricsIdList: " + goal.getMetricIdList() + "\n");
			System.out.println("questionIdList: " + goal.getQuestionIdList() + "\n");
			System.out.println("tags: " + goal.getTags() + "\n");
			//System.out.println("organizationalGoalId: " + goal.getOrganizationalGoal().getId() + "\n");
			
			System.out.println("functionJavaScript: " + goal.getInterpretationModel().getFunctionJavascript() +  "\n");
			System.out.println("queryNoSQL: " + goal.getInterpretationModel().getQueryNoSQL() + "\n");
			
			//System.out.println("metricatorId: " + goal.getMetricator().getId() + "\n");
		}
		
		
		ModelMapper modelMapper = modelMapperFactory.getLooseModelMapper();
		//modelMapper.addMappings(new MeasurementGoalMap());
		MeasurementGoalDTO dto = modelMapper.map(goal, MeasurementGoalDTO.class);

		if(debug){
			System.out.println("\n\n--- Model mapper in use to DTO---\n\n");
	
			System.out.println("id: " + dto.getId() + "\n");
			System.out.println("object: " + dto.getObject() + "\n");
			System.out.println("viewPoint: " + dto.getViewPoint() + "\n");
			System.out.println("qualityFocus: " + dto.getFocus() + "\n");
			System.out.println("releaseNote: " + dto.getMetadata().getReleaseNote() + "\n");
			System.out.println("purpose: " + goal.getPurpose() + "\n");
			System.out.println("version: " + dto.getMetadata().getVersion() + "\n");
			System.out.println("creationDate: " + dto.getMetadata().getCreationDate() + "\n");
			System.out.println("lastVersionDate: " + dto.getMetadata().getLastVersionDate() + "\n");
			System.out.println("metricsIdList: " + dto.getMetricIdList() + "\n");
			System.out.println("questionIdList: " + dto.getQuestionIdList() + "\n");
		}		
		return dto;
	}
	
	@Override
	public MeasurementGoal getMeasurementGoalById(String id) throws BadInputException, NotFoundException{
		if (id == null) {
			throw new BadInputException("MeasurementGoal id cannot be null");
		}
		MeasurementGoal measurementGoal = measurementGoalRepository.findOne(id);
		if (measurementGoal == null) {
			throw new NotFoundException("MeasurementGoal with id " + id + "is not available");
		}
		
		return measurementGoal;
	
	}
	@Override
	public MeasurementGoalDTO getMeasurementGoal(MeasurementGoalDTO dto) throws BadInputException, NotFoundException{
		return measurementGoalToDTO(getMeasurementGoalById(dto.getId()));
	}
	

	@Override
	public MeasurementGoalDTO getMeasurementGoalByIdAndVersion(String id, String version)
			throws BadInputException, NotFoundException {
		if (id == null || version == null) {
			throw new BadInputException("Metric id,version cannot be null");
		}
		MeasurementGoal measurementGoal = measurementGoalRepository.findByIdAndVersion(id, version);
		if (measurementGoal == null) {
			throw new NotFoundException("Metric with id " + id + " and version " + version + "is not available");
		}
		return modelMapperFactory.getLooseModelMapper().map(measurementGoal, MeasurementGoalDTO.class);
	}
	
	@Override
	public MeasurementGoalCrudDTO getMeasurementGoalByUser(String userId) throws NotFoundException, BadInputException {
		if (userId == null) {
			throw new BadInputException("MeasurementGoal userId cannot be null");
		}
		ArrayList<MeasurementGoal> measurementGoals = measurementGoalRepository.findByMetricatorId(userId);
		if (measurementGoals.size() == 0) {
			throw new NotFoundException("User " + userId + " has no Metrics");
		}
		
		MeasurementGoalCrudDTO dto = new MeasurementGoalCrudDTO();
		dto.setRequest("MeasurementGoal of " + userId);
		Iterator<MeasurementGoal> measurementGoalIter = measurementGoals.iterator();
		while (measurementGoalIter.hasNext()) {
			dto.addMeasurementGoalToList(modelMapperFactory.getLooseModelMapper().map(measurementGoalIter.next(), MeasurementGoalDTO.class));
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
			throw new BadInputException("New Metrics cannot have ID");
		}
		
//		if (dto.getMetadata().getState() != State.Created){
//			throw new BadInputException("MeasurementGoal must be in CREATED state");
//		}
		
		dto.getMetadata().setCreationDate(LocalDate.now());
		dto.getMetadata().setLastVersionDate(LocalDate.now());
		
		if(debug){
			System.out.println("\n\n--- Using Model mapper ---\n\n");
			
			System.out.println("id: " + dto.getId() + "\n");
			System.out.println("object: " + dto.getObject() + "\n");
			System.out.println("viewPoint: " + dto.getViewPoint() + "\n");
			System.out.println("qualityFocus: " + dto.getFocus() + "\n");
			System.out.println("releaseNote: " + dto.getMetadata().getReleaseNote() + "\n");
			System.out.println("purpose: " + dto.getPurpose() + "\n");
			System.out.println("version: " + dto.getMetadata().getVersion() + "\n");
			System.out.println("creationDate: " + dto.getMetadata().getCreationDate() + "\n");
			System.out.println("lastVersionDate: " + dto.getMetadata().getLastVersionDate() + "\n");
			System.out.println("metricsIdList: " + dto.getMetricIdList() + "\n");
			System.out.println("questionIdList: " + dto.getQuestionIdList() + "\n");
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
		
		ModelMapper modelMapper = modelMapperFactory.getLooseModelMapper();
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
			System.out.println("metricsIdList: " + goal.getMetricIdList() + "\n");
			System.out.println("questionIdList: " + goal.getQuestionIdList() + "\n");
			System.out.println("tags: " + goal.getTags() + "\n");
			System.out.println("organizationalGoalId: " + goal.getOrganizationalGoal().getId() + "\n");
			
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
	public MeasurementGoalCrudDTO updateMeasurementGoal(MeasurementGoalDTO dto) throws DBException, NotFoundException{
		
		ModelMapper modelMapper = modelMapperFactory.getLooseModelMapper();
		MeasurementGoal newGoal = modelMapper.map(dto, MeasurementGoal.class);
		MeasurementGoal oldGoal = measurementGoalRepository.findById(newGoal.getId());
		
		//state transition
		
		MeasurementGoalCrudDTO dtoCrud = new MeasurementGoalCrudDTO();
		dtoCrud.setRequest("update MeasurementGoal id" + dto.getMetadata().getId());
		if (oldGoal == null) {
			throw new NotFoundException();
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
		deleteMeasurementGoalById(dto.getId());
	}
	
	
}
