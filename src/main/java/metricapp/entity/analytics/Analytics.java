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
	
	private double gqmScore;
	private long gridsTot;
	private long artifactsTot;
	private double acceptanceRatio;
	private double worktimeRatio;
	private double tasksProgress;
	private Map<LocalDate, Long> assigned;
	private Map<LocalDate, Long> submitted;
	private Map<LocalDate, Long> accepted;
	
	public double computeGqmScore() {
		return (this.getGridsTot() / this.getArtifactsTot()) * (this.getAcceptanceRatio() / this.getWorktimeRatio());
	}
	
	public static Analytics randomUserAnalytics() {
		Analytics analytics = new Analytics();
		
		analytics.setAcceptanceRatio(RandomGenerator.randomPercentage());
		analytics.setArtifactsTot(RandomGenerator.randomLong());		
		analytics.setGridsTot(RandomGenerator.randomLong());
		analytics.setTasksProgress(RandomGenerator.randomPercentage());
		analytics.setWorktimeRatio(RandomGenerator.randomPercentage());
		
		analytics.setAssigned(new HashMap<LocalDate, Long>());
		analytics.setSubmitted(new HashMap<LocalDate, Long>());
		analytics.setAccepted(new HashMap<LocalDate, Long>());
		
		for (int i = 0; i < RandomGenerator.randomIntBetween(10, 100); i++) {
			LocalDate time = RandomGenerator.randomLocalDate();
			long assigned = RandomGenerator.randomLongBetween(0, 10);
			long submitted = RandomGenerator.randomLongBetween(0, 10);
			long accepted = RandomGenerator.randomLongBetween(0, 10);
			analytics.getAssigned().put(time, assigned);
			analytics.getSubmitted().put(time, submitted);
			analytics.getAccepted().put(time, accepted);
		}
		analytics.setGqmScore(analytics.computeGqmScore());
		
		return analytics;
	}
	
	
	
}