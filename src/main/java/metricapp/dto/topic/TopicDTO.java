package metricapp.dto.topic;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class TopicDTO implements Serializable {

	private static final long serialVersionUID = -7106528410253143597L;
	
	private String id;
	private LocalDate creationDate;
	private String name;
	private List<String> subscribers;
	
}
