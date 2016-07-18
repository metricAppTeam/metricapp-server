package metricapp.dto.analytics;

import org.modelmapper.PropertyMap;

import metricapp.entity.analytics.Analytics;

public class AnalyticsMap extends PropertyMap<Analytics, AnalyticsDTO> {

	@Override
	protected void configure() {
		map().setAcceptanceRatio(source.getAcceptanceRatio());
		map().setArtifactsTot(source.getArtifactsTot());
		map().setGqmScore(source.getGqmScore());
		map().setGridsTot(source.getGridsTot());
		map().setTasksProgress(source.getTasksProgress());
		map().setWorktimeRatio(source.getWorktimeRatio());
		map().setAssigned(source.getAssigned());
		map().setSubmitted(source.getSubmitted());
		map().setAccepted(source.getAccepted());
	}
	
}
