package metricapp.dto.question;

import org.modelmapper.PropertyMap;

import metricapp.entity.question.Question;

public class QuestionMap extends PropertyMap<Question, QuestionDTO>{

	@Override
	protected void configure() {
		map().getMetadata().setId(source.getId());
		map().getMetadata().setCreatorId(source.getCreatorId());
		map().getMetadata().setReleaseNote(source.getReleaseNote());
		map().getMetadata().setVersion(source.getVersion());
		map().getMetadata().setTags(source.getTags());
		map().getMetadata().setState(source.getState());
		map().getMetadata().setLastVersionDate(source.getLastVersionDate());
		map().getMetadata().setCreationDate(source.getCreationDate());
		map().setFocus(source.getFocus());
		map().setSubject(source.getSubject());
		map().setDescription(source.getDescription());
		
	}

}
