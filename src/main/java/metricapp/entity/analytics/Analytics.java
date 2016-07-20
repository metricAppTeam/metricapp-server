package metricapp.entity.analytics;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import metricapp.utility.RandomGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Analytics {
	
	private int gqmScore;
	private int gridsTot;
	private int artifactsTot;
	private int acceptanceRatio;
	private int worktimeRatio;
	private int tasksProgress;
	public int artifactsMGoal;
	public int artifactsRate;
	private Map<LocalDate, Integer> assigned;
	private Map<LocalDate, Integer> submitted;
	private Map<LocalDate, Integer> accepted;
	
	public double computeGqmScore() {
		return (this.getGridsTot() / this.getArtifactsTot()) * (this.getAcceptanceRatio() / this.getWorktimeRatio());
	}
	
	public static Analytics randomUserAnalytics() {
		Analytics analytics = new Analytics();
		/*
		analytics.setAcceptanceRatio(RandomGenerator.randomIntBetween(0, 100));
		analytics.setArtifactsTot(RandomGenerator.randomIntBetween(10, 100));		
		analytics.setGridsTot(RandomGenerator.randomIntBetween(10, 100));
		analytics.setTasksProgress(RandomGenerator.randomIntBetween(10, 100));
		analytics.setWorktimeRatio(RandomGenerator.randomIntBetween(10, 100));
		
		analytics.setAssigned(new HashMap<LocalDate, Long>());
		analytics.setSubmitted(new HashMap<LocalDate, Long>());
		analytics.setAccepted(new HashMap<LocalDate, Long>());
		
		LocalDate time = RandomGenerator.randomLocalDate();
		for (int i = 0; i < RandomGenerator.randomIntBetween(10, 100); i++) {			
			time.plusDays(i);
			long assigned = RandomGenerator.randomIntBetween(1, 10);
			long submitted = RandomGenerator.randomIntBetween(1, 10);
			long accepted = RandomGenerator.randomIntBetween(1, 10);
			analytics.getAssigned().put(time, assigned);
			analytics.getSubmitted().put(time, submitted);
			analytics.getAccepted().put(time, accepted);
		}
		analytics.setGqmScore(analytics.computeGqmScore());
		*/
		
		analytics.setAcceptanceRatio(70);
		analytics.setArtifactsTot(10);
		analytics.setGridsTot(1);
		analytics.setTasksProgress(50);
		analytics.setWorktimeRatio(80);
		analytics.setArtifactsMGoal(10);
		analytics.setArtifactsRate(60);
		
		analytics.setAssigned(new HashMap<LocalDate, Integer>());
		analytics.setSubmitted(new HashMap<LocalDate, Integer>());
		analytics.setAccepted(new HashMap<LocalDate, Integer>());
		
		LocalDate time = LocalDate.of(2016, 06, 27);
		
		for (int i = 0; i < 30; i++) {			
			time = time.plusDays(i);
			int assigned = RandomGenerator.randomIntBetween(1, 10);
			int submitted = RandomGenerator.randomIntBetween(1, 7);
			int accepted = RandomGenerator.randomIntBetween(1, 5);
			analytics.getAssigned().put(time, assigned);
			analytics.getSubmitted().put(time, submitted);
			analytics.getAccepted().put(time, accepted);
		}
		
		analytics.setGqmScore(80);
		
		return analytics;
	}
	
	
	
}