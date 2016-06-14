package metricapp.service.spec.repository;

import java.util.ArrayList;

import metricapp.entity.external.Assumption;
import metricapp.entity.external.ContextFactor;
import metricapp.entity.external.InstanceProject;
import metricapp.entity.external.OrganizationalGoal;

public interface ExternalElementsRepository {

	public Assumption getAssumptionById(String id);
		
	public ContextFactor getContextFactorById(String id);
		
	public OrganizationalGoal getOrganizationalGoalById(String id);
		
	public InstanceProject getInstanceProjectById(String id);
	
	public ArrayList<Assumption> getAssumptionsByIdList(ArrayList<String> list);
	
	public ArrayList<ContextFactor> getContextFactorsByIdList(ArrayList<String> list);
	
	public ArrayList<Assumption> getAssumptionsByTags(ArrayList<String> tags);
	
	public ArrayList<ContextFactor> getContextFactorsByTags(ArrayList<String> tags);
}
