package metricapp.dto;

import java.util.List;

public class MetricDTO {
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
	
}
