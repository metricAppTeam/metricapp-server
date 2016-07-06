package metricapp.entity;

public enum State {
	Approved,
	ApprovedInternalApprobation,
	Created,
	Rejected,
	External,
	Suspended,
	OnUpdate,
	OnUpdateWaitingQuestions,
	OnUpdateQuestionerEndpoint,
	OnUpdateInternalRefinement,
	Pending;
	
}
