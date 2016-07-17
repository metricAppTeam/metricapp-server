package metricapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import metricapp.dto.analytics.AnalyticsCrudDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.controller.GridAnalyticsCRUDInterface;

@CrossOrigin
@RestController
@RequestMapping(("/analytics/grids"))
public class GridAnalyticsRestController {
	
	@Autowired
	private GridAnalyticsCRUDInterface gridAnalyticsCRUDController;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<AnalyticsCrudDTO> getGridAnalytics(
			@RequestHeader(value = "username", defaultValue = "NA") String username,
			@RequestParam(value = "gridid", defaultValue = "NA") String gridid) {
		
		AnalyticsCrudDTO responseDTO = new AnalyticsCrudDTO();
		
		try {
			
			if (username.equals("NA")) {
				return new ResponseEntity<AnalyticsCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
	
			if (!gridid.equals("NA")) {
				responseDTO = gridAnalyticsCRUDController.getAnalyticsByGridId(gridid);
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
