package metricapp.dto.question;

import java.io.Serializable;

import lombok.Data;
import metricapp.dto.MetadataDTO;

@Data
public class QuestionDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2279973123016377422L;
	
	public MetadataDTO metadata;
	public String focus;
	public String subject;
	public String description;
	
	public QuestionDTO(){
		setMetadata(new MetadataDTO());
	}
}
