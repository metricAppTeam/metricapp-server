package metricapp.entity.topic;

public enum TopicCategory {
	NORMAL ("NORMAL"),
	BUS ("BUS");
	
	private final String name;       

    private TopicCategory(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }
}
