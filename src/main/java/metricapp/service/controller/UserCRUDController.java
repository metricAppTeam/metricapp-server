package metricapp.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import metricapp.dto.metric.MetricCrudDTO;
import metricapp.service.spec.controller.UserCRUDControllerInterface;
import metricapp.service.spec.repository.UserRepository;


@Service("UserCRUDController")
public class UserCRUDController implements UserCRUDControllerInterface{

	@Autowired
	UserRepository repository;
	
	
	
}
