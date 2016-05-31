package metricapp.dto;

import java.io.Serializable;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import lombok.Data;
import metricapp.entity.State;
import metricapp.entity.metric.Metric;
import metricapp.entity.metric.Set;
import metricapp.entity.stakeholders.Metricator;

@Data
public class MetricDTO implements Serializable{

	private static final long serialVersionUID = -2073939437602304884L;
	public String name;
	public String description;
	public String metricatorId;
	public boolean hasMax;
	public boolean hasMin;
	public double max;
	public double min;
	public boolean hasUserDefinedList;
	public List<String> userDefinedList;
	public String unit;
	public boolean isOrdered;
	public String set;
	public MetadataDTO metadata;
	
	public static void main(String[] args) {
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
		
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		MetricDTO metricDTO = modelMapper.map(metrica, MetricDTO.class);
		System.out.println(metricDTO.toString());
		
		
	}
	
}
