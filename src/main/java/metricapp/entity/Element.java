package metricapp.entity;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import metricapp.service.RandomGenerator;

@Document
@Data
public class Element extends Object{
	
	
	@Id
	private String id;
	@Version
	private String version;
	private List<String> tags;
	private String releaseNote;
	private State state;
	private String creatorId;
	@CreatedDate
	private LocalDate creationDate;
	//private Date creationDate;
	//private SimpleDateFormat creationDate;
	@LastModifiedDate
	private LocalDate lastVersionDate;
	//private Date lastVersionDate;
	
	//TODO: use metadata attribute for this
	//private SimpleDateFormat lastVersionDate;
	
	private Entity entityType;
	

	public void setTagsByList(String ...strings){
		ArrayList<String> tagList = new ArrayList<String>();
		for(String tag : strings){
			tagList.add(tag);
		}
		this.tags=tagList;
	}
	/*
	public void setTags(List<String> tags) {
		this.tags = tags;	
	}*/
	/*
	public void setCreationDate(String date){
		this.creationDate = new SimpleDateFormat(date);

	}*/
	
	/*
	public void setLastVersionDate(String date){
		this.lastVersionDate = new SimpleDateFormat(date);
	}*/

	
	/*
	 * randomAttributes fills every attribute of the entity. 
	 * 
	 * 
	 * */
	public void randomAttributes() throws IllegalArgumentException,IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException{
		Field[] attributes = this.getClass().getDeclaredFields();
		Class<?> actual = this.getClass();
		//when the function reaches Element, it stops
		while(!actual.getName().equals(Object.class.getName())){
			for (Field field : attributes) {
				//necessary for private fields
				field.setAccessible(true);
				
				//set the attribute
				RandomGenerator.randomAttribute(this, field);
			}
			actual = actual.getSuperclass();
			attributes = this.getClass().getSuperclass().getDeclaredFields();
		}
		
	}
	
}
