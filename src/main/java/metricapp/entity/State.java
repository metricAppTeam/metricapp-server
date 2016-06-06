package metricapp.entity;

public enum State {
	Approved,
	Approved_InternalApprobation,
	Created,
	Rejected,
	External,
	Suspended,
	OnUpdate,
	OnUpdate_WaitingQuestions,
	OnUpdate_QuestionerEndpoint,
	OnUpdate_InternalRefinement,
	Pending;
	
}
