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

import metricapp.dto.team.TeamCrudDTO;
import metricapp.dto.team.TeamDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;
import metricapp.service.controller.TeamCRUDController;

@CrossOrigin 
@RestController
@RequestMapping(("/team"))

public class TeamRestController {
	
	@Autowired 
	TeamCRUDController teamCRUDController;
	
	/**
	 * Get Method for search team by id
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<TeamCrudDTO> getTeamDTO(
			@RequestParam(value="id", defaultValue="NA") String id){
		
		TeamCrudDTO teamCrudDTO = new TeamCrudDTO();
		
		try{
			if(!id.equals("NA") && !id.equals("all")){
				teamCrudDTO = teamCRUDController.getTeamById(id);
			}else if(id.equals("all")){
				teamCrudDTO = teamCRUDController.getAllTeams();
			}else{
				teamCrudDTO.setError("No parameters given");
				return new ResponseEntity<TeamCrudDTO>(teamCrudDTO, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<TeamCrudDTO>(teamCrudDTO, HttpStatus.OK);
			
		} catch (BadInputException e){
			teamCrudDTO.setError("No team have been found");
			return new ResponseEntity<TeamCrudDTO>(teamCrudDTO, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e){
			teamCrudDTO.setError("No team have been found");
			return new ResponseEntity<TeamCrudDTO>(teamCrudDTO, HttpStatus.NOT_FOUND);
		} catch (Exception e){
			teamCrudDTO.setError("Server Error");
			e.printStackTrace();
			
			return new ResponseEntity<TeamCrudDTO>(teamCrudDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Put Method for Update team data
	 * @param teamDTO
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<TeamCrudDTO> updateTeamDTO(@RequestBody TeamDTO teamDTO){
		
		TeamCrudDTO teamCrudDTO = new TeamCrudDTO();
		try {
			teamCrudDTO = teamCRUDController.updateTeam(teamDTO);
			
			if(teamCrudDTO == null){
				return new ResponseEntity<TeamCrudDTO>(teamCrudDTO, HttpStatus.BAD_REQUEST);
			}
			else{
				return new ResponseEntity<TeamCrudDTO>(teamCrudDTO, HttpStatus.OK);
			}
			
		} catch (BadInputException e) {
			teamCrudDTO.setError("Bad Input");
			return new ResponseEntity<TeamCrudDTO>(teamCrudDTO, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException e) {
			teamCrudDTO.setError("Not Found");
			return new ResponseEntity<TeamCrudDTO>(teamCrudDTO, HttpStatus.NOT_FOUND);
		} catch (IllegalStateTransitionException e) {
			teamCrudDTO.setError("Illegal state transition");
			return new ResponseEntity<TeamCrudDTO>(teamCrudDTO, HttpStatus.FORBIDDEN);
		} catch (DBException e) {
			teamCrudDTO.setError("DB_Exception");
			return new ResponseEntity<TeamCrudDTO>(teamCrudDTO, HttpStatus.CONFLICT);
		} catch (Exception e){
			teamCrudDTO.setError("Internal Server Error");
			return new ResponseEntity<TeamCrudDTO>(teamCrudDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
	 * Post Method for Create new team
	 * @param teamDTO
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<TeamCrudDTO> createQuestionDTO(@RequestBody TeamDTO teamDTO){
		TeamCrudDTO teamCrudDTO = new TeamCrudDTO();
		try{
			teamCrudDTO = teamCRUDController.createTeam(teamDTO);
			System.out.println("Sending result OK");
			return new ResponseEntity<TeamCrudDTO>(teamCrudDTO, HttpStatus.CREATED);			
		} catch (BadInputException e){
			teamCrudDTO.setError("Bad Request");
			return new ResponseEntity<TeamCrudDTO>(teamCrudDTO, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			teamCrudDTO.setError("Server Error");
			return new ResponseEntity<TeamCrudDTO>(teamCrudDTO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
