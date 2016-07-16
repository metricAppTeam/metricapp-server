package metricapp.entity.stakeholders;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Document
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Team extends Person {
	
	@Id
	private String id;
	private String name;
	private String grid_name;
	private LocalDate ts_create;
	private LocalDate ts_update;
	private ArrayList<User> expert;
	private ArrayList<User> questioners;
	private ArrayList<User> metricators;
	
}
