package metricapp.dto.metric;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import metricapp.dto.MetadataDTO;
import metricapp.entity.metric.ScaleType;


@Data
public class MetricDTO implements Serializable{

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
	public String set;
	public MetadataDTO metadata;
	public ScaleType scaleType;

	
}
