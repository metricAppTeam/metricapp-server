package hello;

public class Goal_json {
	private String id;
	private String def;
	
	public Goal_json(String id, String def) {		
		this.id = id;
		this.def = def;
	}
	
	public Goal_json(){
		;
	}
	
	
	public String getDef() {
		return def;
	}
	public String getId() {
		return id;
	}
	public void setDef(String def) {
		this.def = def;
	}
	
	public void setId(String id) {
		this.id = id;
	}

}
