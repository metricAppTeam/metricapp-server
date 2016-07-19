package metricapp.service.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.user.UserCrudDTO;
import metricapp.dto.user.UserDTO;
import metricapp.entity.stakeholders.User;
import metricapp.exception.BadInputException;
import metricapp.exception.BusException;
import metricapp.exception.DBException;
import metricapp.exception.IDException;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;
import metricapp.service.repository.BusUserRepository;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.controller.UserCRUDInterface;
import metricapp.service.spec.repository.UserRepository;

@Service
public class UserCRUDController implements UserCRUDInterface {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BusUserRepository busUserRepository;
	
	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory; 
		
	@Override
	public UserCrudDTO getUserByUsername(String username) throws NotFoundException, BadInputException {
		
		if(username == null){
			throw new BadInputException("Id cannot be null");
		}
		
		User user = userRepository.findUserByUsername(username);
		
		UserCrudDTO userCrudDTO = new UserCrudDTO();
		try{
			userCrudDTO.addUserToList(modelMapperFactory.getLooseModelMapper().map(user, UserDTO.class));
			return userCrudDTO;
		}
		catch(IllegalArgumentException e){
			throw new NotFoundException("No User found");
		}
	}
	
	
	@Override
	public UserCrudDTO createUser(UserDTO userDTO) throws BadInputException, IDException, DBException, BusException{
		
		if (userDTO.getUsername() == null) {
			throw new BadInputException("Username field is empty");
		}
		if (userDTO.getPassword() == null) {
			throw new BadInputException("Password field is empty");
		}
		if (userDTO.getLastname() == null) {
			throw new BadInputException("Lastname field is empty");
		}
		if (userDTO.getFirstname() == null) {
			throw new BadInputException("Firstname field is empty");
		}
		if (userDTO.getRole() == null) {
			throw new BadInputException("Role field is empty");
		}
		if (userDTO.getEmail() == null) {
			throw new BadInputException("Email field is empty");
		}
		if (userDTO.getBirthday() == null) {
			throw new BadInputException("Birthday field is empty");
		}
		if (userDTO.getMobile() == null) {
			throw new BadInputException("Mobile field is empty");
		}
		if (userDTO.getOnline() == null) {
			userDTO.setOnline("false");
		}
		if (userDTO.getPic() == null) {
			userDTO.setPic("/uploads/user-picture.svg");
		}
		if (userDTO.getGender() == null) {
			throw new BadInputException("Gender field is empty");
		}
		if (userDTO.getBio() == null) {
			userDTO.setBio("Insert here your bio");
		}
		if (userDTO.getWebsite() == null) {
			userDTO.setWebsite("mywebsite.com");
		}
		
		User newUser;
		
		try{
			newUser = modelMapperFactory.getLooseModelMapper().map(userDTO, User.class);			
		}
		catch(Exception e){
			throw new DBException("Error in saving data in repository");
		}
		
		if(userRepository.exists(newUser.getUsername())){
			throw new IDException("Username it is already in use");
		}
		

		User busUser;
		try{
			busUser = busUserRepository.registerUser(newUser);
		}
		catch(Exception e){
			throw new BusException(e);
		}
		System.out.println("2");
		if(!(busUser.getUsername().equals(newUser.getUsername()) && busUser.getPassword().equals(newUser.getPassword()))){
			System.out.println(busUser.getUsername() + newUser.getUsername() + "," + busUser.getPassword() + newUser.getPassword());
			throw new BusException("Error in saving data in bus repository");
		}
		UserCrudDTO userCrudDTO = new UserCrudDTO();
		userCrudDTO.setRequest("create User");
		
		try{
			userCrudDTO.addUserToList(modelMapperFactory.getLooseModelMapper().map(userRepository.save(newUser), UserDTO.class));
		}
		catch(Exception e){
			throw new DBException("Error in saving data in repository");
		}
		return userCrudDTO;
	}

	@Override
	public UserCrudDTO updateUser(UserDTO userDTO)  
			throws BadInputException, NotFoundException, IllegalStateTransitionException, DBException, IDException{
		
		if(userDTO.getUsername() == null){
			throw new IDException("Id cannot be null");
		}
		
		User oldUser;
		
		if(userRepository.exists(userDTO.getUsername()))
			oldUser = userRepository.findUserByUsername(userDTO.getUsername());
		else{
			throw new NotFoundException("User not be found");
		}
		
		try{
			if(userDTO.getFirstname() != null)
				oldUser.setFirstname(userDTO.getFirstname());
			
			if(userDTO.getLastname() != null)
				oldUser.setLastname(userDTO.getLastname());
			
			if(userDTO.getGender() != null)
				oldUser.setGender(userDTO.getGender());
			
			if(userDTO.getRole() != null)
				oldUser.setRole(userDTO.getRole());
			
			if(userDTO.getEmail() != null)
				oldUser.setEmail(userDTO.getEmail());
			
			if(userDTO.getMobile() != null)
				oldUser.setMobile(userDTO.getMobile());
			
			if(userDTO.getPic() != null)
				oldUser.setPic(userDTO.getPic());
			
			if(userDTO.getBirthday() != null)
				oldUser.setBirthday(userDTO.getBirthday());
			
			if(userDTO.getWebsite() != null)
				oldUser.setWebsite(userDTO.getWebsite());
			
			if(userDTO.getBio() != null)
				oldUser.setBio(userDTO.getBio());
		}
		catch(Exception e){
			throw new DBException("Error in saving data in repository");
		}
		
		UserCrudDTO userCrudDTO = new UserCrudDTO();
		userCrudDTO.setRequest("update User");
		
		try{
			userCrudDTO.addUserToList(
					modelMapperFactory.getLooseModelMapper().map(userRepository.save(oldUser), UserDTO.class));
			return userCrudDTO;
		}
		catch(IllegalArgumentException e){
			throw new NotFoundException("Impossible");
		}
	}
	
	@Override
	public UserCrudDTO getAllUsers(){
		
		UserCrudDTO userCrudDTO = new UserCrudDTO();
		
		Iterator<User> userIter = userRepository.findAll().iterator();
		
		while(userIter.hasNext()){
			
			userCrudDTO.addUserToList(modelMapperFactory.getLooseModelMapper().map(userIter.next(), UserDTO.class));
			
		}	
		return userCrudDTO;
	}

	@Override
	public UserCrudDTO getBusUserByUsername(String username) throws NotFoundException, BadInputException, BusException {
		
		if(username == null){
			throw new BadInputException("Id cannot be null");
		}
		
		User busUser;
		try{
			busUser = busUserRepository.findUserByUsername(username);
		}
		catch(Exception e){
			throw new BusException("User not found in bus repository");
		}
		
		UserCrudDTO userCrudDTO = new UserCrudDTO();
		try{
			userCrudDTO.addUserToList(modelMapperFactory.getLooseModelMapper().map(busUser, UserDTO.class));
			return userCrudDTO;
		}
		catch(IllegalArgumentException e){
			throw new NotFoundException("No User found");
		}
	}

}