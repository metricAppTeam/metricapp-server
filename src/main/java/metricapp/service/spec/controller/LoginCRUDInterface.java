package metricapp.service.spec.controller;

import metricapp.dto.login.LoginCrudDTO;
import metricapp.dto.login.LoginDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.BusException;
import metricapp.exception.IDException;
import metricapp.exception.LoginException;
import metricapp.exception.NotFoundException;

public interface LoginCRUDInterface {

	public LoginCrudDTO createLogin(LoginDTO loginDTO) throws BadInputException, NotFoundException, LoginException, BusException, IDException;
}
