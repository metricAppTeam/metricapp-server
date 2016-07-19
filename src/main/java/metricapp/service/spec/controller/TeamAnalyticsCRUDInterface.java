package metricapp.service.spec.controller;

import metricapp.dto.analytics.AnalyticsCrudDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;

public interface TeamAnalyticsCRUDInterface {
	
	public AnalyticsCrudDTO getAnalyticsByTeamId(String teamid) throws BadInputException, NotFoundException;
		
}
