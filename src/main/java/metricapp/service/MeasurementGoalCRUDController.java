package metricapp.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import lombok.Data;
import metricapp.dto.MeasurementGoalCrudDTO;
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
