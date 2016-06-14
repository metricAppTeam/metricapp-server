package metricapp.dto.bus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusResponseDTO extends BusDTO {
    public String content;
}
