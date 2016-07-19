package metricapp.service.spec.controller;

import metricapp.dto.credentials.CredentialsDTO;
import metricapp.dto.user.UserCrudDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.BusException;
import metricapp.exception.IDException;
import metricapp.exception.UnauthorizedException;

public interface AuthCRUDInterface {
	
	public UserCrudDTO login(CredentialsDTO credentialsDTO) throws BadInputException, UnauthorizedException, BusException, IDException;
	
	public UserCrudDTO logout(String authUsername) throws BadInputException, UnauthorizedException;
	
	public String authenticate(String auth) throws BadInputException, UnauthorizedException;	
	
}
