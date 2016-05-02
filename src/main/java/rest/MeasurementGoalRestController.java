package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import dto.MeasurementGoalCrudDTO;
import dto.ResponseDTO;
import service.MeasurementGoalCRUDControllerImpl;
import service.spec.MeasurementGoalCRUDController;

@RestController
@RequestMapping("/measurementgoal")
public class MeasurementGoalRestController {
	
	// TODO errorcheck and try catch blocks
	
	
	private MeasurementGoalCRUDController controller;
	
	// TODO autowired problem to resolve
	@Autowired
	private void getMeasurementGoalCRUDController(){
		this.controller = new MeasurementGoalCRUDControllerImpl();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<MeasurementGoalCrudDTO> getMeasurementGoalDTO(@RequestParam(value="id") String id){
		MeasurementGoalCrudDTO dto = new MeasurementGoalCrudDTO();
		dto.setId(id);
		
		return new ResponseEntity<MeasurementGoalCrudDTO>(controller.getMeasurementGoal(dto),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO> deleteMeasurementGoalDTO(@RequestParam String id){
		MeasurementGoalCrudDTO dto = new MeasurementGoalCrudDTO();
		dto.setId(id);
		controller.deleteMeasurementGoal(dto);
		
		return new ResponseEntity<ResponseDTO>(HttpStatus.OK);

	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO> putMeasurementGoalDTO(@RequestBody MeasurementGoalCrudDTO dto){
		controller.updateMeasurementGoal(dto);
		return new ResponseEntity<ResponseDTO>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> postMeasurementGoalDTO(@RequestBody MeasurementGoalCrudDTO dto){
		controller.createMeasurementGoal(dto);
		return new ResponseEntity<ResponseDTO>(HttpStatus.OK);
	}
	
}
