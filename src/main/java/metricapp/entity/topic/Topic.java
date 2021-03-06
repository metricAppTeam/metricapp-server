package metricapp.entity.topic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import metricapp.utility.RandomGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeAlias("metricapp.Topic")
@Document(collection = "topics")
public class Topic {
	
	@Id
	private String id;
	@CreatedDate
	private Long creationDate;
	@Indexed(unique = true)
	private String name;
	private List<String> subscribers;
	
	public static Topic randomTopic() {
		Topic topic = new Topic();
		topic.setId(RandomGenerator.randomString());
		topic.setCreationDate(RandomGenerator.randomLong());
		topic.setName(RandomGenerator.randomString());
		topic.setSubscribers(new ArrayList<String>());
		topic.setSubscribers(RandomGenerator.randomArrayList());
		return topic;
	}

}
