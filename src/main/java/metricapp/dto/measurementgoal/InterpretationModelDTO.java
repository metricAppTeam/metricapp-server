package metricapp.dto.measurementgoal;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class InterpretationModelDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String functionJavascript;
	public String queryNoSQL;

}