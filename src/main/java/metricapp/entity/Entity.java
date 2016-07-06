package metricapp.entity;

public enum Entity {
	MeasurementGoal ("phase2"),
	Question("phase2"),
	Metric("phase2"),
	ContextFactor("phase1"),
	Assumption("phase1"),
	InstanceProject("phase1"),
	User("phase2"),
	OrganizationalGoal("phase1");
	
	private final String phase;
	
	private Entity(String phase){
		this.phase=phase;
	}
	
	public String getPhase(){
		return this.phase;
	}
}
