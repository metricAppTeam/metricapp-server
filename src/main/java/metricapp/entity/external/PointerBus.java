package metricapp.entity.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import metricapp.utility.RandomGenerator;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class PointerBus {

	public String objIdLocalToPhase;
	public String typeObj;

	
	public String instance;

	
	public String busVersion;
	@JsonProperty("tags")
	public List<KeyValue> busTags;

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
	
	public PointerBus(){
		this.busTags = new ArrayList<KeyValue>();
		this.busVersion= "";
		this.instance= "";
		this.objIdLocalToPhase= "";
		this.typeObj = "";
	}
}