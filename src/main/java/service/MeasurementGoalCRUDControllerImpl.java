package service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import dto.MeasurementGoalCrudDTO;
import entity.measurementGoal.InterpretationModel;
import entity.measurementGoal.MeasurementGoal;
import lombok.Data;
import service.spec.AssumptionRepository;
import service.spec.ContextRepository;
import service.spec.InterpretationModelRepository;
import service.spec.MeasurementGoalCRUDController;
import service.spec.MeasurementGoalRepository;
import service.spec.MetricRepository;
import service.spec.VariationFactorsRepository;

@Data
@Service("MeasurementGoalCRUDController")
public class MeasurementGoalCRUDControllerImpl implements MeasurementGoalCRUDController{

	// TODO check user rights
	
	@Autowired
	private MeasurementGoalRepository measurementGoalRepository;
	
	@SuppressWarnings("unused")
	@Autowired
	private MetricRepository metricRepository;

	@SuppressWarnings("unused")
	@Autowired
	private ContextRepository contextRepository;

	@SuppressWarnings("unused")
	@Autowired
	private VariationFactorsRepository variationFactorsRepository;
	
	@SuppressWarnings("unused")
	@Autowired
	private AssumptionRepository assumptionRepository;
	
	@SuppressWarnings("unused")
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
		measurementGoalRepository.count();
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
