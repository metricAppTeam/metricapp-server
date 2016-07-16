/**
 * 
 */
package metricapp.entity.external;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * This pointer extends PointerBus, it's used in update and create requests and responses from and to the Bus.
 *
 */
@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class RichPointerBus extends PointerBus {

	private Object payload;
	
	public RichPointerBus(){
		super();
	}
	
	
}
