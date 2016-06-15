package metricapp.utility;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import metricapp.dto.question.QuestionDTOMap;
import metricapp.dto.question.QuestionMap;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.dto.metric.MetricDTOMap;
import metricapp.dto.metric.MetricMap;

/**
 * This class is a Utility with the scope of managing ModelMapper instances.
 *
 * It implements Factory pattern to offers different configurations of the same class.
 * Spring is needed to implements simple Singleton pattern of the instances.
 *
 * The module is loaded with different instances, with different configuration, to guarantee performances in multithread scenarios
 */
@Service
public class ModelMapperFactory implements ModelMapperFactoryInterface{
	
	private ModelMapper strictModelMapper;
    private ModelMapper looseModelMapper;
    private ModelMapper standardModelMapper;

    /**
     * This function returns the ModelMapper object configurated as Standard Mapping strategy
     *
     * e.g. modelMapperFactory.getStandardModelMapper().map(objectToMap, TargetObject.class) -> TargetObject targetObject
     * @return
     */
	public ModelMapper getStandardModelMapper(){
		return strictModelMapper;
	}

    /**
     * This function returns the ModelMapper object configurated as Loose Mapping strategy
     *
     * e.g. modelMapperFactory.getStandardModelMapper().map(objectToMap, TargetObject.class) -> TargetObject targetObject
     * @return
     */
	public ModelMapper getLooseModelMapper(){
		return looseModelMapper;
	}

    /**
     * This function returns the ModelMapper object configurated as Strict Mapping strategy
     *
     * e.g. modelMapperFactory.getStandardModelMapper().map(objectToMap, TargetObject.class) -> TargetObject targetObject
     * @return
     */
	public ModelMapper getStrictModelMapper(){
		return strictModelMapper;
	}

    /**
     * This method is needed to configure and create new instance of internal modelMapper objects.
     * It is not intended for external use.
     */
    @Autowired
    private void modelMapperInitAll(){
        modelMapperFactoryInit(this.looseModelMapper);
        looseModelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapperFactoryInit(this.standardModelMapper);
        standardModelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        modelMapperFactoryInit(this.strictModelMapper);
        strictModelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * This method configures a ModelMapper instance to use our converters and maps.
     * It is not intended for external use
     * @param modelMapper
     * @return
     */
	private ModelMapper modelMapperFactoryInit(ModelMapper modelMapper){
		if(modelMapper == null){
			modelMapper = new ModelMapper();
		}
		modelMapper.addMappings(new MetricMap()); 
		modelMapper.addMappings(new MetricDTOMap());
		modelMapper.addConverter(ModelMapperUtility.localDateToString());
		modelMapper.addConverter(ModelMapperUtility.stringToLocalDate());
		modelMapper.addMappings(new QuestionMap());
		modelMapper.addMappings(new QuestionDTOMap());
        return modelMapper;
		
	}

    /**
     * This function permits the user to have a new ModelMapper instance, unlinked with internal instances, configured with
     * our converters and maps.
     * It is useful in debugging mappers, it is not intended for normal use.
     */
	public void modelMapperFactoryExternalInit(){
		modelMapperFactoryInit(new ModelMapper());
	}
}
