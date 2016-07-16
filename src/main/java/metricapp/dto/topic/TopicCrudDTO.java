package metricapp.dto.topic;

import java.util.ArrayList;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import metricapp.dto.MessageDTO;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
public class TopicCrudDTO extends MessageDTO {
	
	private static final long serialVersionUID = 1576795169213279094L;
	
	private long count;	
	public ArrayList<TopicDTO> topicsDTO;
	
	
	public TopicCrudDTO() {
		this.setTopicsDTO(new ArrayList<TopicDTO>());
	}
	
	public void addTopicToList(TopicDTO topic) {
		try {
			this.topicsDTO.add(topic);
		} catch(NullPointerException e){
			this.topicsDTO = new ArrayList<TopicDTO>();
			this.topicsDTO.add(topic);
		}		
	}
}
