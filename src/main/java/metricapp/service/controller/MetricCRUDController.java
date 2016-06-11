package metricapp.service.controller;

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
import metricapp.exception.DBException;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;
import metricapp.service.spec.controller.MetricCRUDInterface;
import metricapp.service.spec.controller.ModelMapperFactoryInterface;
import metricapp.service.spec.repository.MetricRepository;

@Service
public class MetricCRUDController implements MetricCRUDInterface {

	@Autowired
	private MetricRepository metricRepository;

	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory;

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
	public MetricCrudDTO getMetricByIdLastApprovedVersion(String id) throws BadInputException, NotFoundException {
		if (id == null) {
			throw new BadInputException("Metric id cannot be null");
		}
		Metric metric = modelMapperFactory.getLooseModelMapper()
				.map(metricRepository.findMetricById(id).getLastApprovedMetric(), Metric.class);
		if (metric == null) {
			throw new NotFoundException("Approved Metric with id " + id + "is not available");
		}
		MetricCrudDTO dto = new MetricCrudDTO();
		dto.setRequest("Metric, id=" + id + ";state=Approved");
		dto.addMetricToList(modelMapperFactory.getLooseModelMapper().map(metric, MetricDTO.class));
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
		Metric newMetric = modelMapperFactory.getLooseModelMapper().map(dto, Metric.class);
		Metric oldMetric = metricRepository.findMetricById(newMetric.getId());
		stateTransition(oldMetric, newMetric);

		MetricCrudDTO dtoCrud = new MetricCrudDTO();
		dtoCrud.setRequest("update Metric id" + dto.getMetadata().getId());
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

	private void stateTransition(Metric oldMetric, Metric newMetric) throws IllegalStateTransitionException {
		newMetric.setLastVersionDate(LocalDate.now());
		State before = oldMetric.getState();
		State after = newMetric.getState();
		if (before.equals(after)) {
			return;
		}
		switch (before) {

		case Created:
			if (after.equals(State.OnUpdate)) {
				// TODO alert newMetric.getMetricatorId()
				return;
			} else {
				throw new IllegalStateTransitionException(
						"New State is not applicable: " + after.toString() + " from " + before.toString());
			}
		case OnUpdate:
			if (after.equals(State.Pending)) {
				// TODO alert newMetric.getCreatorId()
				newMetric.setLastApprovedMetric(oldMetric.getLastApprovedMetric());
				return;
			} else {
				throw new IllegalStateTransitionException(
						"New State is not applicable: " + after.toString() + " from " + before.toString());
			}
		case Pending:
			if (after.equals(State.Approved)) {
				// TODO alert newMetric.getMetricatorId() with
				// newMetric.getReleaseNote()
				// TODO send to bus the new metric ->need to convert: wipe securekey, change id, ermesLastVersion
				newMetric.setLastApprovedMetric(
						modelMapperFactory.getLooseModelMapper().map(newMetric, MetricDTO.class));
				return;
			}
			if (after.equals(State.Rejected)) {
				// TODO alert newMetric.getMetricatorId() with
				// newMetric.getReleaseNote()
				newMetric.setLastApprovedMetric(oldMetric.getLastApprovedMetric());
				return;
			} else {
				throw new IllegalStateTransitionException(
						"New State is not applicable: " + after.toString() + " from " + before.toString());
			}
		case Approved:
			if (after.equals(State.OnUpdate)) {
				// TODO alert newMetric.getMetricatorId() with
				// newMetric.getReleaseNote()
				newMetric.setLastApprovedMetric(oldMetric.getLastApprovedMetric());
				return;
			}
			if (after.equals(State.Suspended)) {
				// TODO alert newMetric.getMetricatorId() with
				// newMetric.getReleaseNote()
				newMetric.setLastApprovedMetric(oldMetric.getLastApprovedMetric());
				return;
			} else {
				throw new IllegalStateTransitionException(
						"New State is not applicable: " + after.toString() + " from " + before.toString());
			}
		case Rejected:
			if (after.equals(State.OnUpdate)) {
				// TODO alert newMetric.getMetricatorId() with
				// newMetric.getReleaseNote()
				newMetric.setLastApprovedMetric(oldMetric.getLastApprovedMetric());
				return;
			}
			if (after.equals(State.Suspended)) {
				// TODO alert newMetric.getMetricatorId() with
				// newMetric.getReleaseNote()
				newMetric.setLastApprovedMetric(oldMetric.getLastApprovedMetric());
				return;
			} else {
				throw new IllegalStateTransitionException(
						"New State is not applicable: " + after.toString() + " from " + before.toString());
			}
		case Suspended:
			// TODO check dependencies
			throw new IllegalStateTransitionException(
					"New State is not applicable: " + after.toString() + " from " + before.toString());
		default:
			throw new IllegalStateTransitionException("Before State is not applicable: " + before.toString());
		}
	}

}
