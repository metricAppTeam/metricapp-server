package metricapp.entity.metric;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import metricapp.entity.Element;
import metricapp.entity.stakeholders.Metricator;

@Data
@Document
public class Metric extends Element{
	
	private String name;
	private String description;
	
	@DBRef
	private Metricator metricator;
	
	private boolean hasMax;
	private boolean hasMin;
	private boolean hasUserDefinedList;
	private boolean isOrdered;
	
	private double max;
	private double min;
	private List<String> userDefinedList;
	private String unit;
	private ScaleType scaleType;
	private Set set;
	
	
	
	
}
