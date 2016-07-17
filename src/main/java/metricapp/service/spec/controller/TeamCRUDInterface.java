package metricapp.service.spec.controller;

import metricapp.dto.team.TeamCrudDTO;
import metricapp.dto.team.TeamDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.IDException;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;

public interface TeamCRUDInterface {

	public TeamCrudDTO getTeamById(String id) throws NotFoundException, BadInputException, IDException;
	public TeamCrudDTO createTeam(TeamDTO dto) throws BadInputException, IDException;
	public TeamCrudDTO getAllTeams();
	public TeamCrudDTO deleteUserByUsername(String id, String username)throws IllegalStateTransitionException, NotFoundException, IDException, DBException;
	public TeamCrudDTO addUserToTeam(TeamDTO teamDTO) throws IllegalStateTransitionException, NotFoundException, IDException, DBException;
	public TeamCrudDTO upgradeTeamInfo(TeamDTO teamDTO) throws NotFoundException, IDException, DBException;
	
}
