package metricapp.service.spec.controller;

import metricapp.dto.metric.MetricCrudDTO;
import metricapp.dto.metric.MetricDTO;
import metricapp.exception.BadInputException;
import metricapp.exception.DBException;
import metricapp.exception.IllegalStateTransitionException;
import metricapp.exception.NotFoundException;

public interface MetricCRUDInterface {
	
	public MetricCrudDTO getMetricById(String id) throws BadInputException, NotFoundException ;
	public MetricCrudDTO getMetricByIdAndVersion(String id, String version) throws BadInputException, NotFoundException;
	public MetricCrudDTO getMetricOfUser(String userId) throws NotFoundException, BadInputException ;
	public MetricCrudDTO updateMetric(MetricDTO dto) throws BadInputException, IllegalStateTransitionException, NotFoundException, DBException ;
	public void deleteMetricById(String id) throws BadInputException, IllegalStateTransitionException ;
	public MetricCrudDTO getMetricByIdLastApprovedVersion(String id) throws BadInputException, NotFoundException ;
	public MetricCrudDTO createMetric(MetricDTO dto) throws BadInputException;
		
}
