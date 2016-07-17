package metricapp.entity.topic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import metricapp.utility.RandomGenerator;

@Data
@TypeAlias("metricapp.Topic")
@Document(collection = "topics")
public class Topic {
	
	@Id
	private String id;
	@CreatedDate
	private LocalDate creationDate;
	@Indexed(unique = true)
	private String name;
	private List<String> subscribers;
	
	public static Topic randomTopic() {
		Topic topic = new Topic();
		topic.setId(RandomGenerator.randomString());
		topic.setCreationDate(RandomGenerator.randomLocalDate());
		topic.setName(RandomGenerator.randomString());
		topic.setSubscribers(new ArrayList<String>());
		topic.setSubscribers(RandomGenerator.randomArrayList());
		return topic;
	}

}
