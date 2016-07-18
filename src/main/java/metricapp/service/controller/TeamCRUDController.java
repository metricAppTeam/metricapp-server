package metricapp.service.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.team.TeamCrudDTO;
import metricapp.dto.team.TeamDTO;
import metricapp.entity.stakeholders.Role;
import metricapp.entity.stakeholders.Team;
import metricapp.entity.stakeholders.User;
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
		newTeam.setTsCreate(LocalDate.now());
		newTeam.setTsUpdate(LocalDate.now());
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
	public TeamCrudDTO getAllTeams(){
			
		TeamCrudDTO teamCrudDTO = new TeamCrudDTO();
		
		Iterator<Team> teamIter = teamRepository.findAll().iterator();
	
		while(teamIter.hasNext()){
			teamCrudDTO.addTeamToList(modelMapperFactory.getStandardModelMapper().map(teamIter.next(), TeamDTO.class));
		}
		return teamCrudDTO;
	}

	@Override
	public TeamCrudDTO deleteUserByUsername(String id, String username) throws IllegalStateTransitionException, NotFoundException, IDException, DBException
	{
		Team oldTeam;
		
		try{
			oldTeam = teamRepository.findTeamById(id);
		}
		catch(Exception e){
			throw new NotFoundException("Team not be found in repository");
		}
	
		TeamCrudDTO updateTeamCrudDTO = new TeamCrudDTO();
		updateTeamCrudDTO.setRequest("upgrade Team");
		try
		{
			if(oldTeam.getMetricators().removeIf(p -> p.getUsername().equals(username)))
			{
				updateTeamCrudDTO.addTeamToList(modelMapperFactory.getStandardModelMapper().map(teamRepository.save(oldTeam), TeamDTO.class));
				return updateTeamCrudDTO;
			}else if(oldTeam.getQuestioners().removeIf(p -> p.getUsername().equals(username)))
			{
				updateTeamCrudDTO.addTeamToList(modelMapperFactory.getStandardModelMapper().map(teamRepository.save(oldTeam), TeamDTO.class));
				return updateTeamCrudDTO;
			}else
			{
				throw new DBException("User not be found in this team repository or it is an GQMExpert");
			}
			
		}catch(Exception e){
			throw new DBException("Error in saving new team in repository");
		}
	}
	
	
	
	@Override
	public TeamCrudDTO addUserToTeam(TeamDTO teamDTO) throws IllegalStateTransitionException, NotFoundException, IDException, DBException
	{
		Team oldTeam;
		
		try{
			oldTeam = teamRepository.findTeamById(teamDTO.getId());
		}
		catch(Exception e){
			throw new NotFoundException("Team not be found in repository");
		}
		
		User newMember;
		if(teamDTO.getExtrauser() != null)
			newMember = teamDTO.getExtrauser();
		else
			throw new NotFoundException("User not found in the request");
		
		try
		{
			if(newMember.getRole().equals(Role.GQMExpert))
			{
				ArrayList<User> expert;
				
				if(oldTeam.getExpert() == null)
					expert = new ArrayList<User>();
				else
					expert = oldTeam.getExpert();
				
				expert.add(newMember);
				oldTeam.setExpert(expert);
			}
			else if(newMember.getRole().equals(Role.Questioner))
			{
				ArrayList<User> questioners;
				
				if(oldTeam.getQuestioners() == null)
					questioners = new ArrayList<User>();
				else
					questioners = oldTeam.getQuestioners();
				
				questioners.add(newMember);
				oldTeam.setQuestioners(questioners);
			}
			
			else if(newMember.getRole().equals(Role.Metricator))
			{
				ArrayList<User> metricators;
				
				if(oldTeam.getMetricators() == null)
					metricators = new ArrayList<User>();
				else
					metricators = oldTeam.getMetricators();
				
				metricators.add(newMember);
				oldTeam.setMetricators(metricators);
			}
			else 
				throw new NotFoundException("User Role is empty");
		
		}catch(Exception e){
			throw new DBException("Error in saving new team in repository");
		}
		
		oldTeam.setExtrauser(null);
		
		
		TeamCrudDTO updateTeamCrudDTO = new TeamCrudDTO();
		updateTeamCrudDTO.setRequest("upgrade Team");
		
		
		try
		{
			oldTeam.setTsUpdate(LocalDate.now());
			updateTeamCrudDTO.addTeamToList(modelMapperFactory.getStandardModelMapper().map(teamRepository.save(oldTeam), TeamDTO.class));
			return updateTeamCrudDTO;
			
		}catch(Exception e){
			throw new DBException("Error in saving new team in repository");
		}
	}

	@Override
	public TeamCrudDTO upgradeTeamInfo(TeamDTO teamDTO) throws NotFoundException, DBException 
	{
		Team oldTeam;
		
		try{
			oldTeam = teamRepository.findTeamById(teamDTO.getId());
		}
		catch(Exception e){
			throw new NotFoundException("Team not be found in repository");
		}
		
		TeamCrudDTO updateTeamCrudDTO = new TeamCrudDTO();
		updateTeamCrudDTO.setRequest("upgrade Team");
		
		try
		{
			if(teamDTO.getName()!= null)
				if(!oldTeam.getName().equals(teamDTO.getName()))
					oldTeam.setName(teamDTO.getName());
		
			if(teamDTO.getGridName()!= null)
				if(!oldTeam.getGridName().equals(teamDTO.getGridName()))
					oldTeam.setGridName(teamDTO.getGridName());
			oldTeam.setTsUpdate(LocalDate.now());
			updateTeamCrudDTO.addTeamToList(modelMapperFactory.getStandardModelMapper().map(teamRepository.save(oldTeam), TeamDTO.class));
			return updateTeamCrudDTO;
			
		}catch(Exception e){
			throw new DBException("Error in saving new team in repository");
		}
	}
}