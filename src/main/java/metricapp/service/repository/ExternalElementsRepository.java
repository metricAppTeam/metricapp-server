package metricapp.service.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import metricapp.dto.bus.BusMessageOtherPhases;
import metricapp.entity.Entity;
import metricapp.entity.external.*;
import metricapp.exception.BadInputException;
import metricapp.exception.BusException;
import metricapp.service.spec.repository.BusApprovedElementInterface;
import metricapp.service.spec.repository.ExternalElementsRepositoryInterface;
import metricapp.utility.JacksonMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

/**
 * This repository offers user friendly methods to grab external elements from Bus.
 *
 * There's the possibility to find elements by id, by id list, by pointerBus list for almost of them
 */
@Service
public class ExternalElementsRepository implements ExternalElementsRepositoryInterface {

	
	@Autowired
	private BusApprovedElementInterface busApprovedElementRepository; 

	@Autowired
	private JacksonMapper mapper;
	
	
    /**
     * this is the simple method to grab an assumption, version value could be null (last version is received)
     * @param id
     * @param version
     * @return
     * @throws BusException
     * @throws IOException
     * @throws BadInputException 
     */
	@Override
	public Assumption getAssumptionByIdAndVersion(@Nonnull String id, String version) throws BusException, IOException, BadInputException {
        PointerBus request = new PointerBus();

        request.setBusVersion(version);
        request.setTypeObj(Entity.Assumption.name());
        request.setInstance(id);

        return (Assumption) busApprovedElementRepository.getApprovedElement(request, Assumption.class);
	}

    /**
     * * this is the simple method to grab a context factor, version value could be null (last version is received)
     * @param id
     * @param version
     * @return
     * @throws IOException
     * @throws BusException
     * @throws BadInputException 
     */
	@Override
	public ContextFactor getContextFactorByIdAndVersion(@Nonnull String id, String version) throws IOException, BusException, BadInputException {
        PointerBus request = new PointerBus();

        request.setBusVersion(version);
        request.setTypeObj(Entity.ContextFactor.name());
        request.setInstance(id);

        return (ContextFactor) busApprovedElementRepository.getApprovedElement(request, ContextFactor.class);
	}

    /**
     * * this is the simple method to grab an organizational goal, version value could be null (last version is received)
     * @param id
     * @param version
     * @return
     * @throws IOException
     * @throws BusException
     * @throws BadInputException 
     */
	@Override
	public OrganizationalGoal getOrganizationalGoalByIdAndVersion(@Nonnull String id, String version) throws IOException, BusException, BadInputException {
        PointerBus request = new PointerBus();

        request.setBusVersion(version);
        request.setTypeObj(Entity.OrganizationalGoal.name());
        request.setInstance(id);

        return (OrganizationalGoal) busApprovedElementRepository.getApprovedElement(request, OrganizationalGoal.class);
	}

    /**
     * * this is the simple method to grab an instance project, version value could be null (last version is received)
     * @param id
     * @param version
     * @return
     * @throws IOException
     * @throws BusException
     * @throws BadInputException 
     */
	@Override
	public InstanceProject getInstanceProjectByIdAndVersion(@Nonnull String id, String version) throws IOException, BusException, BadInputException {
        PointerBus request = new PointerBus();

        request.setBusVersion(version);
        request.setTypeObj(Entity.InstanceProject.name());
        request.setInstance(id);
        request.setBusVersion("");

        return (InstanceProject) busApprovedElementRepository.getApprovedElement(request, InstanceProject.class);
	}

    /**
     * this method is a loop of version and id getter. 
     * If there's an error, function returns empty List
     * @param list
     * @return
     * @throws IOException
     * @throws BusException
     * @throws BadInputException 
     */
	@Override
	public ArrayList<Assumption> getAssumptionsByIdList(@Nonnull List<String> list) throws IOException, BusException, BadInputException {
        Iterator<String> i = list.iterator();

        // it increases the performance
        ArrayList<Assumption> newList = new ArrayList<>(list.size());

        while(i.hasNext()){
        	try{
        		newList.add(this.getAssumptionByIdAndVersion(i.next(), null));
        	}catch(Exception e){}
        }

        return newList;
	}

    /**
     * this method is a loop of version and id getter.If there's an error, function returns empty List
     * @param list
     * @return
     * @throws IOException
     * @throws BusException
     * @throws BadInputException 
     */
	@Override
	public ArrayList<ContextFactor> getContextFactorsByIdList(@Nonnull List<String> list) throws IOException, BusException, BadInputException {
        Iterator<String> i = list.iterator();

        // it increases the performance
        ArrayList<ContextFactor> newList = new ArrayList<>(list.size());

        while(i.hasNext()){
        	try{
        		newList.add(this.getContextFactorByIdAndVersion(i.next(),null));
        	}catch(Exception e){}
        }

        return newList;
	}

