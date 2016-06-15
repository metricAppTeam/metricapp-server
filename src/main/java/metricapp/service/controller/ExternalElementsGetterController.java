package metricapp.service.controller;

import metricapp.dto.externalElements.ExternalElementsDTO;
import metricapp.entity.measurementGoal.MeasurementGoal;
import metricapp.service.spec.controller.ExternalElementsGetterInterface;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.repository.MeasurementGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExternalElementsGetterController implements ExternalElementsGetterInterface{


	@Autowired
	private MeasurementGoalRepository measurementGoalRepository;

	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory;


	/**
	 * this method fills the return dto with every external informations that are related to 
	 * a Measurement Goal. It's useful to show the metricator the view of that Measurement Goal
	 * 
	 * @param measurementGoalId
	 * @return ExternalElementsDTO a dto that contains organizational goal, 
	 * context factors and assumptions of that goal
	 */
	ExternalElementsDTO getMeasurementGoalExternalElements(String measurementGoalId){

		MeasurementGoal measurementGoal = this.measurementGoalRepository.findById(measurementGoalId);

		return null;
		}
	
}
