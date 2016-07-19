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
import metricapp.exception.UnauthorizedException;
import metricapp.service.spec.controller.AuthCRUDInterface;
import metricapp.service.spec.controller.TeamAnalyticsCRUDInterface;

@CrossOrigin
@RestController
@RequestMapping(("/analytics/teams"))
public class TeamAnalyticsRestController {
	
	@Autowired
	private AuthCRUDInterface authController;
	
	@Autowired
	private TeamAnalyticsCRUDInterface analyticsController;	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<AnalyticsCrudDTO> getGridAnalytics(
			@RequestHeader(value = "Authorization", defaultValue = "NA") String auth,
			@RequestParam(value = "teamid", defaultValue = "NA") String teamid) {
		
		AnalyticsCrudDTO responseDTO = new AnalyticsCrudDTO();
		
		try {
			
			if (auth.equals("NA")) {
				return new ResponseEntity<AnalyticsCrudDTO>(HttpStatus.UNAUTHORIZED);
			}
			
			authController.authenticate(auth);
	
			if (!teamid.equals("NA")) {
				responseDTO = analyticsController.getAnalyticsByTeamId(teamid);
			} else {
				return new ResponseEntity<AnalyticsCrudDTO>(HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<AnalyticsCrudDTO>(responseDTO, HttpStatus.OK);
			
		} catch (UnauthorizedException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<AnalyticsCrudDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
		} catch (BadInputException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<AnalyticsCrudDTO>(responseDTO, HttpStatus.NOT_FOUND);
		}  catch (NotFoundException e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<AnalyticsCrudDTO>(responseDTO, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			responseDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<AnalyticsCrudDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
