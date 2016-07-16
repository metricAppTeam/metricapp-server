package metricapp.utility;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import metricapp.dto.question.QuestionDTOMap;
import metricapp.dto.question.QuestionMap;
import metricapp.dto.topic.TopicDTOMap;
import metricapp.dto.topic.TopicMap;
import metricapp.service.spec.ModelMapperFactoryInterface;
import metricapp.dto.event.EventDTOMap;
import metricapp.dto.event.EventMap;
import metricapp.dto.measurementGoal.MeasurementGoalDTOMap;
import metricapp.dto.measurementGoal.MeasurementGoalMap;
import metricapp.dto.metric.MetricDTOMap;
import metricapp.dto.metric.MetricMap;
import metricapp.dto.notification.NotificationDTOMap;
import metricapp.dto.notification.NotificationMap;
import metricapp.dto.notification.box.NotificationBoxDTOMap;
import metricapp.dto.notification.box.NotificationBoxMap;

/**
 * This class is a Utility with the scope of managing ModelMapper instances.
 *
 * It implements Factory pattern to offers different configurations of the same class.
 * Spring is needed to implements simple Singleton pattern of the instances.
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
     * e.g. modelMapperFactory.getStandardModelMapper().map(objectToMap, TargetObject.class) = TargetObject targetObject
     * @return
     */
	public ModelMapper getStandardModelMapper(){
		return strictModelMapper;
	}

    /**
     * This function returns the ModelMapper object configurated as Loose Mapping strategy
     *
     * e.g. modelMapperFactory.getStandardModelMapper().map(objectToMap, TargetObject.class) = TargetObject targetObject
     * @return
     */
	public ModelMapper getLooseModelMapper(){
		return looseModelMapper;
	}

    /**
     * This function returns the ModelMapper object configurated as Strict Mapping strategy
     *
     * e.g. modelMapperFactory.getStandardModelMapper().map(objectToMap, TargetObject.class) = TargetObject targetObject
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
		this.looseModelMapper = modelMapperFactoryInit(this.looseModelMapper);
        looseModelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		this.standardModelMapper=  modelMapperFactoryInit(this.standardModelMapper);
        standardModelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        this.strictModelMapper = modelMapperFactoryInit(this.strictModelMapper);
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
		modelMapper.addMappings(new MeasurementGoalDTOMap());
		modelMapper.addMappings(new MeasurementGoalMap());
		modelMapper.addMappings(new NotificationMap());
		modelMapper.addMappings(new NotificationDTOMap());
		modelMapper.addMappings(new NotificationBoxMap());
		modelMapper.addMappings(new NotificationBoxDTOMap());
		modelMapper.addMappings(new EventMap());
		modelMapper.addMappings(new EventDTOMap());
		modelMapper.addMappings(new TopicMap());
		modelMapper.addMappings(new TopicDTOMap());
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
