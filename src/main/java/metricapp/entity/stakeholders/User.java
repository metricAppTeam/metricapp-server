package metricapp.entity.stakeholders;

import java.time.LocalDate;

import lombok.Data;

@Data
public class User {
	
	private String role;
	private String description;
	private String name;
	private String surname;
	private LocalDate dob;
	private Gender gender;
	private String pic;
	
	private enum Gender{
		Male,
		Female;
	}
}
