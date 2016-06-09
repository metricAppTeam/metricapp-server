package metricapp.service.spec.controller;

import metricapp.dto.metric.MetricCrudDTO;
import metricapp.dto.metric.MetricDTO;
import metricapp.service.spec.exception.BadInputException;
import metricapp.service.spec.exception.DBException;
import metricapp.service.spec.exception.IllegalStateTransitionException;
import metricapp.service.spec.exception.NotFoundException;

public interface MetricCRUDInterface {
	
	public MetricCrudDTO getMetricById(String id) throws BadInputException, NotFoundException ;
	public MetricCrudDTO getMetricByIdAndVersion(String id, String version) throws BadInputException, NotFoundException;
	public MetricCrudDTO getMetricOfUser(String userId) throws NotFoundException, BadInputException ;
	public MetricCrudDTO updateMetric(MetricDTO dto) throws BadInputException, IllegalStateTransitionException, NotFoundException, DBException ;
	public void deleteMetricById(String id) throws BadInputException ;
	public MetricCrudDTO getMetricByIdLastApprovedVersion(String id) throws BadInputException, NotFoundException ;
	public MetricCrudDTO createMetric(MetricDTO dto) throws BadInputException;
		
}
