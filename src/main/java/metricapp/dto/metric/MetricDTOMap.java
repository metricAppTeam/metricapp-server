package metricapp.dto.metric;


import org.modelmapper.PropertyMap;

import metricapp.entity.metric.Metric;

public class MetricDTOMap extends PropertyMap<MetricDTO, Metric> {

	@Override
	protected void configure() {
			
		map().setId(source.getMetadata().getId());
		map().setCreationDate(source.getMetadata().getCreationDate());
		map().setCreatorId(source.getMetadata().getCreatorId());
		map().setLastVersionDate(source.getMetadata().getLastVersionDate());
		map().setReleaseNote(source.getMetadata().getReleaseNote());
		map().setVersion(source.getMetadata().getVersion());
		map().setTags(source.getMetadata().getTags());
		map().setEntityType(source.getMetadata().getEntityType());
		map().setSet(source.getSet());
	}

}
