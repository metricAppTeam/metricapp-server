package metricapp.service.controller;

import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import metricapp.dto.metric.MetricCrudDTO;
import metricapp.dto.metric.MetricDTO;
import metricapp.entity.Entity;
import metricapp.entity.State;
import metricapp.entity.metric.Metric;
import metricapp.exception.BadInputException;
import metricapp.exception.BusException;
import metricapp.exception.DBException;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.service.spec.controller.MetricCRUDInterface;
import metricapp.service.spec.repository.BusApprovedElementInterface;
import metricapp.service.spec.repository.MetricRepository;
import metricapp.utility.stateTransitionUtils.AbstractStateTransitionFactory;

@Service
public class MetricCRUDController implements MetricCRUDInterface {

	@Autowired
	private MetricRepository metricRepository;

	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory;
	
	@Autowired
	private BusApprovedElementInterface busApprovedElementRepository;

	@Override
	public MetricCrudDTO getMetricById(String id) throws BadInputException, NotFoundException {
		if (id == null) {
			throw new BadInputException("Metric id cannot be null");
		}
		Metric metric = metricRepository.findMetricById(id);
		if (metric == null) {
			throw new NotFoundException("Metric with id " + id + "is not available");
		}
		MetricCrudDTO dto = new MetricCrudDTO();
		dto.setRequest("Metric, id=" + id);
		dto.addMetricToList(modelMapperFactory.getLooseModelMapper().map(metric, MetricDTO.class));
		return dto;
	}

	@Override
	public MetricCrudDTO getMetricByIdAndVersion(String id, String version)
			throws BadInputException, NotFoundException {
		if (id == null || version == null) {
			throw new BadInputException("Metric id,version cannot be null");
		}
		Metric metric = metricRepository.findMetricByIdAndVersion(id, version);
		if (metric == null) {
			throw new NotFoundException("Metric with id " + id + " and version " + version + "is not available");
		}
		MetricCrudDTO dto = new MetricCrudDTO();
		dto.setRequest("Metric, id=" + id + ";version=" + version);
		dto.addMetricToList(modelMapperFactory.getLooseModelMapper().map(metric, MetricDTO.class));
		return dto;
	}


	@Override
	public MetricDTO getMetricByIdLastApprovedVersion(String id) throws BadInputException, NotFoundException, BusException, IOException {
		if (id == null) {
			throw new BadInputException("Metric id cannot be null");
		}
		
		Metric last =busApprovedElementRepository.getLastApprovedElement(id, Metric.class);
		
		return modelMapperFactory.getLooseModelMapper().map(last, MetricDTO.class);
	}
	
	@Override
	public MetricCrudDTO getMetricCrudDTOByIdLastApprovedVersion(String id) throws BadInputException, NotFoundException, BusException, IOException {
		MetricCrudDTO dto= new MetricCrudDTO();
		dto.addMetricToList(getMetricByIdLastApprovedVersion(id));
		return dto;
		
	}


	@Override
	public MetricCrudDTO getMetricOfUser(String userId) throws NotFoundException, BadInputException {
		if (userId == null) {
			throw new BadInputException("Metric userId cannot be null");
		}
		ArrayList<Metric> metrics = metricRepository.findMetricByMetricatorId(userId);
		if (metrics.size() == 0) {
			throw new NotFoundException("User " + userId + " has no Metrics");
		}
		MetricCrudDTO dto = new MetricCrudDTO();
		dto.setRequest("Metric of " + userId);
		Iterator<Metric> metricP = metrics.iterator();
		while (metricP.hasNext()) {
			dto.addMetricToList(modelMapperFactory.getLooseModelMapper().map(metricP.next(), MetricDTO.class));
		}
		return dto;
	}

	@Override
	public MetricCrudDTO createMetric(@Nonnull MetricDTO dto) throws BadInputException {

		if (dto.getMetadata().getCreatorId() == null) {
			throw new BadInputException("Bad Input");
		}
		if (dto.getMetadata().getId() != null) {
			throw new BadInputException("New Metrics cannot have ID");
		}
		if (dto.getMetadata().getState() != State.Created) {
			throw new BadInputException("New Metrics must be in state of CREATED");
		}
		dto.getMetadata().setCreationDate(LocalDate.now());
		dto.getMetadata().setLastVersionDate(LocalDate.now());
		Metric newMetric = modelMapperFactory.getLooseModelMapper().map(dto, Metric.class);

		newMetric.setCreationDate(LocalDate.now());
		newMetric.setLastVersionDate(LocalDate.now());
		newMetric.setEntityType(Entity.Metric);
		newMetric.setVersion("0");
		MetricCrudDTO dtoCrud = new MetricCrudDTO();
		dtoCrud.setRequest("create Metric");
		dtoCrud.addMetricToList(
				modelMapperFactory.getLooseModelMapper().map(metricRepository.save(newMetric), MetricDTO.class));
		return dtoCrud;
	}

