package metricapp.service.spec.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import metricapp.entity.external.*;
import metricapp.exception.BusException;


public interface ExternalElementsRepositoryInterface {

	public Assumption getAssumptionByIdAndVersion(String id, String version) throws BusException, IOException;
		
	public ContextFactor getContextFactorByIdAndVersion(String id, String version) throws IOException, BusException;
		
	public OrganizationalGoal getOrganizationalGoalByIdAndVersion(String id, String version) throws IOException, BusException;
		
	public InstanceProject getInstanceProjectByIdAndVersion(String id, String version) throws IOException, BusException;
	
	public ArrayList<Assumption> getAssumptionsByIdList(List<String> list) throws IOException, BusException;
	
	public ArrayList<ContextFactor> getContextFactorsByIdList(List<String> list) throws IOException, BusException;
	
	public ArrayList<Assumption> getAssumptionsByTags(List<String> tags) throws Exception;
	
	public ArrayList<ContextFactor> getContextFactorsByTags(List<String> tags) throws Exception;

	public ArrayList<ContextFactor> getContextFactorsByPointerBusList(List<PointerBus> list) throws IOException, BusException;

	public ArrayList<Assumption> getAssumptionsByPointerBusList(List<PointerBus> list) throws IOException, BusException;

	}
