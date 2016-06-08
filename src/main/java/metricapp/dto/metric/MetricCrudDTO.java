package metricapp.dto.metric;

import java.util.ArrayList;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import metricapp.dto.DTO;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
public class MetricCrudDTO extends DTO {
	
	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 8336783368496627133L;
	public ArrayList<MetricDTO> metricsDTO;
	
	
	public MetricCrudDTO(){
		this.setMetricsDTO(new ArrayList<MetricDTO>());
	}
	
	public void addMetricToList(MetricDTO metric){
		try{
			this.metricsDTO.add(metric);
		}
		catch(NullPointerException e){
			this.metricsDTO = new ArrayList<MetricDTO>();
			this.metricsDTO.add(metric);
		}
		
	}
}
