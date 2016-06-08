package metricapp.dto.question;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import metricapp.dto.DTO;
import metricapp.dto.MetadataDTO;

@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class QuestionDTO extends DTO implements Serializable{
	
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
