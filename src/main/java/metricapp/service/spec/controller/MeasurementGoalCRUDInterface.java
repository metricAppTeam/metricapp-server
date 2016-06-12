package metricapp.service.spec.controller;

import metricapp.dto.measurementGoal.MeasurementGoalDTO;
import metricapp.entity.measurementGoal.MeasurementGoal;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;

public interface MeasurementGoalCRUDInterface {
	
	public MeasurementGoal getMeasurementGoalById(String id) throws BadInputException, NotFoundException;
	
	public MeasurementGoalDTO getMeasurementGoal(MeasurementGoalDTO dto) throws BadInputException, NotFoundException;
	
	public MeasurementGoal createMeasurementGoal(MeasurementGoal goal);
		
	public MeasurementGoalDTO createMeasurementGoal(MeasurementGoalDTO dto) throws BadInputException;
	
	public MeasurementGoal updateMeasurementGoal(MeasurementGoal goal);
		
	public MeasurementGoalDTO updateMeasurementGoal(MeasurementGoalDTO dto);
	
	public void deleteMeasurementGoalById(String id);
		
	public void deleteMeasurementGoal(MeasurementGoalDTO dto);

	MeasurementGoalDTO getMeasurementGoalByIdAndVersion(String id, String version)
			throws BadInputException, NotFoundException;

	MeasurementGoalDTO getMeasurementGoalByUser(String userId) throws NotFoundException, BadInputException;
		
}
