package metricapp.service.repository;

import java.util.ArrayList;

import metricapp.entity.external.Assumption;
import metricapp.entity.external.ContextFactor;
import metricapp.entity.external.InstanceProject;
import metricapp.entity.external.OrganizationalGoal;

public class ExternalElementsRepository implements metricapp.service.spec.repository.ExternalElementsRepository {

	public Assumption getAssumptionById(String id){
		return null;
	}
	
	public ContextFactor getContextFactorById(String id){
		return null;
	}
	
	public OrganizationalGoal getOrganizationalGoalById(String id){
		return null;
	}
	
	public InstanceProject getInstanceProjectById(String id){
		return null;
	}
	
	public ArrayList<Assumption> getAssumptionsByIdList(ArrayList<String> list){
		return null;
	}
	
	public ArrayList<ContextFactor> getContextFactorsByIdList(ArrayList<String> list){
		return null;
	}
	
	public ArrayList<Assumption> getAssumptionsByTags(ArrayList<String> tags){
		return null;
	}
	
	public ArrayList<ContextFactor> getContextFactorsByTags(ArrayList<String> tags){
		return null;
	}
	
	
}
