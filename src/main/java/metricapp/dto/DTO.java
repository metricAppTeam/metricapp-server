package metricapp.dto;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import lombok.Data;
import metricapp.utility.RandomGenerator;

@Data
public class DTO implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -5876831761186189543L;

	/**
	 * 
	 */
	
	
	public MetadataDTO metadata;
	
	public DTO(){
		this.setMetadata(new MetadataDTO());
	}
	
	
	public void randomAttributes() throws IllegalArgumentException,IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException{
		Field[] attributes = this.getClass().getDeclaredFields();
		Class<?> actual = this.getClass();
		//when the function reaches DTO, it stops
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
		this.getMetadata().randomAttributes();
	}

	
}
