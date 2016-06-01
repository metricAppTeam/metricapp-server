package metricapp.service;

import org.springframework.beans.BeanUtils;


import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import lombok.Data;
import metricapp.dto.measurementgoal.MeasurementGoalDTO;
import metricapp.dto.measurementgoal.MeasurementGoalMap;
import metricapp.entity.measurementGoal.InterpretationModel;
import metricapp.entity.measurementGoal.MeasurementGoal;
import metricapp.service.spec.AssumptionRepository;
import metricapp.service.spec.ContextRepository;
import metricapp.service.spec.MeasurementGoalCRUDInterface;
import metricapp.service.spec.MeasurementGoalRepository;
import metricapp.service.spec.MetricRepository;


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
	
	
	private boolean userCanModify(String userId){
		return true;
	}
	
	private MeasurementGoalDTO measurementGoalToDTO(MeasurementGoal goal){
		//MeasurementGoalCrudDTO dto = new MeasurementGoalCrudDTO();
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
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE).setFieldAccessLevel(AccessLevel.PRIVATE);
		modelMapper.addConverter(ModelMapperUtility.localDateToString());
		//modelMapper.addMappings(new MeasurementGoalMap());
		MeasurementGoalDTO dto = modelMapper.map(goal, MeasurementGoalDTO.class);

		//BeanUtils.copyProperties(goal, dto);
		System.out.println("\n\n--- Model mapper in use to DTO---\n\n");

		System.out.println("id: " + dto.getId() + "\n");
		System.out.println("object: " + dto.getObject() + "\n");
		System.out.println("viewPoint: " + dto.getViewPoint() + "\n");
		System.out.println("qualityFocus: " + dto.getFocus() + "\n");
		System.out.println("releaseNote: " + dto.getMetadata().getReleaseNote() + "\n");
		//System.out.println("purpose: " + goal.getPurpose + "\n");
		System.out.println("version: " + dto.getMetadata().getVersion() + "\n");
		System.out.println("creationDate: " + dto.getMetadata().getCreationDate() + "\n");
		System.out.println("lastVersionDate: " + dto.getMetadata().getLastVersionDate() + "\n");
		System.out.println("metricsIdList: " + dto.getMetricIdList() + "\n");
		System.out.println("questionIdList: " + dto.getQuestionIdList() + "\n");
		
		return dto;
	}
	
	@Override
	public MeasurementGoal getMeasurementGoalById(String id){
		return measurementGoalRepository.findOne(id);
	}
	@Override
	public MeasurementGoalDTO getMeasurementGoal(MeasurementGoalDTO dto){
		return measurementGoalToDTO(getMeasurementGoalById(dto.getId()));
	}
	
	
	public MeasurementGoal createMeasurementGoal(MeasurementGoal goal){
		return measurementGoalRepository.save(goal);
	}
	
	public MeasurementGoalDTO createMeasurementGoal(MeasurementGoalDTO dto){
		if(userCanModify(dto.getUserId())){
			//MeasurementGoal goal = new MeasurementGoal();
			System.out.println("\n\n--- Using Model mapper ---\n\n");
			
			System.out.println("id: " + dto.getId() + "\n");
			System.out.println("object: " + dto.getObject() + "\n");
			System.out.println("viewPoint: " + dto.getViewPoint() + "\n");
			System.out.println("qualityFocus: " + dto.getFocus() + "\n");
			System.out.println("releaseNote: " + dto.getMetadata().getReleaseNote() + "\n");
			//System.out.println("purpose: " + goal.getPurpose + "\n");
			System.out.println("version: " + dto.getMetadata().getVersion() + "\n");
			System.out.println("creationDate: " + dto.getMetadata().getCreationDate() + "\n");
			System.out.println("lastVersionDate: " + dto.getMetadata().getLastVersionDate() + "\n");
			System.out.println("metricsIdList: " + dto.getMetricIdList() + "\n");
			System.out.println("questionIdList: " + dto.getQuestionIdList() + "\n");
			
			/*
			PropertyMap<MeasurementGoalDTO, MeasurementGoal> measurementGoalDTOMap = new PropertyMap<MeasurementGoalDTO, MeasurementGoal>() {
			  protected void configure() {
			    map().getInterpretationModel().setFunctionJavascript(source.getInterpretationModel().getFunctionJavascript());
			    map(source.getAddress().city, destination.city);
			  }
			};

			modelMapper.addMappings(personMap);
			*/
			
			ModelMapper modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE).setFieldAccessLevel(AccessLevel.PRIVATE);
			modelMapper.addConverter(ModelMapperUtility.stringToLocalDate());
			MeasurementGoal goal = modelMapper.map(dto, MeasurementGoal.class);
			
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
			
			System.out.println("metricatorId: " + goal.getMetricator().getId() + "\n");
			
			


			goal = createMeasurementGoal(goal);
			
			//goal.setObject("Ciaone");
			return measurementGoalToDTO(goal);
		}	
		return null;
	}
	
	@Override
	public MeasurementGoal updateMeasurementGoal(MeasurementGoal goal){
		return measurementGoalRepository.save(goal);
	}
	@Override
	public MeasurementGoalDTO updateMeasurementGoal(MeasurementGoalDTO dto){
		if(userCanModify(dto.getUserId())){
			MeasurementGoal goal = getMeasurementGoalById(dto.getId());
			goal.setObject("Ciaone modificato");
			
			return measurementGoalToDTO(updateMeasurementGoal(goal));
		}
		return null;
	}
	
	@Override
	public void deleteMeasurementGoalById(String id){
		measurementGoalRepository.delete(id);
	}
	@Override
	public void deleteMeasurementGoal(MeasurementGoalDTO dto){
		if(userCanModify(dto.getUserId())){
			deleteMeasurementGoalById(dto.getId());
		}	
	}
	
	
}
