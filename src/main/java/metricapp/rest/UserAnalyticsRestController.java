package metricapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import metricapp.dto.analytics.AnalyticsCrudDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.controller.UserAnalyticsCRUDInterface;

@CrossOrigin
@RestController
@RequestMapping(("/analytics/users"))
public class UserAnalyticsRestController {
	
	@Autowired
	private UserAnalyticsCRUDInterface userAnalyticsCRUDController;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<AnalyticsCrudDTO> getUserAnalytics(
			@RequestParam(value = "username", defaultValue = "NA") String username) {
		
		AnalyticsCrudDTO responseDTO = new AnalyticsCrudDTO();
		
		try {
	
			if (!username.equals("NA")) {
				responseDTO = userAnalyticsCRUDController.getAnalyticsByUsername(username);
				return new ResponseEntity<AnalyticsCrudDTO>(responseDTO, HttpStatus.OK);
			}		
			return new ResponseEntity<AnalyticsCrudDTO>(HttpStatus.BAD_REQUEST);
		} catch (BadInputException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<AnalyticsCrudDTO>(responseDTO, HttpStatus.NOT_FOUND);
		}  catch (NotFoundException e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<AnalyticsCrudDTO>(responseDTO, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			responseDTO.setError(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<AnalyticsCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
