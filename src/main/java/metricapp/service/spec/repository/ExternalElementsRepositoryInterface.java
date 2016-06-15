package metricapp.service.spec.repository;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import metricapp.entity.external.*;
import metricapp.exception.BusException;

import javax.annotation.Nonnull;

public interface ExternalElementsRepositoryInterface {

	public Assumption getAssumptionByIdAndVersion(String id, String version) throws BusException, IOException;
		
	public ContextFactor getContextFactorByIdAndVersion(String id, String version) throws IOException, BusException;
		
	public OrganizationalGoal getOrganizationalGoalByIdAndVersion(String id, String version) throws IOException, BusException;
		
	public InstanceProject getInstanceProjectByIdAndVersion(String id, String version) throws IOException, BusException;
	
	public ArrayList<Assumption> getAssumptionsByIdList(ArrayList<String> list) throws IOException, BusException;
	
	public ArrayList<ContextFactor> getContextFactorsByIdList(ArrayList<String> list) throws IOException, BusException;
	
	public ArrayList<Assumption> getAssumptionsByTags(ArrayList<String> tags) throws Exception;
	
	public ArrayList<ContextFactor> getContextFactorsByTags(ArrayList<String> tags) throws Exception;

	public ArrayList<ContextFactor> getContextFactorsByPointerBusList(ArrayList<PointerBus> list) throws IOException, BusException;

	public ArrayList<Assumption> getAssumptionsByPointerBusList(ArrayList<PointerBus> list) throws IOException, BusException;

	}
