package metricapp.service.spec;

import metricapp.dto.metric.MetricCrudDTO;
import metricapp.dto.metric.MetricDTO;

public interface MetricCRUDInterface {
	
	public MetricCrudDTO getMetricById(String id);
	public MetricCrudDTO getMetricByIdAndVersion(String id, String version);
	public MetricCrudDTO getMetricOfUser(String userId);
	public MetricCrudDTO createMetric(MetricDTO dto);
	public MetricCrudDTO updateMetric(MetricDTO dto);
	public void deleteMetricById(String id);
		
}
