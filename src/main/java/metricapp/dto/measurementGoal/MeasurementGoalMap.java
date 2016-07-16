package metricapp.dto.measurementGoal;


import org.modelmapper.PropertyMap;

import metricapp.entity.measurementGoal.MeasurementGoal;

public class MeasurementGoalMap extends PropertyMap<MeasurementGoal, MeasurementGoalDTO> {

	@Override
	protected void configure() {
		
		map().getInterpretationModel().setFunctionJavascript(source.getInterpretationModel().getFunctionJavascript());
		map().getInterpretationModel().setQueryNoSQL(source.getInterpretationModel().getQueryNoSQL());
		
		map().getMetadata().setId(source.getId());
		//map().getMetadata().setCreationDate(source.getCreationData().toLocalizedPattern());
		map().getMetadata().setCreatorId(source.getCreatorId());
		//map().getMetadata().setLastVersionDate(source.getLastVersionData().toLocalizedPattern());
		map().getMetadata().setReleaseNote(source.getReleaseNote());
		map().getMetadata().setVersion(source.getVersion());
		map().getMetadata().setTags(source.getTags());
		map().getMetadata().setState(source.getState());
		map().getMetadata().setCreationDate(source.getCreationDate());
		map().getMetadata().setLastVersionDate(source.getLastVersionDate());
		map().getMetadata().setVersionBus(source.getVersionBus());
		map().getMetadata().setEntityType(source.getEntityType());
	}
	
}
