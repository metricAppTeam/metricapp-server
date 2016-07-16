package metricapp.service.spec.controller;

import metricapp.dto.team.TeamCrudDTO;
import metricapp.dto.team.TeamDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;

public interface TeamCRUDInterface {

	public TeamCrudDTO getTeamById(String id) throws NotFoundException, BadInputException;
	public TeamCrudDTO createTeam(TeamDTO dto) throws BadInputException;
	public TeamCrudDTO updateTeam(TeamDTO dto) throws BadInputException, NotFoundException, IllegalStateTransitionException, DBException;
	public TeamCrudDTO getAllTeams();
}