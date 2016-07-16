package metricapp.service.spec.controller;

import metricapp.dto.logout.*;
import metricapp.exception.BadInputException;
import metricapp.exception.IDException;
import metricapp.exception.NotFoundException;

public interface LogoutCRUDInterface {

	public LogoutCrudDTO createLogout(LogoutDTO logoutDTO) throws NotFoundException, BadInputException, IDException;
}
