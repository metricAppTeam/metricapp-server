package metricapp.service.spec.controller;

import metricapp.dto.user.*;
import metricapp.dto.user.UserCrudDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;

public interface UserCRUDInterface {

	public UserCrudDTO getUserByUsername(String username) throws NotFoundException, BadInputException;
	public UserCrudDTO createUser(UserDTO dto) throws BadInputException;
	public UserCrudDTO updateUser(UserDTO dto) throws BadInputException, NotFoundException, IllegalStateTransitionException, DBException;
	public UserCrudDTO getAllUsers();
}
