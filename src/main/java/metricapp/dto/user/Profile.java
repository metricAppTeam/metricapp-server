package metricapp.dto.user;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Profile {
	private String firstname;
	private String lastname;
	private Gender gender;
	private LocalDate dob;
	private String pic;
	
	
	public enum Gender{
		Male,
		Female
	}
}
