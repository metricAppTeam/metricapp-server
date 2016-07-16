package metricapp.entity.stakeholders;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
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
	private String gridName;
	@CreatedDate
	private LocalDate tsCreate;
	@LastModifiedDate
	private LocalDate tsUpdate;
	private ArrayList<User> expert;
	private ArrayList<User> questioners;
	private ArrayList<User> metricators;

}