	/**
	 * This method updates a Metric. This is useful when a Metricator modifies the Metric's fields.
	 * ModelMapper is used to retrieve the old Metric instance.
	 * @param  dto MetricDTO
	 * @return      MetricCrudDTO
	 */
	@Override
	public MetricCrudDTO updateMetric(MetricDTO dto)
			throws BadInputException, IllegalStateTransitionException, NotFoundException, DBException {
		if (dto == null) {
			throw new BadInputException("Bad Input");
		}
		if (dto.getMetadata().getId() == null) {
			throw new BadInputException("Metrics cannot have null ID");
		}
		/**
		 *
		 * Note that an Update will be executed IFF dto contains version number
		 * equals to version on MongoDB
		 *
		 **/
		String id = dto.getMetadata().getId();
		Metric newMetric = modelMapperFactory.getLooseModelMapper().map(dto, Metric.class);
		Metric oldMetric = metricRepository.findMetricById(id);
		stateTransition(oldMetric, newMetric);

		MetricCrudDTO dtoCrud = new MetricCrudDTO();
		dtoCrud.setRequest("update Metric id" + id);
		if (oldMetric == null) {
			throw new NotFoundException();
		}

		try {
			dtoCrud.addMetricToList(
					modelMapperFactory.getLooseModelMapper().map(metricRepository.save(newMetric), MetricDTO.class));
		} catch (Exception e) {
			throw new DBException("Error in saving, tipically your version is not the last");
		}

		return dtoCrud;
	}
	
	/**
	 * This method just change state to a Metric. This is useful when a project manager
	 * just needs to approve/reject a Metric without change the Metric's fields. 
	 * ModelMapper is used just to clone the Metric instance, so the set is made manually.
	 * @param  dto, in which must be id, releaseNote, state
	 * @return      MetricCrudDTO
	 */
	@Override
	public MetricCrudDTO changeStateMetric(MetricDTO dto)
			throws BadInputException, IllegalStateTransitionException, NotFoundException, DBException {
		if (dto == null) {
			throw new BadInputException("Bad Input");
		}
		if (dto.getMetadata().getId() == null) {
			throw new BadInputException("Metrics cannot have null ID");
		}
		
		Metric oldMetric = new Metric();
		Metric newMetric = metricRepository.findMetricById(dto.getMetadata().getId());//modelMapperFactory.getLooseModelMapper().map(oldMetric, Metric.class);
		oldMetric.setState(newMetric.getState());

		newMetric.setState(dto.getMetadata().getState());
		newMetric.setReleaseNote(dto.getMetadata().getReleaseNote());
		oldMetric.setVersion(newMetric.getVersion());
		stateTransition(oldMetric, newMetric);
		
		MetricCrudDTO dtoCrud = new MetricCrudDTO();
		dtoCrud.setRequest("update Metric id" + dto.getMetadata().getId());
		
		try {
			dtoCrud.addMetricToList(
					modelMapperFactory.getLooseModelMapper().map(metricRepository.save(newMetric), MetricDTO.class));
		} catch (Exception e) {
			throw new DBException("Error in saving, tipically your version is not the last");
		}

		return dtoCrud;
	}
	

	@Override
	public void deleteMetricById(String id) throws BadInputException, IllegalStateTransitionException {
		if (id == null) {
			throw new BadInputException("Bad Input");
		}
		if (!metricRepository.findMetricById(id).getState().equals(State.Suspended)) {
			throw new IllegalStateTransitionException("A metric must be Suspended before delete");
		}
		metricRepository.delete(id);
	}

	private void stateTransition(Metric oldMetric, Metric newMetric)
			throws IllegalStateTransitionException, NotFoundException {
		System.out.println(oldMetric.getState() + "->" + newMetric.getState());
		newMetric.setLastVersionDate(LocalDate.now());
		if (oldMetric.getState().equals(newMetric.getState())) {
			 return;
			 }
		try {
			AbstractStateTransitionFactory.getFactory(Entity.Metric).transition(oldMetric, newMetric).execute();
		} catch (Exception e) {
			throw new IllegalStateTransitionException(e);
		}
	}
	
	@Override
	public long countMetricByState(String state, String userId) throws BadInputException, NotFoundException{
		if (state == null) {
			throw new BadInputException("State cannot be null");
		}
		return metricRepository.countByStateAndMetricatorId(State.valueOf(state), userId);
	}
	
	@Override
	public MetricCrudDTO getMetricByStateAndMetricatorId(String state, String userId) throws NotFoundException, BadInputException {
		if (state == null) {
			throw new BadInputException("Metric state cannot be null");
		}
		ArrayList<Metric> metrics = metricRepository.findByStateAndMetricatorId(State.valueOf(state),userId);
		if (metrics.size() == 0) {
			throw new NotFoundException("State " + state + " has no Metrics");
		}
		
		MetricCrudDTO dto = new MetricCrudDTO();
		dto.setRequest("Metric of " + userId);
		Iterator<Metric> metricIter = metrics.iterator();
		while (metricIter.hasNext()) {
			dto.addMetricToList(modelMapperFactory.getStandardModelMapper().map(metricIter.next(), MetricDTO.class));
		}
		
		return dto;
	}
	
	@Override
	public MetricCrudDTO getMetricByState(String state) throws NotFoundException, BadInputException {
		if (state == null) {
			throw new BadInputException("Metric state cannot be null");
		}
		ArrayList<Metric> metrics = metricRepository.findByState(State.valueOf(state));
		if (metrics.size() == 0) {
			throw new NotFoundException("State " + state + " has no Metrics");
		}
		
		MetricCrudDTO dto = new MetricCrudDTO();
		Iterator<Metric> metricIter = metrics.iterator();
		while (metricIter.hasNext()) {
			dto.addMetricToList(modelMapperFactory.getStandardModelMapper().map(metricIter.next(), MetricDTO.class));
		}
		
		return dto;
	}


}
