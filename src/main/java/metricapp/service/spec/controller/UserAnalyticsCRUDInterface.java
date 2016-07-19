package metricapp.service.spec.controller;

import metricapp.dto.analytics.AnalyticsCrudDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;

public interface UserAnalyticsCRUDInterface {
	
	public AnalyticsCrudDTO getAnalyticsByUsername(String username) throws BadInputException, NotFoundException;
		
}
