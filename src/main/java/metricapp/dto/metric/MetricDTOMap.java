package metricapp.dto.metric;


import org.modelmapper.PropertyMap;

import metricapp.entity.metric.Metric;

public class MetricDTOMap extends PropertyMap<MetricDTO, Metric> {

	@Override
	protected void configure() {
		
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		map().setId(source.getMetadata().getId());
		//map().setCreationDate(LocalDate.parse(source.getMetadata().getCreationDate(),formatter));
		map().setCreatorId(source.getMetadata().getCreatorId());
		//map().setLastVersionDate(LocalDate.parse(source.getMetadata().getLastVersionDate(),formatter));
		map().setReleaseNote(source.getMetadata().getReleaseNote());
		map().setVersion(source.getMetadata().getVersion());
		map().setTags(source.getMetadata().getTags());
		map().setEntityType(source.getMetadata().getEntityType());
		map().setSet(source.getSet());
	}

}
