package metricapp.dto.user;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import metricapp.entity.stakeholders.Role;

@Data
@AllArgsConstructor
public class UserBus {

	String password;
	public String role;
	String gender;
	String surname;
	String dob;
	String name;
	String pic;
	String username;
	String instance;
	String busVersion;
	String typeObj;
	
	public UserBus(){
		this.typeObj="";
		this.busVersion="";
		this.instance="";
	}
	
	@JsonIgnore
	public String getLastname(){
		return this.getSurname();
	}
	
	public void setLastname(String surname){

		this.setSurname(surname);
	}
	@JsonIgnore
	public String getFirstname(){
		return this.getName();
	}
	
	public void setFirstname(String firstname){
		this.setName(firstname);
	}
	@JsonIgnore
	public String getBirthday(){
		return this.dob;
	}
	@JsonIgnore
	public void setBirthday(LocalDate date){
		if(date!=null){
			this.setBirthday(date.toString());
		}
	}
	
	public void setBirthday(String date){
		this.setDob(date);
	}
	
	@JsonSetter
	public void setRole(String role){
		this.role = "[" + role + "]";
	}
	
	@JsonIgnore
	public void setRole(Role role){
		this.setRole(role.name());
	}
	@JsonProperty(value="role")
	public String getRoleString(){
		return this.role;
	}
	/**
	 * due to different role implementation, we use the highest ranking role
	 * @return
	 */
	@JsonIgnore
	public Role getRole(){
		String cleanArray = role.replace("[","").replace("]", "");
		
		//according to Bus Documentation there's a space after the dot. 
		String[] list = cleanArray.split(", ");
		
		ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(list));
		if (arrayList.contains(Role.GQMExpert.name())){
			return Role.GQMExpert;
		}
		if(arrayList.contains(Role.Metricator.name())){
			return Role.Metricator;
		}
		if(arrayList.contains(Role.Questioner.name())){
			return Role.Questioner;
		}
		else return null;
		
	}

}