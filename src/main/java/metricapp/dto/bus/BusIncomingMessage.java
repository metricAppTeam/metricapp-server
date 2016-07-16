package metricapp.dto.bus;

import java.util.UUID;


public class BusIncomingMessage {

  private static final long serialVersionUID = 1L;
   
    
    private String tag;
    
    private String operation;
    
    private String phase;
    
    private String data;
    
    private UUID id;
    
    private String version;
    
    private String destinationAdress;
 
    public BusIncomingMessage() {
        super();
    }
    public BusIncomingMessage(String tag, String operation, String phase, String destinationAdress, String data, UUID id,
            String version) {
        super();
        this.tag = tag;
        this.data = data;
        this.id = id;
        this.version = version;
        this.phase=phase;
        this.operation=operation;
        this.destinationAdress=destinationAdress;
    }
   
    public String getDestinationAdress() {
        return destinationAdress;
    }
    public void setDestinationAdress(String destinationAdress) {
        this.destinationAdress = destinationAdress;
    }
    public String getOperation() {
        return operation;
    }
    public void setOperation(String operation) {
        this.operation = operation;
    }
    public String getPhase() {
        return phase;
    }
    public void setPhase(String phase) {
        this.phase = phase;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

