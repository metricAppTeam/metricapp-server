package metricapp.entity.question;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import metricapp.entity.Element;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Document
public class Question extends Element{
	private String focus;
	private String subject;
	private String description;
	private String questionerId;

}



