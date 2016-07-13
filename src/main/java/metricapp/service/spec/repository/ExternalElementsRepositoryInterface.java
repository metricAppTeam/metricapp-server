package metricapp.service.spec.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import metricapp.entity.external.*;
import metricapp.exception.BadInputException;
import metricapp.exception.BusException;


public interface ExternalElementsRepositoryInterface {

	public Assumption getAssumptionByIdAndVersion(String id, String version) throws BusException, IOException, BadInputException;
		
	public ContextFactor getContextFactorByIdAndVersion(String id, String version) throws IOException, BusException, BadInputException;
		
	public OrganizationalGoal getOrganizationalGoalByIdAndVersion(String id, String version) throws IOException, BusException, BadInputException;
		
	public InstanceProject getInstanceProjectByIdAndVersion(String id, String version) throws IOException, BusException, BadInputException;
	
	public ArrayList<Assumption> getAssumptionsByIdList(List<String> list) throws IOException, BusException, BadInputException;
	
	public ArrayList<ContextFactor> getContextFactorsByIdList(List<String> list) throws IOException, BusException, BadInputException;
	
	public ArrayList<Assumption> getAssumptionsByTags(List<String> tags) throws Exception;
	
	public ArrayList<ContextFactor> getContextFactorsByTags(List<String> tags) throws Exception;

	public ArrayList<ContextFactor> getContextFactorsByPointerBusList(List<PointerBus> list) throws IOException, BusException, BadInputException;

	public ArrayList<Assumption> getAssumptionsByPointerBusList(List<PointerBus> list) throws IOException, BusException, BadInputException;

    public NotificationPointerBus pointerOfIncomingNotificationObject(String data) throws IOException;
	
    public PointerBus fromNotificationToPointerBus(NotificationPointerBus notification);
    
    public ArrayList<ContextFactor> getAllContextFactors() throws BusException, IOException, BadInputException;
        
    public ArrayList<Assumption> getAllAssumptions() throws BusException, IOException, BadInputException;
    
    public ArrayList<OrganizationalGoal> getAllOrganizationalGoals() throws BusException, IOException, BadInputException;
    
    public ArrayList<InstanceProject> getAllInstanceProjects() throws BusException, IOException, BadInputException;


}
