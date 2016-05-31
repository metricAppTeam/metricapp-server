package metricapp.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.MetricDTO;
import metricapp.entity.State;
import metricapp.entity.metric.Metric;
import metricapp.entity.metric.Set;
import metricapp.entity.stakeholders.Metricator;
import metricapp.service.spec.MetricCRUDInterface;
import metricapp.service.spec.MetricRepository;
import metricapp.service.spec.ModelMapperFactoryInterface;

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
		metrica.setMetricator(new Metricator());
		metrica.getMetricator().setId("6");
		metrica.setName("nome metrica");
		metrica.setOrdered(true);
		metrica.setSet(Set.integers);
		metrica.setReleaseNote("release note");
		metrica.setState(State.OnUpdate);
		metrica.setTags("pioggia","tempo","casa");
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
}
