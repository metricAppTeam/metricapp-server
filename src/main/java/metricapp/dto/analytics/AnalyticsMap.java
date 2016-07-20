package metricapp.dto.analytics;

import org.modelmapper.PropertyMap;

import metricapp.entity.analytics.Analytics;

public class AnalyticsMap extends PropertyMap<Analytics, AnalyticsDTO> {

	@Override
	protected void configure() {
		map().setAcceptanceRatio(source.getAcceptanceRatio());
		map().setGqmScore(source.getGqmScore());
		map().setGridsTot(source.getGridsTot());
		map().setTasksProgress(source.getTasksProgress());
		map().setWorktimeRatio(source.getWorktimeRatio());
		map().setArtifactsMGoal(source.getArtifactsMGoal());
		map().setArtifactsRate(source.getArtifactsRate());
		map().setAssigned(source.getAssigned());
		map().setSubmitted(source.getSubmitted());
		map().setAccepted(source.getAccepted());
	}
	
}
