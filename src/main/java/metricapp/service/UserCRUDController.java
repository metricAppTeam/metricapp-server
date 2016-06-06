package metricapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.service.spec.controller.UserCRUDControllerInterface;
import metricapp.service.spec.repository.UserRepository;

@Service
public class UserCRUDController implements UserCRUDControllerInterface{

	@Autowired
	UserRepository repository;
	
}
