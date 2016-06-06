package metricapp.service.controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.metric.MetricCrudDTO;
import metricapp.dto.metric.MetricDTO;
import metricapp.entity.State;
import metricapp.entity.metric.Metric;
import metricapp.entity.metric.Set;
import metricapp.service.spec.controller.MetricCRUDInterface;
import metricapp.service.spec.controller.ModelMapperFactoryInterface;
import metricapp.service.spec.repository.MetricRepository;

@Service
public class MetricCRUDController implements MetricCRUDInterface{
	
	@Autowired
	private MetricRepository metricRepository;
	
	@Autowired
	private ModelMapperFactoryInterface modelMapperFactory;
	
	public MetricDTO prova(){
		Metric metrica = new Metric();
		metrica.setHasMax(true);
		metrica.setHasMin(false);
		metrica.setMax(10);
		metrica.setMin(0);
		metrica.setHasUserDefinedList(false);
		metrica.setId("5");

		metrica.setName("nome metrica");
		metrica.setOrdered(true);
		metrica.setSet(Set.integers);
		metrica.setReleaseNote("release note");
		metrica.setState(State.OnUpdate);
		metrica.setTagsByList("pioggia","tempo","casa");
		metrica.setUnit("m/s");
		metrica.setDescription("prova");
		metrica.setCreatorId("33");
		metrica.setUserDefinedList("1","3");
		metricRepository.save(metrica);
		
		ModelMapper modelMapper = modelMapperFactory.getLooseModelMapper();
		MetricDTO metricDTO = modelMapper.map(metrica, MetricDTO.class);
		System.out.println(metricDTO.toString());
		return metricDTO;
	}

	@Override
	public MetricCrudDTO getMetricById(String id) {
		Metric metric = metricRepository.findMetricById(id);
		MetricCrudDTO dto = new MetricCrudDTO();
		System.out.println(dto.toString());
		dto.addMetricToList( modelMapperFactory.getLooseModelMapper().map(metric, MetricDTO.class) );
		return dto;
	}

	@Override
	public MetricCrudDTO getMetricByIdAndVersion(String id, String version) {
		Metric metric = metricRepository.findMetricByIdAndVersion(id,version);
		MetricCrudDTO dto = new MetricCrudDTO();
		dto.addMetricToList( modelMapperFactory.getLooseModelMapper().map(metric, MetricDTO.class) );
		return dto;
	}

	@Override
	public MetricCrudDTO getMetricOfUser(String userId) {
		ArrayList<Metric> metrics = metricRepository.findMetricByMetricatorId(userId);
		MetricCrudDTO dto = new MetricCrudDTO();
		
		Iterator<Metric> metricP = metrics.iterator();
		while(metricP.hasNext()){
			dto.addMetricToList( modelMapperFactory.getLooseModelMapper().map(metricP.next(), MetricDTO.class) );
		}
		return dto;
	}

	@Override
	public MetricCrudDTO createMetric(MetricDTO dto) {
		System.out.println(dto.toString());
		
		Metric newMetric = modelMapperFactory.getLooseModelMapper().map(dto, Metric.class);
		System.out.println(newMetric.toString());
		newMetric.setId(null);
		MetricCrudDTO dtoCrud = new MetricCrudDTO();
		dtoCrud.addMetricToList( modelMapperFactory.getLooseModelMapper().map(metricRepository.save(newMetric), MetricDTO.class) );
		return dtoCrud;
	}

	@Override
	public MetricCrudDTO updateMetric(MetricDTO dto) {
		/**
		*
		*Note that an Update will be executed IFF dto contains version number equals to version on MongoDB
		*
		**/
		Metric newMetric = modelMapperFactory.getLooseModelMapper().map(dto, Metric.class);
		MetricCrudDTO dtoCrud = new MetricCrudDTO();
		dtoCrud.addMetricToList( modelMapperFactory.getLooseModelMapper().map(metricRepository.save(newMetric), MetricDTO.class) );
		return dtoCrud;
	}

	@Override
	public void deleteMetricById(String id) {
		metricRepository.delete(id);
	}
}
