package metricapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import metricapp.dto.MeasurementGoalCrudDTO;
import metricapp.dto.MetricDTO;
import metricapp.service.spec.MetricCRUDInterface;

@RestController
@RequestMapping(("/metric"))
public class MetricRestController {
	@Autowired
	private MetricCRUDInterface metricCRUDController;
	
	@RequestMapping
	public ResponseEntity<MetricDTO> prova(){
		
		return new ResponseEntity<MetricDTO>(metricCRUDController.prova(),HttpStatus.OK); 
	}
}
