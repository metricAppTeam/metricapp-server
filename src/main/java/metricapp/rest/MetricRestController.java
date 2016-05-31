package metricapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import metricapp.dto.ResponseDTO;
import metricapp.dto.metric.MetricCrudDTO;
import metricapp.dto.metric.MetricDTO;
import metricapp.service.spec.MetricCRUDInterface;

@RestController
@RequestMapping(("/metric"))
public class MetricRestController {
	@Autowired
	private MetricCRUDInterface metricCRUDController;
	
	

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<MetricCrudDTO> getMetricDTO(@RequestParam(value="id",defaultValue="NA") String id,
			@RequestParam(value="version",defaultValue="NA") String version, @RequestParam(value="userid",defaultValue="NA") String userId){
		MetricCrudDTO dto=null;
		if(!userId.equals("NA")){
			dto = metricCRUDController.getMetricOfUser(userId);
		}
		if(!version.equals("NA") && !id.equals("NA")){
			dto = metricCRUDController.getMetricByIdAndVersion(id,version);
		}
		if(!id.equals("NA")){
			dto = metricCRUDController.getMetricById(id);
		}
		if(dto == null){
			return new ResponseEntity<MetricCrudDTO>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<MetricCrudDTO>(dto,HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO> deleteMetricDTO(@RequestParam String id){
		metricCRUDController.deleteMetricById(id);
		return new ResponseEntity<ResponseDTO>(HttpStatus.OK);

	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO> putMetricDTO(@RequestBody MetricDTO dto){
		metricCRUDController.updateMetric(dto);
		return new ResponseEntity<ResponseDTO>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> postMeasurementGoalDTO(@RequestBody MetricDTO dto){
		metricCRUDController.createMetric(dto);
		return new ResponseEntity<ResponseDTO>(HttpStatus.OK);
	}
	
	
}
