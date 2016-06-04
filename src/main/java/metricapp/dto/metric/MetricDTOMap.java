package metricapp.dto.metric;

import org.modelmapper.PropertyMap;

import metricapp.entity.metric.Metric;

public class MetricDTOMap extends PropertyMap<MetricDTO, Metric> {

	@Override
	protected void configure() {
		map().setId(source.getMetadata().getId());
		//map().setCreationData(source.getMetadata().getCreationDate().toString());
		map().setCreatorId(source.getMetadata().getCreatorId());
		//map().setLastVersionData(source.getMetadata().getLastVersionDate().toString());
		map().setReleaseNote(source.getMetadata().getReleaseNote());
		map().setVersion(source.getMetadata().getVersion());
		map().setTags(source.getMetadata().getTags());
		
	}

}
