package metricator.entity.measurementGoal;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import metricator.entity.AbstractGoal;

@Document
@Data
@EqualsAndHashCode(callSuper=false)
public class MeasurementGoal extends AbstractGoal{
	
	@Id
	private String id;
	
	@Version
	private Long version;
	
	private String object;
	
	private String purpose;
	
	private String qualityFocus;
	
	private String viewPoint;
	
	@DBRef
	private Iterable<Context> contexts;
	
	@DBRef
	private Iterable<Assumption> assumptions;
	
	@DBRef
	private InterpretationModel interpretationModel;
	
	@DBRef
	private Iterable<VariationFactors> variationFactors;
	
	@DBRef
	private Iterable<Metric> metrics;

	
}
