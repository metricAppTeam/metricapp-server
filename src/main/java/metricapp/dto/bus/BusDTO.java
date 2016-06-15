package metricapp.dto.bus;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusDTO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6765156160010440068L;
	public String tag;
    public String originAdress;
    public String id;
    public String resolvedAdress;
}
