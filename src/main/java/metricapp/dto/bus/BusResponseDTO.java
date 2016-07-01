package metricapp.dto.bus;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusResponseDTO extends BusDTO {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2613417517943390397L;
	public ArrayList<String> content;
}
