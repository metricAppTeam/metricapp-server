package metricapp.entity.user;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import metricapp.entity.stakeholders.Person;
import metricapp.entity.stakeholders.Role;

@Setter
@Getter
@Document
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=false)
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
	private Role role; 
	
	public enum Gender{
		Male,
		Female
	}
}
