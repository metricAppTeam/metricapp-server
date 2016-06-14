package metricapp.dto.bus;

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
    public class BusContent extends MetadataBusDTO{
        public Serializable payload;
    }
}
