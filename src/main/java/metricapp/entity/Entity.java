package metricapp.entity;

public enum Entity {
	MeasurementGoal (metricapp.entity.measurementGoal.MeasurementGoal.class),
	Question (metricapp.entity.question.Question.class),
	Metric (metricapp.entity.metric.Metric.class),
	ContextFactor (ContextFactor.class),
	Assumption (Assumption.class);
	
	private Class<Element> associatedClass;
	
	private Entity(Class<?> c){
		this.associatedClass=(Class<Element>) c;
	}
	
	public Class<Element> getAssociatedClass(){
		return this.associatedClass;
	}
}
