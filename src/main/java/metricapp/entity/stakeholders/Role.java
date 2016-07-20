package metricapp.entity.stakeholders;

public enum Role {
	Questioner("Questioner"),
	Metricator("Metricator"),
	GQMExpert("GQM Expert");
	
	private final String busString;
	
	private Role(String busString){
		this.busString=busString;
	}
	
	public String getBusString(){
		return this.busString;
	}
}
