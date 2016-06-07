package metricapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import metricapp.service.spec.controller.UserCRUDControllerInterface;

@RestController
@RequestMapping(("/users"))
public class UserRestController {

	@Autowired
	UserCRUDControllerInterface controller;
}