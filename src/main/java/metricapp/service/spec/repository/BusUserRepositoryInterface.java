package metricapp.service.spec.repository;

import metricapp.entity.stakeholders.User;
import metricapp.exception.BusException;

public interface BusUserRepositoryInterface {

	User findUserByUsername(String username) throws BusException;

	User registerUser(User user) throws BusException;

	

}
