package metricapp.dto.analytics;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

import lombok.Data;

@Data
public class AnalyticsDTO implements Serializable {

	private static final long serialVersionUID = -6131993103442424873L;
	
	public double gqmScore;
	public long gridsTot;
	public long artifactsTot;
	public double acceptanceRatio;
	public double worktimeRatio;
	public double tasksProgress;
	public Map<LocalDate, Long> assigned;
	public Map<LocalDate, Long> submitted;
	public Map<LocalDate, Long> accepted;
	
}
