package metricapp.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.analytics.AnalyticsCrudDTO;
import metricapp.dto.analytics.AnalyticsDTO;
import metricapp.entity.analytics.Analytics;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.controller.UserAnalyticsCRUDInterface;

@Service
public class UserAnalyticsCRUDController implements UserAnalyticsCRUDInterface {

	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory;
	
	@Override
	public AnalyticsCrudDTO getAnalyticsByUsername(String username) throws BadInputException, NotFoundException {
		if (username == null) {
			throw new BadInputException("UserAnalytics username cannot be null");
		}
		
		// START USER ANALYTICS COMPUTATION
		Analytics analytics = Analytics.randomUserAnalytics();
		// END USER ANALYTICS COMPUTATION
		
		AnalyticsCrudDTO crud = new AnalyticsCrudDTO();
		crud.setRequest("GET UserAnalytics WITH username=" + username);
		crud.addAnalyticsToList(modelMapperFactory.getStandardModelMapper().map(analytics, AnalyticsDTO.class));
		
		return crud;
	}

	
}
