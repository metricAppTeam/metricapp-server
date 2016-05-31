package metricapp.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
@Data
public class MetadataDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6227428458702853303L;
	public String id;
	public String version;
	public List<String> tags;
	public String creationDate;
	public String lastVersionDate;
	public String creatorId;
	public String state;
	public String releaseNote;
}
