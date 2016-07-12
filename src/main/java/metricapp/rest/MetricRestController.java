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

import metricapp.dto.metric.MetricCrudDTO;
import metricapp.dto.metric.MetricDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.controller.MetricCRUDInterface;
import metricapp.service.spec.repository.BusApprovedElementInterface;

@CrossOrigin
@RestController
@RequestMapping(("/metric"))
public class MetricRestController {
	@Autowired
	private MetricCRUDInterface metricCRUDController;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<MetricCrudDTO> getMetricDTO(@RequestParam(value = "id", defaultValue = "NA") String id,
			@RequestParam(value = "version", defaultValue = "NA") String version,
			@RequestParam(value = "userid", defaultValue = "NA") String userId,
			@RequestParam(value = "approved", defaultValue = "false") String approved,
			@RequestParam(value = "state", defaultValue = "NA") String state) {
		MetricCrudDTO dto = new MetricCrudDTO();
		try {
			if (!userId.equals("NA") && state.equals("NA")) {
				dto = metricCRUDController.getMetricOfUser(userId);
				return new ResponseEntity<MetricCrudDTO>(dto, HttpStatus.OK);
			}
			if (!id.equals("NA") && approved.equals("true")) {
				dto = metricCRUDController.getMetricCrudDTOByIdLastApprovedVersion(id);
				return new ResponseEntity<MetricCrudDTO>(dto, HttpStatus.OK);
			}
			if (!version.equals("NA") && !id.equals("NA")) {
				dto = metricCRUDController.getMetricByIdAndVersion(id, version);
				return new ResponseEntity<MetricCrudDTO>(dto, HttpStatus.OK);
			}
			if (!id.equals("NA")) {
				dto = metricCRUDController.getMetricById(id);
				return new ResponseEntity<MetricCrudDTO>(dto, HttpStatus.OK);
			} 
			if (!userId.equals("NA") && !state.equals("NA")) {
				dto = metricCRUDController.getMetricByStateAndMetricatorId(state,id);
				return new ResponseEntity<MetricCrudDTO>(dto, HttpStatus.OK);
			}
			if (userId.equals("NA") && !state.equals("NA")) {
				dto = metricCRUDController.getMetricByState(state);
				return new ResponseEntity<MetricCrudDTO>(dto, HttpStatus.OK);
			} 
			else {
				return new ResponseEntity<MetricCrudDTO>(HttpStatus.BAD_REQUEST);
			}
		} catch (BadInputException e) {
			dto.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<MetricCrudDTO>(dto, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			dto.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<MetricCrudDTO>(dto, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			dto.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<MetricCrudDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<MetricCrudDTO> deleteMetricDTO(@RequestParam String id) {
		MetricCrudDTO dto = new MetricCrudDTO();
		try {
			metricCRUDController.deleteMetricById(id);
		} catch (BadInputException e) {
			e.printStackTrace();
			dto.setError(e.getMessage());
			return new ResponseEntity<MetricCrudDTO>(dto, HttpStatus.BAD_REQUEST);
		} catch (IllegalStateTransitionException e) {
			e.printStackTrace();
			dto.setError(e.getMessage());
			return new ResponseEntity<MetricCrudDTO>(dto, HttpStatus.FORBIDDEN);
		} catch (Exception e){
			dto.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<MetricCrudDTO>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<MetricCrudDTO>(HttpStatus.OK);

	}
	@Autowired
	BusApprovedElementInterface busApprovedElementRepository; 
	/**
	 * This method implements Put interface for Metrics.
	 * It expects a put on /metric/ that contains a MetricDTO. an optional parameter of the url can be onlychangestate, it's a boolean true or false, default is false.
	 * WIth that flag put dto must only contain the state and the id.
	 * Version number of the entity MUST be the same of the last in db, this is to avoid collisions.
	 * @param dto metricDTO filled 
	 * @param onlyChangeState it's a boolean
	 * @return returns the entity modified.
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<MetricCrudDTO> putMetricDTO(@RequestBody MetricDTO dto,
			@RequestParam(value = "onlychangestate", defaultValue = "false") String onlyChangeState) {
		MetricCrudDTO rensponseDTO = new MetricCrudDTO();
		try {
			if (onlyChangeState.equals("false")) {
				return new ResponseEntity<MetricCrudDTO>(metricCRUDController.updateMetric(dto), HttpStatus.OK);
			} else {
				return new ResponseEntity<MetricCrudDTO>(metricCRUDController.changeStateMetric(dto), HttpStatus.OK);
			}
		} catch (BadInputException e) {
			rensponseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<MetricCrudDTO>(rensponseDTO, HttpStatus.BAD_REQUEST);
		} catch (IllegalStateTransitionException e) {
			rensponseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<MetricCrudDTO>(rensponseDTO, HttpStatus.FORBIDDEN);
		} catch (NotFoundException e) {
			rensponseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<MetricCrudDTO>(rensponseDTO, HttpStatus.NOT_FOUND);
		} catch (DBException e) {
			rensponseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<MetricCrudDTO>(rensponseDTO, HttpStatus.CONFLICT);
		}catch (Exception e){
			rensponseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<MetricCrudDTO>(rensponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<MetricCrudDTO> postMeasurementGoalDTO(@RequestBody MetricDTO dto) {

		MetricCrudDTO rensponseDTO = new MetricCrudDTO();
		try {
			return new ResponseEntity<MetricCrudDTO>(metricCRUDController.createMetric(dto), HttpStatus.CREATED);
		} catch (BadInputException e) {
			rensponseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<MetricCrudDTO>(rensponseDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e){
			rensponseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<MetricCrudDTO>(rensponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/count",method = RequestMethod.GET)
	public ResponseEntity<MetricCrudDTO> getCountMetricDTOByState(@RequestParam(value="state") String state,
			@RequestParam(value="userid") String userId){
		MetricCrudDTO dto = new MetricCrudDTO();
		try {
			dto.setCount(metricCRUDController.countMetricByState(state,userId));
			return new ResponseEntity<MetricCrudDTO>(dto, HttpStatus.OK);
		} catch (BadInputException | NotFoundException e) {
			e.printStackTrace();
			dto.setError(e.getMessage());
			return new ResponseEntity<MetricCrudDTO>(dto, HttpStatus.BAD_REQUEST);
		}
	}

}
