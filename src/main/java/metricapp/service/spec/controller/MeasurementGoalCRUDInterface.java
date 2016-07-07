package metricapp.service.spec.controller;

import metricapp.dto.measurementGoal.MeasurementGoalCrudDTO;
import metricapp.dto.measurementGoal.MeasurementGoalDTO;
import metricapp.entity.measurementGoal.MeasurementGoal;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;

public interface MeasurementGoalCRUDInterface {
	
	public MeasurementGoalCrudDTO getMeasurementGoalById(String id) throws BadInputException, NotFoundException;
	
//	public MeasurementGoalDTO getMeasurementGoal(MeasurementGoalDTO dto) throws BadInputException, NotFoundException;
	
	public MeasurementGoal createMeasurementGoal(MeasurementGoal goal);
		
	public MeasurementGoalCrudDTO createMeasurementGoal(MeasurementGoalDTO dto) throws BadInputException;
	
	public MeasurementGoal updateMeasurementGoal(MeasurementGoal goal);
		
	public MeasurementGoalCrudDTO updateMeasurementGoal(MeasurementGoalDTO dto) throws DBException, NotFoundException, BadInputException, IllegalStateTransitionException;
	
	public void deleteMeasurementGoalById(String id) throws BadInputException, IllegalStateTransitionException;
		
	public void deleteMeasurementGoal(MeasurementGoalDTO dto) throws BadInputException, IllegalStateTransitionException;

	MeasurementGoalCrudDTO getMeasurementGoalByIdAndVersion(String id, String version)
			throws BadInputException, NotFoundException;

	MeasurementGoalCrudDTO getMeasurementGoalByUser(String userId) throws NotFoundException, BadInputException;

	MeasurementGoalCrudDTO getMeasurementGoalByIdAndLastApprovedVersion(String id) throws BadInputException, NotFoundException;

	MeasurementGoalCrudDTO changeStateMeasurementGoal(MeasurementGoalDTO dto)
			throws BadInputException, IllegalStateTransitionException, NotFoundException, DBException;

	long countMeasurementGoalByState(String state) throws BadInputException, NotFoundException;
		
}
