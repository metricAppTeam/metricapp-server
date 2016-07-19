package metricapp.service.spec.controller.OLD;

import metricapp.dto.OLD.logout.LogoutCrudDTO;
import metricapp.dto.OLD.logout.LogoutDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.IDException;
import metricapp.exception.NotFoundException;

public interface LogoutCRUDInterface {

	public LogoutCrudDTO createLogout(LogoutDTO logoutDTO) throws NotFoundException, BadInputException, IDException;
}
