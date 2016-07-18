package metricapp.entity.stakeholders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Document
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=false)
@ToString
public class User extends Person {
	
	@Id
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private Gender gender;
	private LocalDate birthday;
	private String pic;
	private String email;
	private String mobile;
	private String online;
	private String website;
	private String bio;
	private Role role; 
	
	public enum Gender{
		Male,
		Female
	}
	
	/**
	 * This method is needed to convert parameter dob of Bus to birthday. 
	 * According to Bus documentation, date are stored in string of type dd-MM-yyyy
	 * @param date
	 */
	public void setBirthday(String date){
		if(date!=null){
			try{
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				this.birthday = LocalDate.parse(date, formatter);
			}catch(Exception e){
				return;
			}
		}
	}
	
	public String getBirthdayAsString(){
		if(this.getBirthday() == null){
			return this.getBirthday().toString();
		}
		return null;
	}
}
