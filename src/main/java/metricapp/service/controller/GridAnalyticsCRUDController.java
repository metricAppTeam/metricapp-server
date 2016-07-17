package metricapp.service.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.analytics.AnalyticsCrudDTO;
import metricapp.dto.analytics.AnalyticsDTO;
import metricapp.entity.analytics.Analytics;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.controller.GridAnalyticsCRUDInterface;
import metricapp.utility.RandomGenerator;

@Service
public class GridAnalyticsCRUDController implements GridAnalyticsCRUDInterface {

	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory;
	
	@Override
	public AnalyticsCrudDTO getAnalyticsByGridId(String gridid) throws BadInputException, NotFoundException {
		if (gridid == null) {
			throw new BadInputException("GridAnalytics gridid cannot be null");
		}
		
		// START USERS ANALYTICS COMPUTATION
		int n = RandomGenerator.randomIntBetween(2, 20);
		List<Analytics> usersAnalytics = new ArrayList<Analytics>();
		for (int i = 0; i < n; i++) {
			Analytics analytics = Analytics.randomUserAnalytics();
			usersAnalytics.add(analytics);
		}
		// END USERS ANALYTICS COMPUTATION
		
		Analytics gridAnalytics = new Analytics();
		double gqmScore = 0;
		long gridsTot = 0;
		long artifactsTot = 0;
		double acceptanceRatio = 0;
		double worktimeRatio = 0;
		double tasksProgress = 0;
		Map<LocalDate, Long> assigned = new HashMap<LocalDate, Long>();
		Map<LocalDate, Long> submitted = new HashMap<LocalDate, Long>();
		Map<LocalDate, Long> accepted = new HashMap<LocalDate, Long>();
		for (Analytics a : usersAnalytics) {
			gqmScore += a.getGqmScore();
			gridsTot += a.getGridsTot();
			artifactsTot += a.getArtifactsTot();
			acceptanceRatio += a.getAcceptanceRatio();
			worktimeRatio += a.getWorktimeRatio();
			tasksProgress += a.getTasksProgress();
			for (Map.Entry<LocalDate, Long> record : a.getAssigned().entrySet()) {
				LocalDate recordTime = record.getKey();
				long v = assigned.getOrDefault(recordTime, Long.valueOf(0));
				v += record.getValue();
				assigned.put(recordTime, v);
			}
			for (Map.Entry<LocalDate, Long> record : a.getSubmitted().entrySet()) {
				LocalDate recordTime = record.getKey();
				long v = submitted.getOrDefault(recordTime, Long.valueOf(0));
				v += record.getValue();
				submitted.put(recordTime, v);
			}
			for (Map.Entry<LocalDate, Long> record : a.getAccepted().entrySet()) {
				LocalDate recordTime = record.getKey();
				long v = accepted.getOrDefault(recordTime, Long.valueOf(0));
				v += record.getValue();
				accepted.put(recordTime, v);
			}
		}
		
		acceptanceRatio /= n;
		worktimeRatio /= n;
		tasksProgress /= n;
		
		gridAnalytics.setGqmScore(gqmScore);
		gridAnalytics.setGridsTot(gridsTot);
		gridAnalytics.setArtifactsTot(artifactsTot);
		gridAnalytics.setAcceptanceRatio(acceptanceRatio);
		gridAnalytics.setWorktimeRatio(worktimeRatio);
		gridAnalytics.setTasksProgress(tasksProgress);
		
		gridAnalytics.setAssigned(assigned);
		gridAnalytics.setSubmitted(submitted);
		gridAnalytics.setAccepted(accepted);
		
		AnalyticsCrudDTO crud = new AnalyticsCrudDTO();
		crud.setRequest("GET GridAnalytics WITH gridid=" + gridid);
		crud.addAnalyticsToList(modelMapperFactory.getStandardModelMapper().map(gridAnalytics, AnalyticsDTO.class));
		
		return crud;
	}

	
}
