package metricapp.service.spec.controller;

import metricapp.dto.analytics.AnalyticsCrudDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;

public interface GridAnalyticsCRUDInterface {
	
	public AnalyticsCrudDTO getAnalyticsByGridId(String gridid) throws BadInputException, NotFoundException;
		
}
