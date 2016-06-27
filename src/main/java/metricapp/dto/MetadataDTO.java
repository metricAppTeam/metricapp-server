package metricapp.dto;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import metricapp.entity.Entity;
import metricapp.entity.State;
import metricapp.utility.RandomGenerator;

@Data
public class MetadataDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6227428458702853303L;
	public String id;
	public String version;
	public List<String> tags;
	public String creationDate;
	public String lastVersionDate;
	public String creatorId;
	public State state;
	public String releaseNote;
	public Entity entityType;
	public String versionBus;
	
	public void randomAttributes() throws IllegalArgumentException,IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException{
		Field[] attributes = this.getClass().getDeclaredFields();	
		for (Field field : attributes) {
			//necessary for private fields
			field.setAccessible(true);
			
			//set the attribute
			RandomGenerator.randomAttribute(this, field);
		}
	}
	
	@JsonProperty("creationDate")
	public void setCreationDate(String date){
		this.creationDate = date;
	}
	
	@JsonProperty("lastVersionDate")
	public void setLastVersionDate(String date){
		this.lastVersionDate = date;
	}
	@JsonIgnore
	public void setCreationDate(LocalDate date){
		this.creationDate = date.toString();
	}
	@JsonIgnore
	public void setLastVersionDate(LocalDate date){
		this.lastVersionDate = date.toString();
	}
	
	
	

	
}
