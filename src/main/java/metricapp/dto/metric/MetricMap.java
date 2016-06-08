package metricapp.dto.metric;

import org.modelmapper.PropertyMap;

import metricapp.entity.metric.Metric;

public class MetricMap extends PropertyMap<Metric, MetricDTO> {

	@Override
	protected void configure() {
		map().getMetadata().setId(source.getId());
		map().getMetadata().setCreationDate(source.getCreationDate());
		map().getMetadata().setCreatorId(source.getCreatorId());
		map().getMetadata().setLastVersionDate(source.getLastVersionDate());
		map().getMetadata().setReleaseNote(source.getReleaseNote());
		map().getMetadata().setVersion(source.getVersion());
		map().getMetadata().setTags(source.getTags());
		map().getMetadata().setState(source.getState());
		map().getMetadata().setEntityType(source.getEntityType());
		
	}
	
}
