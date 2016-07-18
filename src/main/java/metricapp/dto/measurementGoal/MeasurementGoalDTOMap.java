package metricapp.dto.measurementGoal;

import org.modelmapper.PropertyMap;

import metricapp.entity.measurementGoal.MeasurementGoal;

public class MeasurementGoalDTOMap extends PropertyMap<MeasurementGoalDTO, MeasurementGoal> {

	@Override
	protected void configure() {
		map().setId(source.getMetadata().getId());
		map().setReleaseNote(source.getMetadata().getReleaseNote());
		map().setCreationDate(source.getMetadata().getCreationDate());
		map().setLastVersionDate(source.getMetadata().getLastVersionDate());
		map().setState(source.getMetadata().getState());
		map().setVersion(source.getMetadata().getVersion());
		map().setTags(source.getMetadata().getTags());
		map().setVersionBus(source.getMetadata().getVersionBus());
		map().setEntityType(source.getMetadata().getEntityType());
	}
	
}