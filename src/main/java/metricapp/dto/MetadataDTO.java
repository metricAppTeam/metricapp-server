package metricapp.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import metricapp.entity.OrganizationalGoal;
import metricapp.entity.measurementGoal.Assumption;
import metricapp.entity.measurementGoal.Context;
import metricapp.entity.measurementGoal.InterpretationModel;
import metricapp.entity.measurementGoal.MeasurementGoal;
import metricapp.entity.metric.Metric;
import metricapp.entity.question.Question;
import metricapp.entity.stakeholders.Metricator;
import metricapp.entity.stakeholders.Questioner;

import metricapp.entity.State;
@Data
@EqualsAndHashCode(callSuper=false)

public class MetadataDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6227428458702853303L;
	public String id;
	public String version;
	public List<String> tags;
	public String creationDate;
	public String lastVersionDate;
	public String creatorId;
	public State state;
	public String releaseNote;
}
