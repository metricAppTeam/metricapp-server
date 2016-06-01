package metricapp.service.spec;

import metricapp.dto.measurementGoal.MeasurementGoalDTO;
import metricapp.entity.measurementGoal.MeasurementGoal;

public interface MeasurementGoalCRUDInterface {
	
	public MeasurementGoal getMeasurementGoalById(String id);
	
	public MeasurementGoalDTO getMeasurementGoal(MeasurementGoalDTO dto);
	
	public MeasurementGoal createMeasurementGoal(MeasurementGoal goal);
		
	public MeasurementGoalDTO createMeasurementGoal(MeasurementGoalDTO dto);
	
	public MeasurementGoal updateMeasurementGoal(MeasurementGoal goal);
		
	public MeasurementGoalDTO updateMeasurementGoal(MeasurementGoalDTO dto);
	
	public void deleteMeasurementGoalById(String id);
		
	public void deleteMeasurementGoal(MeasurementGoalDTO dto);
		
}
