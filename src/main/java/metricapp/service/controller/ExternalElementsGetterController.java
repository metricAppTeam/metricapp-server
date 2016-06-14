package metricapp.service.controller;

import metricapp.dto.externalElements.ExternalElementsDTO;
import metricapp.service.spec.controller.ExternalElementsGetterInterface;

public class ExternalElementsGetterController implements ExternalElementsGetterInterface{
	
	/**
	 * this method fills the return dto with every external informations that are related to 
	 * a Measurement Goal. It's useful to show the metricator the view of that Measurement Goal
	 * 
	 * @param measurementGoalId
	 * @return ExternalElementsDTO a dto that contains organizational goal, 
	 * context factors and assumptions of that goal
	 */
	ExternalElementsDTO getMeasurementGoalExternalElements(String measurementGoalId){
		return null;
		}
	
}
