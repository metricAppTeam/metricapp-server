package metricapp.entity.external;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;
import metricapp.entity.AbstractGoal;


@Data
@EqualsAndHashCode(callSuper=true)
public class OrganizationalGoal extends AbstractGoal {
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
	
	//creationDate, creatorId in element
	
}
