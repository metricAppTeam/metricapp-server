package metricapp.service.spec.controller;

import metricapp.exception.BadInputException;
import metricapp.exception.UnauthorizedException;

public interface AuthCRUDInterface {
	
	public boolean checkAuthorization(String auth) throws BadInputException, UnauthorizedException;
	
}
