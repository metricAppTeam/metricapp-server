package metricapp.dto.topic;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import metricapp.dto.DTO;

@Getter
@Setter()
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class TopicDTO extends DTO implements Serializable {

	private static final long serialVersionUID = -7106528410253143597L;
	
	private String id;
	private LocalDate creationDate;
	private String name;
	private List<String> subscribers;
	
	public TopicDTO() {
		super();
	}
	
}
