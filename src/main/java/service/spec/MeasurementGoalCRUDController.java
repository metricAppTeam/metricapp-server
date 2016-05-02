package service.spec;


import dto.MeasurementGoalCrudDTO;
import entity.measurementGoal.MeasurementGoal;

public interface MeasurementGoalCRUDController {
	
	public MeasurementGoal getMeasurementGoalById(String id);
	
	public MeasurementGoalCrudDTO getMeasurementGoal(MeasurementGoalCrudDTO dto);
	
	public MeasurementGoal createMeasurementGoal(MeasurementGoal goal);
		
	public MeasurementGoalCrudDTO createMeasurementGoal(MeasurementGoalCrudDTO dto);
	
	public MeasurementGoal updateMeasurementGoal(MeasurementGoal goal);
		
	public MeasurementGoalCrudDTO updateMeasurementGoal(MeasurementGoalCrudDTO dto);
	
	public void deleteMeasurementGoalById(String id);
		
	public void deleteMeasurementGoal(MeasurementGoalCrudDTO dto);
		
}