    /**
     * this method is a loop of version and id getter.If there's an error, function returns empty List
     * @param list
     * @return
     * @throws IOException
     * @throws BusException
     * @throws BadInputException 
     */
	@Override
    public ArrayList<Assumption> getAssumptionsByPointerBusList(@Nonnull List<PointerBus> list) throws IOException, BusException, BadInputException {
        Iterator<PointerBus> i = list.iterator();

        // it increases the performance
        ArrayList<Assumption> newList = new ArrayList<>(list.size());

        PointerBus pointer;
        while(i.hasNext()){
        	try{
        		pointer = i.next();
        		newList.add(this.getAssumptionByIdAndVersion(pointer.getInstance(),pointer.getBusVersion()));
        	}catch(Exception e){}
        }

        return newList;
    }

    /**
     * this method is a loop of version and id getter.If there's an error, function returns empty List
     * @param list
     * @return
     * @throws IOException
     * @throws BusException
     * @throws BadInputException 
     */
    @Override
    public ArrayList<ContextFactor> getContextFactorsByPointerBusList(@Nonnull List<PointerBus> list) throws IOException, BusException, BadInputException {
        Iterator<PointerBus> i = list.iterator();

        // it increases the performance
        ArrayList<ContextFactor> newList = new ArrayList<>(list.size());

        PointerBus pointer;
        while(i.hasNext()){
        	try{
        		pointer = i.next();
        		newList.add(this.getContextFactorByIdAndVersion(pointer.getInstance(),pointer.getBusVersion()));
        	}catch(Exception e){}
        }

        return newList;
    }
    @Override
    public NotificationPointerBus pointerOfIncomingNotificationObject(String data) throws IOException{
    	return mapper.fromJson(data, NotificationPointerBus.class);
    }
    
    @Override
    public BusMessageOtherPhases messageFromOtherPhases(String data) throws IOException{
    	return mapper.fromJson(data, BusMessageOtherPhases.class);
    }
    
    @Override
    public PointerBus fromNotificationToPointerBus(NotificationPointerBus notification){
    	PointerBus pointer = new PointerBus();
    	pointer.setBusVersion(notification.getBusVersion());
    	pointer.setInstance(notification.getInstance());
    	pointer.setTypeObj(notification.getTypeObj());
    	return pointer;
    }

    @Override
	public ArrayList<Assumption> getAssumptionsByTags(@Nonnull List<String> tags) throws Exception {
        throw new Exception("not Possible due to lack of Implementation from Bus");
	}

    @Override
	public ArrayList<ContextFactor> getContextFactorsByTags(@Nonnull List<String> tags) throws Exception {
        throw new Exception("not Possible due to lack of Implementation from Bus");
	}
	
	 /**
     * this is the simple method to grab all assumptions
     * @return
     * @throws BusException
     * @throws IOException
     * @throws BadInputException 
     */
	@Override
	public ArrayList<Assumption> getAllAssumptions() throws BusException, IOException, BadInputException {
        PointerBus request = new PointerBus();
        request.setTypeObj(Entity.Assumption.name());
        return busApprovedElementRepository.getApprovedElements(request, Assumption.class);
	}
	
	 /**
     * this is the simple method to grab all ContextFactors
     * @return
     * @throws BusException
     * @throws IOException
     * @throws BadInputException 
     */
	@Override
	public ArrayList<ContextFactor> getAllContextFactors() throws BusException, IOException, BadInputException {
        PointerBus request = new PointerBus();
        request.setTypeObj(Entity.ContextFactor.name());
        return busApprovedElementRepository.getApprovedElements(request, ContextFactor.class);
	}
	
	 /**
     * this is the simple method to grab all OrganizationalGoals
     * @return
     * @throws BusException
     * @throws IOException
     * @throws BadInputException 
     */
	@Override
	public ArrayList<OrganizationalGoal> getAllOrganizationalGoals() throws BusException, IOException, BadInputException {
        PointerBus request = new PointerBus();
        request.setTypeObj(Entity.OrganizationalGoal.name());
        return busApprovedElementRepository.getApprovedElements(request, OrganizationalGoal.class);
	}
	
	 /**
     * this is the simple method to grab all InstanceProjects
     * @return
     * @throws BusException
     * @throws IOException
     * @throws BadInputException 
     */
	@Override
	public ArrayList<InstanceProject> getAllInstanceProjects() throws BusException, IOException, BadInputException {
        PointerBus request = new PointerBus();
        request.setTypeObj(Entity.InstanceProject.name());
        return busApprovedElementRepository.getApprovedElements(request, InstanceProject.class);
	}
	
	
}
