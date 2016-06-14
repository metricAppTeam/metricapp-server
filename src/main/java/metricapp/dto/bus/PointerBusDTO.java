package metricapp.dto.bus;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class PointerBusDTO implements Serializable {
    public String objIdLocalToPhase;
    public String typeObj;
    public String instance;
    public String busVersion;
    public ArrayList<String> tags;

    public String toJsonString(){
        // TODO implement mapper
        return null;
    }
}
