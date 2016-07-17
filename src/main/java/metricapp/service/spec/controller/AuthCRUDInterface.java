package metricapp.service.spec.controller;

import metricapp.exception.BadInputException;
import metricapp.exception.UnauthorizedException;

public interface AuthCRUDInterface {
	
	public String authenticate(String auth) throws BadInputException, UnauthorizedException;
	
}
