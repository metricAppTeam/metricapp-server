package metricapp.dto.externalElements;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;
import metricapp.dto.GoalDTO;

@Data
@EqualsAndHashCode(callSuper=true)
public class OrganizationalGoalDTO extends GoalDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7848107950230553402L;

	private String title;
	private String timeFrame;
	private String object;
	private String focus;
	private String magnitude;
	private String priority;
	private String rationaleId;
	private String organizationalGoalState;
	private String instanceProjectId;
	
	private ArrayList<String> organizationalScopeIds;
	private ArrayList<String> constraints;
	private ArrayList<String> strategyChildIds;
	private ArrayList<String> strategyFatherId;
	private ArrayList<String> subgoalIds;
	private ArrayList<String> collaborativeGoalIds;
	private ArrayList<String> conflictGoalIds;
	
}
