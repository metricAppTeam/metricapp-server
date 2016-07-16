package metricapp.service.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.team.TeamCrudDTO;
import metricapp.dto.team.TeamDTO;
import metricapp.entity.stakeholders.Team;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.IDException;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.controller.TeamCRUDInterface;
import metricapp.service.spec.repository.TeamRepository;

@Service
public class TeamCRUDController implements TeamCRUDInterface {

	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory; 
		
	@Override
	public TeamCrudDTO getTeamById(String id) throws NotFoundException, BadInputException, IDException {
		
		if(id == null){
			throw new IDException("Id cannot be null");
		}
		
		Team team = teamRepository.findTeamById(id);
		TeamCrudDTO teamCrudDTO = new TeamCrudDTO();
		try{
			teamCrudDTO.addTeamToList(modelMapperFactory.getStandardModelMapper().map(team, TeamDTO.class));
			return teamCrudDTO;
		}
		catch(IllegalArgumentException e){
			throw new NotFoundException("No Team found");
		}
	}
	
	
	@Override
	public TeamCrudDTO createTeam(TeamDTO teamDTO) throws BadInputException, IDException{
		
		
		if (teamDTO.getId() == null) {
			throw new BadInputException("Id field is empty");
		}
		
		Team newTeam = modelMapperFactory.getStandardModelMapper().map(teamDTO, Team.class);
		
		if(teamRepository.exists(newTeam.getId())){
			throw new IDException("ID it is already in use");
		}
		
		TeamCrudDTO teamCrudDTO = new TeamCrudDTO();
		teamCrudDTO.setRequest("create Team");
		
		teamCrudDTO.addTeamToList(
					modelMapperFactory.getStandardModelMapper().map(teamRepository.save(newTeam), TeamDTO.class));
		
		return teamCrudDTO;
	}

	@Override
	public TeamCrudDTO updateTeam(TeamDTO teamDTO)  
			throws BadInputException, NotFoundException, IllegalStateTransitionException, DBException{
			// DA FARE
		return null;
	}
	
	@Override
	public TeamCrudDTO getAllTeams(){
			
		TeamCrudDTO teamCrudDTO = new TeamCrudDTO();
		
		Iterator<Team> teamIter = teamRepository.findAll().iterator();
	
		while(teamIter.hasNext()){
			teamCrudDTO.addTeamToList(modelMapperFactory.getStandardModelMapper().map(teamIter.next(), TeamDTO.class));
		}
		return teamCrudDTO;
	}
}