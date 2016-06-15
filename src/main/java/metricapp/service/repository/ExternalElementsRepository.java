package metricapp.service.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import metricapp.dto.bus.PointerBusDTO;
import metricapp.entity.Entity;
import metricapp.entity.external.*;
import metricapp.exception.BusException;
import metricapp.service.spec.repository.BusInterface;
import metricapp.service.spec.repository.ExternalElementsRepositoryInterface;
import metricapp.utility.JacksonMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nonnull;

/**
 * This repository offers user friedly methods to grab external elements from Bus.
 *
 * There's the possibility to find elements by id, by id list, by pointerBus list for almost of them
 */
public class ExternalElementsRepository implements ExternalElementsRepositoryInterface {

	@Autowired
	private BusInterface busRepository;

    @Autowired
    private JacksonMapper mapper;

    /**
     * this is the simple method to grab an assumption, version value could be null (last version is received)
     * @param id
     * @param version
     * @return
     * @throws BusException
     * @throws IOException
     */
	public Assumption getAssumptionByIdAndVersion(@Nonnull String id, String version) throws BusException, IOException {
        PointerBusDTO request = new PointerBusDTO();

        request.setBusVersion(version);
        request.setTypeObj(Entity.Assumption.name());
        request.setInstance(id);

        return mapper.fromJson(busRepository.read(request), Assumption.class);
	}

    /**
     * * this is the simple method to grab a context factor, version value could be null (last version is received)
     * @param id
     * @param version
     * @return
     * @throws IOException
     * @throws BusException
     */
	public ContextFactor getContextFactorByIdAndVersion(@Nonnull String id, String version) throws IOException, BusException {
        PointerBusDTO request = new PointerBusDTO();

        request.setBusVersion(version);
        request.setTypeObj(Entity.ContextFactor.name());
        request.setInstance(id);

        return mapper.fromJson(busRepository.read(request), ContextFactor.class);
	}

    /**
     * * this is the simple method to grab an organizational goal, version value could be null (last version is received)
     * @param id
     * @param version
     * @return
     * @throws IOException
     * @throws BusException
     */
	public OrganizationalGoal getOrganizationalGoalByIdAndVersion(@Nonnull String id, String version) throws IOException, BusException {
        PointerBusDTO request = new PointerBusDTO();

        request.setBusVersion(version);
        request.setTypeObj(Entity.OrganizationalGoal.name());
        request.setInstance(id);

        return mapper.fromJson(busRepository.read(request), OrganizationalGoal.class);
	}

    /**
     * * this is the simple method to grab an instance project, version value could be null (last version is received)
     * @param id
     * @param version
     * @return
     * @throws IOException
     * @throws BusException
     */
	public InstanceProject getInstanceProjectByIdAndVersion(@Nonnull String id, String version) throws IOException, BusException {
        PointerBusDTO request = new PointerBusDTO();

        request.setBusVersion(version);
        request.setTypeObj(Entity.InstanceProject.name());
        request.setInstance(id);

        return mapper.fromJson(busRepository.read(request), InstanceProject.class);
	}

    /**
     * this method is a loop of version and id getter
     * @param list
     * @return
     * @throws IOException
     * @throws BusException
     */
	public ArrayList<Assumption> getAssumptionsByIdList(@Nonnull ArrayList<String> list) throws IOException, BusException {
        Iterator<String> i = list.iterator();

        // it increases the performance
        ArrayList<Assumption> newList = new ArrayList<>(list.size());

        while(i.hasNext()){
            newList.add(this.getAssumptionByIdAndVersion(i.next(), null));
        }

        return newList;
	}

    /**
     * this method is a loop of version and id getter
     * @param list
     * @return
     * @throws IOException
     * @throws BusException
     */
	public ArrayList<ContextFactor> getContextFactorsByIdList(@Nonnull ArrayList<String> list) throws IOException, BusException {
        Iterator<String> i = list.iterator();

        // it increases the performance
        ArrayList<ContextFactor> newList = new ArrayList<>(list.size());

        while(i.hasNext()){
            newList.add(this.getContextFactorByIdAndVersion(i.next(),null));
        }

        return newList;
	}

    /**
     * this method is a loop of version and id getter
     * @param list
     * @return
     * @throws IOException
     * @throws BusException
     */
    public ArrayList<Assumption> getAssumptionsByPointerBusList(@Nonnull ArrayList<PointerBus> list) throws IOException, BusException {
        Iterator<PointerBus> i = list.iterator();

        // it increases the performance
        ArrayList<Assumption> newList = new ArrayList<>(list.size());

        PointerBus pointer;
        while(i.hasNext()){
            pointer = i.next();
            newList.add(this.getAssumptionByIdAndVersion(pointer.getInstance(),pointer.getBusVersion()));
        }

        return newList;
    }

    /**
     * this method is a loop of version and id getter
     * @param list
     * @return
     * @throws IOException
     * @throws BusException
     */
    public ArrayList<ContextFactor> getContextFactorsByPointerBusList(@Nonnull ArrayList<PointerBus> list) throws IOException, BusException {
        Iterator<PointerBus> i = list.iterator();

        // it increases the performance
        ArrayList<ContextFactor> newList = new ArrayList<>(list.size());

        PointerBus pointer;
        while(i.hasNext()){
            pointer = i.next();
            newList.add(this.getContextFactorByIdAndVersion(pointer.getInstance(),pointer.getBusVersion()));
        }

        return newList;
    }

    // TODO
	public ArrayList<Assumption> getAssumptionsByTags(@Nonnull ArrayList<String> tags) throws Exception {
        throw new Exception("not implemented");
	}

    // TODO
	public ArrayList<ContextFactor> getContextFactorsByTags(@Nonnull ArrayList<String> tags) throws Exception {
        throw new Exception("not implemented");
	}
	
	
}
