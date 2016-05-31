package metricapp.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Element {
	
	
	@Id
	private String id;
	
	@Version
	private String version;
	
	private List<String> tags;
	
	private String releaseNote;
	
	private State state;
	
	@CreatedDate
	//private Date creationDate;
	private LocalDate creationDate = LocalDate.now();
	@LastModifiedDate
	//private Date lastVersionDate;
	private LocalDate lastVersionDate = LocalDate.now();

	//TODO: use metadata attribute for this
	
	
	
	
}
