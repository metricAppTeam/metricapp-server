package metricapp.entity.event;

public enum EventScope {
	OGOAL ("OGOAL"),
	MGOAL ("MGOAL"),
	QUESTION ("QUESTION"),
	METRIC ("METRIC"),
	GRID ("GRID"),
	TEAM ("TEAM"),
	USER ("USER");
	
	private final String name;       

    private EventScope(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }
}
