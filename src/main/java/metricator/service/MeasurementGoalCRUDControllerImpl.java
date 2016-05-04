package metricator.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import lombok.Data;
import metricator.dto.MeasurementGoalCrudDTO;
import metricator.entity.measurementGoal.InterpretationModel;
import metricator.entity.measurementGoal.MeasurementGoal;
import metricator.service.spec.AssumptionRepository;
import metricator.service.spec.ContextRepository;
import metricator.service.spec.InterpretationModelRepository;
import metricator.service.spec.MeasurementGoalCRUDController;
import metricator.service.spec.MeasurementGoalRepository;
import metricator.service.spec.MetricRepository;
import metricator.service.spec.VariationFactorsRepository;

@ComponentScan
@Data
@Service("MeasurementGoalCRUDController")
public class MeasurementGoalCRUDControllerImpl implements MeasurementGoalCRUDController{

	// TODO check user rights in requests
	
	
	@Autowired
	private MeasurementGoalRepository measurementGoalRepository;
	
	@Autowired
	private MetricRepository metricRepository;

	@Autowired
	private ContextRepository contextRepository;

	@Autowired
	private VariationFactorsRepository variationFactorsRepository;
	
	@Autowired
	private AssumptionRepository assumptionRepository;
	
	@Autowired
	private InterpretationModelRepository intepretationModelRepository;
	
	private boolean userCanModify(String userId){
		return true;
	}
	
	private MeasurementGoalCrudDTO measurementGoalToDTO(MeasurementGoal goal){
		MeasurementGoalCrudDTO dto = new MeasurementGoalCrudDTO();
		BeanUtils.copyProperties(goal, dto);
		return dto;
	}
	
	@Override
	public MeasurementGoal getMeasurementGoalById(String id){
		return measurementGoalRepository.findOne(id);
	}
	@Override
	public MeasurementGoalCrudDTO getMeasurementGoal(MeasurementGoalCrudDTO dto){
		return measurementGoalToDTO(getMeasurementGoalById(dto.getId())); 
	}
	
	
	public MeasurementGoal createMeasurementGoal(MeasurementGoal goal){
		return measurementGoalRepository.save(goal);
	}
	
	public MeasurementGoalCrudDTO createMeasurementGoal(MeasurementGoalCrudDTO dto){
		if(userCanModify(dto.getUserId())){
			MeasurementGoal goal = new MeasurementGoal();
			BeanUtils.copyProperties(dto, goal);
			goal = createMeasurementGoal(goal);
			
			return measurementGoalToDTO(goal);
		}	
		return null;
	}
	
	@Override
	public MeasurementGoal updateMeasurementGoal(MeasurementGoal goal){
		return measurementGoalRepository.save(goal);
	}
	@Override
	public MeasurementGoalCrudDTO updateMeasurementGoal(MeasurementGoalCrudDTO dto){
		if(userCanModify(dto.getUserId())){
			MeasurementGoal goal = getMeasurementGoalById(dto.getId());
			BeanUtils.copyProperties(dto, goal);
			
			return measurementGoalToDTO(updateMeasurementGoal(goal));
		}
		return null;
	}
	
	@Override
	public void deleteMeasurementGoalById(String id){
		measurementGoalRepository.delete(id);
	}
	@Override
	public void deleteMeasurementGoal(MeasurementGoalCrudDTO dto){
		if(userCanModify(dto.getUserId())){
			deleteMeasurementGoalById(dto.getId());
		}	
	}
	
	
}
