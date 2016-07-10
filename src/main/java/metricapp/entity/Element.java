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

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import metricapp.utility.RandomGenerator;
/**
 * Element is a generic object in our Db and that can be pushed to Bus Storage.
 * Examples of Elements are MeasurementGoal, Metric, Assumption, ContextFactor...
 */
@Document
@Data
public class Element extends Object {

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
	@LastModifiedDate
	private LocalDate lastVersionDate;
	/**
	 * private id to modify object on bus
	 */
	
	private String secretToken;
	/**
	 * version of the last approved entity on bus
	 */
	@JsonProperty("busVersion")
	private String versionBus;
	private Entity entityType;

	public void setTagsByList(String... strings) {
		ArrayList<String> tagList = new ArrayList<String>();
		for (String tag : strings) {
			tagList.add(tag);
		}
		this.tags = tagList;
	}
	/*
	 * public void setTags(List<String> tags) { this.tags = tags; }
	 */

	@JsonProperty("creationDate")
	public void setCreationDate(String date) {
		if (date != null) {
			this.creationDate = LocalDate.parse(date);
		}
	}

	@JsonProperty("lastVersionDate")
	public void setLastVersionDate(String date) {
		if (date != null) {
			this.lastVersionDate = LocalDate.parse(date);
		}

	}

	@JsonIgnore
	public void setCreationDate(LocalDate date) {
		this.creationDate = date;
	}

	@JsonIgnore
	public void setLastVersionDate(LocalDate date) {
		this.lastVersionDate = date;
	}

	@JsonGetter("creationDate")
	public String getCreationDateString(){
		if(lastVersionDate == null){
			return null;
		}else{
			return creationDate.toString();
		}
	}
	
	@JsonGetter("lastVersionDate")
	public String getLastVersionDateString(){
		if(lastVersionDate == null){
			return null;
		}else{
			return lastVersionDate.toString();
		}
	}
	
		
	
	/**
	 * randomAttributes fills every attribute of the entity.
	 * it can throw a large number of Exception, due to Reflection Implementation.
	 * See RandomGenerator documentation to specifications.
	 */
	public void randomAttributes() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException {
		Field[] attributes = this.getClass().getDeclaredFields();
		Class<?> actual = this.getClass();
		// when the function reaches Element, it stops
		while (!actual.getName().equals(Object.class.getName())) {
			for (Field field : attributes) {
				// necessary for private fields
				field.setAccessible(true);

				// set the attribute
				RandomGenerator.randomAttribute(this, field);
			}
			actual = actual.getSuperclass();
			attributes = actual.getDeclaredFields();
		}

	}
}
