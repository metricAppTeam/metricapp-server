package metricapp.dto.question;

import org.modelmapper.PropertyMap;

import metricapp.entity.question.Question;

public class QuestionDTOMap extends PropertyMap<QuestionDTO, Question>{

	@Override
	protected void configure() {
		map().setId(source.getMetadata().getId());
		map().setCreatorId(source.getMetadata().getCreatorId());
		map().setFocus(source.getFocus());
		map().setSubject(source.getSubject());
		map().setDescription(source.getDescription());
		map().setState(source.getMetadata().getState());
		map().setTags(source.getMetadata().getTags());
		map().setVersion(source.getMetadata().getVersion());
		map().setReleaseNote(source.getMetadata().getReleaseNote());
		map().setCreationDate(source.getMetadata().getCreationDate());
		map().setLastVersionDate(source.getMetadata().getLastVersionDate());
	}

}
