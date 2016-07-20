package metricapp.dto.analytics;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

import lombok.Data;

@Data
public class AnalyticsDTO implements Serializable {

	private static final long serialVersionUID = -6131993103442424873L;
	
	public int gqmScore;
	public int gridsTot;
	public int artifactsTot;
	public int acceptanceRatio;
	public int worktimeRatio;
	public int tasksProgress;
	public int artifactsMGoal;
	public int artifactsRate;
	public Map<LocalDate, Integer> assigned;
	public Map<LocalDate, Integer> submitted;
	public Map<LocalDate, Integer> accepted;
}
