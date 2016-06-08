package metricapp.dto;

import java.io.Console;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lombok.Data;
import metricapp.entity.Entity;
import metricapp.entity.State;
import metricapp.service.RandomGenerator;

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
	
	public void randomAttributes() throws IllegalArgumentException,IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException{
		Field[] attributes = this.getClass().getDeclaredFields();	
		for (Field field : attributes) {
			//necessary for private fields
			field.setAccessible(true);
			
			//set the attribute
			RandomGenerator.randomAttribute(this, field);
		}
	}
	
	public void setCreationDate(String date){
		this.creationDate = date;
	}
	
	public void setLastVersionDate(String date){
		this.lastVersionDate = date;
	}

	public void setCreationDate(LocalDate date){
		this.creationDate = date.toString();
	}
	
	public void setLastVersionDate(LocalDate date){
		this.lastVersionDate = date.toString();
	}
	

	
}
