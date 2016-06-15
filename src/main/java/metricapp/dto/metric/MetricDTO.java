package metricapp.dto.metric;

import java.io.Serializable;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import metricapp.dto.DTO;
import metricapp.dto.bus.PointerBusDTO;
import metricapp.entity.external.PointerBus;
import metricapp.entity.metric.ScaleType;
import metricapp.entity.metric.Set;


@Getter
@Setter()
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class

MetricDTO extends DTO implements Serializable{

	private static final long serialVersionUID = -2073939437602304884L;
	public String name;
	public String description;
	public String metricatorId;
	public boolean hasMax;
	public boolean hasMin;
	public double max;
	public double min;
	public boolean hasUserDefinedList;
	public List<String> userDefinedList;
	public String unit;
	public boolean isOrdered;
	public Set set;
	public ScaleType scaleType;
	
	public MetricDTO(){
		super();
	}
	
	

	
}
