package metricapp.entity.event;

public enum EventPhase {
	PHASE2_1 ("PHASE2_1"),
	PHASE2_2 ("PHASE2_2");
	
	private final String name;       

    private EventPhase(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }
}
