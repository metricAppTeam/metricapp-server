package metricapp.entity.external;

import lombok.Data;
import metricapp.utility.RandomGenerator;
import org.springframework.data.annotation.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Data
public class PointerBus {

    public String objIdLocalToPhase;
    public String typeObj;

    @Id
    public String instance;


    public String busVersion;
    public List<String> busTags;


    public void randomAttributes() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException,
            NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException {
        Field[] attributes = this.getClass().getDeclaredFields();
        // when the function reaches Element, it stops
        
        for (Field field : attributes) {
            // necessary for private fields
            field.setAccessible(true);
            // set the attribute
            RandomGenerator.randomAttribute(this, field);
        }
    }
}