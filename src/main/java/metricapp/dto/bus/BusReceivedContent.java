package metricapp.dto.bus;

import java.util.ArrayList;

import lombok.Data;

@Data
public class BusReceivedContent<T> {

	
	public String busVersion;
	public String instance;
	public String payload;
	public String objIdLocalToPhase;
	public String typeObj;
	public ArrayList<String> tags;

}
