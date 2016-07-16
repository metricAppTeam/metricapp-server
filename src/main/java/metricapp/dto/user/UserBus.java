package metricapp.dto.user;


import java.util.ArrayList;
import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import metricapp.entity.stakeholders.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBus {

	String password;
	String role;
	String gender;
	String surname;
	String dob;
	String name;
	String pic;
	String username;
	String instance;
	String busVersion;
	String typeObj;
	
	public String getLastname(){
		return this.getSurname();
	}
	
	public String getFirstname(){
		return this.getName();
	}
	
	public String getBirthday(){
		return this.dob;
	}
	
	
	/**
	 * due to different role implementation, we use the highest ranking role
	 * @return
	 */
	public Role getRole(){
		String cleanArray = role.replace("[","");
		cleanArray = role.replace("]", "");
		
		//according to Bus Documentation there's a space after the dot. 
		String[] list = cleanArray.split(", ");
		
		ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(list));
		if (arrayList.contains(Role.GQMExpert.name())){
			return Role.GQMExpert;
		}
		if(arrayList.contains(Role.Metricator.name())){
			return Role.Metricator;
		}
		if(arrayList.contains(Role.Questioner)){
			return Role.Questioner;
		}
		else return null;
		
	}

}
