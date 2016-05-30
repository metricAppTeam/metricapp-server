package metricapp.entity.measurementGoal;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import metricapp.entity.AbstractGoal;
import metricapp.entity.metric.Metric;
import metricapp.entity.question.Question;
import metricapp.entity.stakeholders.Metricator;
import metricapp.entity.stakeholders.Questioner;

@Document
@Data
@EqualsAndHashCode(callSuper=false)
public class MeasurementGoal extends AbstractGoal{
	
	
	
	private String object;
	
	private String purpose;
	
	private String qualityFocus;
	
	private String viewPoint;
	
	@DBRef
	private Iterable<Context> contexts;
	
	@DBRef
	private Iterable<Assumption> assumptions;
	
	private InterpretationModel interpretationModel;
	
	@DBRef
	private Iterable<Metric> metrics;
	
	@DBRef
	private Iterable<Question> questions;
	
	@DBRef 
	private Metricator metricator;

	@DBRef
	private Iterable<Questioner> questioners;
	
	

	
}
