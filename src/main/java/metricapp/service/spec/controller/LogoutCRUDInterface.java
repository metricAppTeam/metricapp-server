package metricapp.service.spec.controller;

import metricapp.dto.logout.*;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.LoginException;
import metricapp.exception.NotFoundException;

public interface LogoutCRUDInterface {

	public LogoutCrudDTO getLogout(String username) throws NotFoundException, BadInputException, LoginException, DBException;
}
