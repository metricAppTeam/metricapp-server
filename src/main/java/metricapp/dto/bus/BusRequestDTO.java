package metricapp.dto.bus;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class BusRequestDTO extends BusDTO {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6399985292317129080L;
	public ArrayList<String> content;
}
