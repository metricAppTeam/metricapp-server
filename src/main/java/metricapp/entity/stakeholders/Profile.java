package metricapp.entity.stakeholders;

import java.time.LocalDate;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class Profile {
	private String firstname;
	private String lastname;
	private Gender gender;
	private LocalDate dob;
	private String pic;
	private String email;
	private String mobile;
	private String online;
	
	public enum Gender{
		Male,
		Female
	}
}
