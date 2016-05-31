package metricapp.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import metricapp.dto.MetricDTO;
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
	
	@Autowired
	private void ModelMapperFactoryInit(){
		this.modelMapper = new ModelMapper();
	}
}
