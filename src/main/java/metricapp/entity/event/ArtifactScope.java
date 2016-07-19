package metricapp.entity.event;

public enum ArtifactScope {
	OGOAL ("OGOAL"),
	MGOAL ("MGOAL"),
	QUESTION ("QUESTION"),
	METRIC ("METRIC"),
	GRID ("GRID"),
	TEAM ("TEAM"),
	USER ("USER");
	
	private final String name;       

    private ArtifactScope(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }
}
