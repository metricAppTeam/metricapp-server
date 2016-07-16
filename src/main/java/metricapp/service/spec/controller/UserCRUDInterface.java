package metricapp.service.spec.controller;

import metricapp.dto.user.*;
import metricapp.dto.user.UserCrudDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.BusException;
import metricapp.exception.DBException;
import metricapp.exception.IDException;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;

public interface UserCRUDInterface {

	public UserCrudDTO getUserByUsername(String username) throws NotFoundException, BadInputException;
	public UserCrudDTO createUser(UserDTO dto) throws BadInputException, IDException, DBException, BusException;
	public UserCrudDTO updateUser(UserDTO dto) throws BadInputException, NotFoundException, IllegalStateTransitionException, DBException, IDException;
	public UserCrudDTO getAllUsers();
	public UserCrudDTO getBusUserByUsername(String username) throws NotFoundException, BadInputException, BusException;
}
