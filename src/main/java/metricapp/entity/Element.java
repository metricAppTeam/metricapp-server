package metricapp.entity;

import java.util.ArrayList;
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
	private String creatorId;
	@CreatedDate
	private Date creationData;
	@LastModifiedDate
	private Date lastVersionData;
	
	//TODO: use metadata attribute for this
	
	public void setTags(String ...strings){
		ArrayList<String> tagList = new ArrayList<String>();
		for(String tag : strings){
			tagList.add(tag);
		}
		this.tags=tagList;
	}
	
	
}
