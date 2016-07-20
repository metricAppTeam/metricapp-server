package metricapp.service.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.analytics.AnalyticsCrudDTO;
import metricapp.entity.analytics.Analytics;
import metricapp.exception.BadInputException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.controller.TeamAnalyticsCRUDInterface;
import metricapp.utility.RandomGenerator;

@Service
public class TeamAnalyticsCRUDController implements TeamAnalyticsCRUDInterface {

	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory;
	
	@Override
	public AnalyticsCrudDTO getAnalyticsByTeamId(String teamid) throws BadInputException, NotFoundException {
		if (teamid == null) {
			throw new BadInputException("TeamAnalytics teamid cannot be null");
		}
		
		// START USERS ANALYTICS COMPUTATION
		int n = RandomGenerator.randomIntBetween(2, 20);
		List<Analytics> usersAnalytics = new ArrayList<Analytics>();
		for (int i = 0; i < n; i++) {
			Analytics analytics = Analytics.randomUserAnalytics();
			usersAnalytics.add(analytics);
		}
		// END USERS ANALYTICS COMPUTATION
		
		Analytics teamAnalytics = new Analytics();
		int gqmScore = 0;
		int gridsTot = 0;
		int artifactsTot = 0;
		int acceptanceRatio = 0;
		int worktimeRatio = 0;
		int tasksProgress = 0;
		int artifactsMGoal = 0;
		int artifactsRate = 0;
		Map<LocalDate, Integer> assigned = new HashMap<LocalDate, Integer>();
		Map<LocalDate, Integer> submitted = new HashMap<LocalDate, Integer>();
		Map<LocalDate, Integer> accepted = new HashMap<LocalDate, Integer>();
		for (Analytics a : usersAnalytics) {
			gqmScore += a.getGqmScore();
			gridsTot += a.getGridsTot();
			artifactsTot += a.getArtifactsTot();
			acceptanceRatio += a.getAcceptanceRatio();
			worktimeRatio += a.getWorktimeRatio();
			tasksProgress += a.getTasksProgress();
			artifactsMGoal += a.getArtifactsMGoal();
			artifactsRate += a.getArtifactsRate();
			for (Map.Entry<LocalDate, Integer> record : a.getAssigned().entrySet()) {
				LocalDate recordTime = record.getKey();
				int v = assigned.getOrDefault(recordTime, Integer.valueOf(0));
				v += record.getValue();
				assigned.put(recordTime, v);
			}
			for (Map.Entry<LocalDate, Integer> record : a.getSubmitted().entrySet()) {
				LocalDate recordTime = record.getKey();
				int v = submitted.getOrDefault(recordTime, Integer.valueOf(0));
				v += record.getValue();
				submitted.put(recordTime, v);
			}
			for (Map.Entry<LocalDate, Integer> record : a.getAccepted().entrySet()) {
				LocalDate recordTime = record.getKey();
				int v = accepted.getOrDefault(recordTime, Integer.valueOf(0));
				v += record.getValue();
				accepted.put(recordTime, v);
			}
		}
		
		acceptanceRatio /= n;
		worktimeRatio /= n;
		tasksProgress /= n;
		artifactsMGoal /= n;
		artifactsRate /= n;
		
		teamAnalytics.setGqmScore(gqmScore);
		teamAnalytics.setGridsTot(gridsTot);
		teamAnalytics.setArtifactsTot(artifactsTot);
		teamAnalytics.setAcceptanceRatio(acceptanceRatio);
		teamAnalytics.setWorktimeRatio(worktimeRatio);
		teamAnalytics.setTasksProgress(tasksProgress);
		teamAnalytics.setArtifactsMGoal(artifactsMGoal);
		teamAnalytics.setArtifactsRate(artifactsRate);
		
		teamAnalytics.setAssigned(assigned);
		teamAnalytics.setSubmitted(submitted);
		teamAnalytics.setAccepted(accepted);
		
		AnalyticsCrudDTO crud = new AnalyticsCrudDTO();
		crud.setRequest("GET TeamAnalytics WITH teamid=" + teamid);
		crud.setMessage("SUCCESS in LOADING analytics FOR team WITH teamid=" + teamid);
		crud.addAnalytics(teamAnalytics, modelMapperFactory.getStandardModelMapper());
		
		return crud;
	}

	
}
