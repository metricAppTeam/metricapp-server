package metricapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import metricapp.dto.ResponseDTO;
import metricapp.dto.measurementGoal.MeasurementGoalDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.controller.MeasurementGoalCRUDInterface;

@RestController
@RequestMapping("/measurementgoal")
public class MeasurementGoalRestController {
	
	// TODO errorcheck and try catch blocks
	
	@Autowired
	private MeasurementGoalCRUDInterface controller;	

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<MeasurementGoalDTO> getMeasurementGoalDTO(@RequestParam(value="id") String id){
		MeasurementGoalDTO dto = new MeasurementGoalDTO();
		dto.setId(id);
		
		try {
			return new ResponseEntity<MeasurementGoalDTO>(controller.getMeasurementGoal(dto),HttpStatus.OK);
		} catch (BadInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO> deleteMeasurementGoalDTO(@RequestParam String id){
		MeasurementGoalDTO dto = new MeasurementGoalDTO();
		dto.setId(id);
		controller.deleteMeasurementGoal(dto);
		
		return new ResponseEntity<ResponseDTO>(HttpStatus.OK);

	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO> putMeasurementGoalDTO(@RequestBody MeasurementGoalDTO dto){
		controller.updateMeasurementGoal(dto);
		return new ResponseEntity<ResponseDTO>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<MeasurementGoalDTO> postMeasurementGoalDTO(@RequestBody MeasurementGoalDTO dto){
		try {
			return new ResponseEntity<MeasurementGoalDTO>(controller.createMeasurementGoal(dto),HttpStatus.CREATED);
		} catch (BadInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
