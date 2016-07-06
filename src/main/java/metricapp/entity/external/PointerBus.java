package metricapp.entity.external;

import lombok.Data;
import metricapp.utility.RandomGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Data
public class PointerBus {

	public String objIdLocalToPhase;
	public String typeObj;

	@Id
	public String instance;

	@Version
	public String busVersion;
	@JsonProperty("tags")
	public List<String> busTags;

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
		this.busTags = new ArrayList<String>();
		this.busVersion= "";
		this.instance= "";
		this.objIdLocalToPhase= "";
		this.typeObj = "";
	}
}