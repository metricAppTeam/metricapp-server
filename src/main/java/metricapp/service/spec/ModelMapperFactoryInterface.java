package metricapp.service.spec;

import org.modelmapper.ModelMapper;

public interface ModelMapperFactoryInterface {
	
	public ModelMapper getLooseModelMapper();
	
	public ModelMapper getStandardModelMapper();
	
	public ModelMapper getStrictModelMapper();
	
}
