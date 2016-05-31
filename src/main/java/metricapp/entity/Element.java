package metricapp.entity;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

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
	private String creatorId;
	@CreatedDate
	private SimpleDateFormat creationDate;
	@LastModifiedDate
	private SimpleDateFormat lastVersionDate;
	
	
	
	public void setTagsByList(String ...strings){
		ArrayList<String> tagList = new ArrayList<String>();
		for(String tag : strings){
			tagList.add(tag);
		}
		this.tags=tagList;
	}
	
	public void setTags(List<String> tags) {
		this.tags = tags;	
	}
	
	public void setCreationDate(String date){
		this.creationDate = new SimpleDateFormat(date);

	}
	
	public void setLastVersionDate(String date){
		this.lastVersionDate = new SimpleDateFormat(date);
	}

	
	
}
