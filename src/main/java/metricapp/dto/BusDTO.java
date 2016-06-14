package metricapp.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class BusDTO implements Serializable {
    public String tag;
    public BusContent content;
    public String originAdress;
    public String id;
    public String resolvedAdress;

    @Data
    public class BusContent{
        public String objIdLocalToPhase;
        public String typeObj;
        public String instance;
        public String busVersion;
        public ArrayList<String> tags;
        public Serializable payload;
    }
}
