package metricapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import metricapp.dto.measurementGoal.MeasurementGoalCrudDTO;
import metricapp.dto.measurementGoal.MeasurementGoalDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.controller.MeasurementGoalCRUDInterface;

@CrossOrigin
@RestController
@RequestMapping("/measurementgoal")
public class MeasurementGoalRestController {
		
	@Autowired
	private MeasurementGoalCRUDInterface controller;	

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<MeasurementGoalCrudDTO> getMeasurementGoalDTO(@RequestParam(value="id", defaultValue = "NA") String id,
			@RequestParam(value = "version", defaultValue = "NA") String version,
			@RequestParam(value = "userid", defaultValue = "NA") String userId,
			@RequestParam(value = "approved", defaultValue = "false") String approved,
			@RequestParam(value = "questionerId", defaultValue = "NA") String questionerId,
			@RequestParam(value = "state", defaultValue = "NA") String state){
		
		MeasurementGoalCrudDTO dto = new MeasurementGoalCrudDTO();
		try {
			if (!userId.equals("NA") && id.equals("NA")) {
				dto = controller.getMeasurementGoalByUser(userId);
				return new ResponseEntity<MeasurementGoalCrudDTO>(dto, HttpStatus.OK);
			}
			if (!id.equals("NA") && approved.equals("true")) {
				dto = controller.getMeasurementGoalByIdAndLastApprovedVersion(id);
				return new ResponseEntity<MeasurementGoalCrudDTO>(dto, HttpStatus.OK);
			}
			if (!version.equals("NA") && !id.equals("NA")) {
				dto = controller.getMeasurementGoalByIdAndVersion(userId, version);
				return new ResponseEntity<MeasurementGoalCrudDTO>(dto, HttpStatus.OK);
			}
			if (!id.equals("NA")) {
				dto = controller.getMeasurementGoalById(id);
				return new ResponseEntity<MeasurementGoalCrudDTO>(dto, HttpStatus.OK);
			}
			if (!userId.equals("NA") && !state.equals("NA")) {
				dto = controller.getMeasurementGoalByState(state, userId);
				return new ResponseEntity<MeasurementGoalCrudDTO>(dto, HttpStatus.OK);
			}
			if (!questionerId.equals("NA")){
				dto = controller.getMeasurementGoalByQuestionerId(questionerId);
				return new ResponseEntity<MeasurementGoalCrudDTO>(dto, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<MeasurementGoalCrudDTO>(HttpStatus.BAD_REQUEST);
			}
		} catch (BadInputException e) {
			dto.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<MeasurementGoalCrudDTO>(dto, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			dto.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<MeasurementGoalCrudDTO>(dto, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			dto.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<MeasurementGoalCrudDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/count",method = RequestMethod.GET)
	public ResponseEntity<MeasurementGoalCrudDTO> getCountMeasurementGoalDTOByState(@RequestParam(value="state") String state,
			@RequestParam(value="userid") String userId){
		MeasurementGoalCrudDTO dto = new MeasurementGoalCrudDTO();
		try {
			dto.setCount(controller.countMeasurementGoalByState(state,userId));
			return new ResponseEntity<MeasurementGoalCrudDTO>(dto, HttpStatus.OK);
		} catch (BadInputException | NotFoundException e) {
			e.printStackTrace();
			dto.setError(e.getMessage());
			return new ResponseEntity<MeasurementGoalCrudDTO>(dto, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<MeasurementGoalCrudDTO> deleteMeasurementGoalDTO(@RequestParam String id){
		MeasurementGoalCrudDTO dto = new MeasurementGoalCrudDTO();
		try {
			controller.deleteMeasurementGoalById(id);
		} catch (BadInputException e) {
			e.printStackTrace();
			dto.setError(e.getMessage());
			return new ResponseEntity<MeasurementGoalCrudDTO>(dto, HttpStatus.BAD_REQUEST);
		} catch (IllegalStateTransitionException e) {
			e.printStackTrace();
			dto.setError(e.getMessage());
			return new ResponseEntity<MeasurementGoalCrudDTO>(dto, HttpStatus.FORBIDDEN);
		} catch (Exception e){
			e.printStackTrace();
			dto.setError(e.getMessage());
			return new ResponseEntity<MeasurementGoalCrudDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<MeasurementGoalCrudDTO>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<MeasurementGoalCrudDTO> putMeasurementGoalDTO(@RequestBody MeasurementGoalDTO dto, @RequestParam(value = "onlychangestate", defaultValue = "false") String onlyChangeState) {
		MeasurementGoalCrudDTO rensponseDTO = new MeasurementGoalCrudDTO();
		try {
			if (onlyChangeState.equals("false")) {
				return new ResponseEntity<MeasurementGoalCrudDTO>(controller.updateMeasurementGoal(dto), HttpStatus.OK);
			} else {
				return new ResponseEntity<MeasurementGoalCrudDTO>(controller.changeStateMeasurementGoal(dto), HttpStatus.OK);
			}
		} catch (NotFoundException e) {
			rensponseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<MeasurementGoalCrudDTO>(rensponseDTO, HttpStatus.NOT_FOUND);
		} catch (DBException e) {
			rensponseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<MeasurementGoalCrudDTO>(rensponseDTO, HttpStatus.CONFLICT);
		} catch (BadInputException e) {
			rensponseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<MeasurementGoalCrudDTO>(rensponseDTO, HttpStatus.BAD_REQUEST);
		} catch (IllegalStateTransitionException e) {
			rensponseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<MeasurementGoalCrudDTO>(rensponseDTO, HttpStatus.FORBIDDEN);
		}
		catch (Exception e){
			rensponseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<MeasurementGoalCrudDTO>(rensponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<MeasurementGoalCrudDTO> postMeasurementGoalDTO(@RequestBody MeasurementGoalDTO dto){
		MeasurementGoalCrudDTO rensponseDTO = new MeasurementGoalCrudDTO();
		
		try {
			return new ResponseEntity<MeasurementGoalCrudDTO>(controller.createMeasurementGoal(dto),HttpStatus.CREATED);
		} catch (BadInputException e) {
			rensponseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<MeasurementGoalCrudDTO>(rensponseDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e){
			rensponseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<MeasurementGoalCrudDTO>(rensponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

