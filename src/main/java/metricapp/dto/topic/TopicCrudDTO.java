package metricapp.dto.topic;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import metricapp.dto.SimpleDTO;
import metricapp.dto.topic.TopicDTO;
import metricapp.entity.topic.Topic;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
public class TopicCrudDTO extends SimpleDTO {
	
	private static final long serialVersionUID = 1576795169213279094L;
	
	private long count;	
	public ArrayList<TopicDTO> topicsDTO;	
	
	public TopicCrudDTO() {
		this.setTopicsDTO(new ArrayList<TopicDTO>());
	}
	
	public void addTopicDTO(TopicDTO topic) {
		if (this.topicsDTO == null) {
			this.topicsDTO = new ArrayList<TopicDTO>();
		}
		this.topicsDTO.add(topic);		
		this.count = this.topicsDTO.size();
	}
	
	public void addAllTopicDTO(List<TopicDTO> topics) {
		if (this.topicsDTO == null) {
			this.topicsDTO = new ArrayList<TopicDTO>();
		}
		for (TopicDTO topic : topics) {
			this.topicsDTO.add(topic);
		}
		this.count = this.topicsDTO.size();
	}
	
	public void addTopic(Topic topic, ModelMapper mapper) {
		if (this.topicsDTO == null) {
			this.topicsDTO = new ArrayList<TopicDTO>();
		}
		TopicDTO topicDTO = mapper.map(topic, TopicDTO.class);
		this.topicsDTO.add(topicDTO);		
		this.count = this.topicsDTO.size();
	}
	
	public void addAllTopic(List<Topic> topics, ModelMapper mapper) {
		if (this.topicsDTO == null) {
			this.topicsDTO = new ArrayList<TopicDTO>();
		}
		for (Topic topic : topics) {
			TopicDTO topicDTO = mapper.map(topic, TopicDTO.class);
			this.topicsDTO.add(topicDTO);
		}
		this.count = this.topicsDTO.size();
	}
}
