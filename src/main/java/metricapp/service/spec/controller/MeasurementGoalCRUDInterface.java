package metricapp.service.spec.controller;

import java.io.IOException;

import metricapp.dto.measurementGoal.MeasurementGoalCrudDTO;
import metricapp.dto.measurementGoal.MeasurementGoalDTO;
import metricapp.entity.measurementGoal.MeasurementGoal;
import metricapp.exception.BadInputException;
import metricapp.exception.BusException;
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

	MeasurementGoalCrudDTO getMeasurementGoalByIdAndLastApprovedVersion(String id) throws BadInputException, NotFoundException, BusException, IOException;

	MeasurementGoalCrudDTO changeStateMeasurementGoal(MeasurementGoalDTO dto)
			throws BadInputException, IllegalStateTransitionException, NotFoundException, DBException;

	long countMeasurementGoalByState(String state, String userId) throws BadInputException, NotFoundException;

	MeasurementGoalCrudDTO getMeasurementGoalByState(String state, String userId) throws NotFoundException, BadInputException;

	MeasurementGoalCrudDTO getMeasurementGoalByQuestionerId(String questionerId)
			throws BadInputException, NotFoundException;

	MeasurementGoalCrudDTO  getMeasurementGoalByQualityFocus(String qualityFocus) throws BadInputException, NotFoundException;

	MeasurementGoalCrudDTO getMeasurementGoalByObject(String object) throws BadInputException, NotFoundException;

	MeasurementGoalCrudDTO getMeasurementGoalByPurpose(String purpose) throws BadInputException, NotFoundException;

	MeasurementGoalCrudDTO getMeasurementGoalByViewPoint(String viewPoint) throws BadInputException, NotFoundException;

	MeasurementGoalCrudDTO getMeasurementGoalByTag(String tag) throws BadInputException, NotFoundException;

	MeasurementGoalCrudDTO getAll() throws NotFoundException;

	MeasurementGoalCrudDTO getAllApproved() throws BadInputException, BusException, IOException;

		
}
