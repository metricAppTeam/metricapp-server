package metricapp.dto.topic;

import org.modelmapper.PropertyMap;

import metricapp.entity.topic.Topic;

public class TopicDTOMap extends PropertyMap<TopicDTO, Topic> {

	@Override
	protected void configure() {
		map().setId(source.getId());
		map().setCreationDate(source.getCreationDate());
		map().setName(source.getName());
		map().setSubscribers(source.getSubscribers());
	}

}
