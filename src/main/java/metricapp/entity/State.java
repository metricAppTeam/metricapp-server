package metricapp.entity;

public enum State {
	Approved,
	Approved_InternalApprovation,
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
