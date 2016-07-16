package metricapp.service.spec.controller;

import metricapp.dto.login.LoginCrudDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.LoginException;
import metricapp.exception.NotFoundException;

public interface LoginCRUDInterface {

	public LoginCrudDTO getLogin(String username, String password) throws NotFoundException, BadInputException, LoginException, DBException;
}
