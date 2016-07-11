package metricapp.service.spec.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import metricapp.dto.metric.MetricCrudDTO;
import metricapp.dto.metric.MetricDTO;
import metricapp.entity.external.PointerBus;
import metricapp.exception.BadInputException;
import metricapp.exception.BusException;
import metricapp.exception.DBException;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;

public interface MetricCRUDInterface {
	
	public MetricCrudDTO getMetricById(String id) throws BadInputException, NotFoundException ;
	public MetricCrudDTO getMetricByIdAndVersion(String id, String version) throws BadInputException, NotFoundException;
	public MetricCrudDTO getMetricOfUser(String userId) throws NotFoundException, BadInputException ;
	public MetricCrudDTO updateMetric(MetricDTO dto) throws BadInputException, IllegalStateTransitionException, NotFoundException, DBException ;
	public void deleteMetricById(String id) throws BadInputException, IllegalStateTransitionException ;
	public MetricDTO getMetricByIdLastApprovedVersion(String id) throws BadInputException, NotFoundException, BusException, IOException ;
	public MetricCrudDTO createMetric(MetricDTO dto) throws BadInputException;
	public MetricCrudDTO changeStateMetric(MetricDTO dto) throws BadInputException, IllegalStateTransitionException, NotFoundException, DBException ;
	public MetricCrudDTO getMetricCrudDTOByIdLastApprovedVersion(String id)
			throws BadInputException, NotFoundException, BusException, IOException;
	long countMetricByState(String state, String userId) throws BadInputException, NotFoundException;
	MetricCrudDTO getMetricByState(String state) throws NotFoundException, BadInputException;
	MetricCrudDTO getMetricByStateAndMetricatorId(String state, String userId)
			throws NotFoundException, BadInputException;
	ArrayList<MetricDTO> getMetricsByPointerBusList(List<PointerBus> list);
		
}
