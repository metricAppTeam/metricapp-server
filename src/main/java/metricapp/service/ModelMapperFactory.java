package metricapp.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.metric.MetricDTOMap;
import metricapp.dto.metric.MetricMap;
import metricapp.service.spec.ModelMapperFactoryInterface;

@Service
public class ModelMapperFactory implements ModelMapperFactoryInterface{
	
	private ModelMapper modelMapper;
	
	public ModelMapper getStandardModelMapper(){
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		return modelMapper;
	}
	
	public ModelMapper getLooseModelMapper(){
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper;
	}
	
	public ModelMapper getStrictModelMapper(){
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
	
	@Autowired
	private void modelMapperFactoryInit(){
		if(this.modelMapper == null){
			this.modelMapper = new ModelMapper();
		}
		modelMapper.addMappings(new MetricMap()); 
		modelMapper.addMappings(new MetricDTOMap());
	}
	
	public void modelMapperFactoryExternalInit(){
		if(this.modelMapper == null){
			this.modelMapper = new ModelMapper();
		}
	}
}
