package metricapp.entity.metric;

import java.util.ArrayList;
import java.util.List;


import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import metricapp.entity.Element;
import metricapp.entity.State;
import metricapp.service.RandomGenerator;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
@Document
public class Metric extends Element{
	
	
	private String name;
	private String description;
	private String metricatorId;
	private boolean hasMax;
	private boolean hasMin;
	private boolean hasUserDefinedList;
	private boolean isOrdered;
	private double max;
	private double min;
	private List<String> userDefinedList;
	private String unit;
	private ScaleType scaleType;
	private Set set;
		
	public void setUserDefinedList(String ...strings){
		ArrayList<String> userList = new ArrayList<String>();
		for(String el : strings){
			userList.add(el);
		}
		this.userDefinedList=userList;
	}

	public void setUserDefinedList(ArrayList<String> strings) {
		this.userDefinedList = strings;
		
	}


	public static Metric randomMetric(){
		Metric metric = new Metric();

		metric.setCreationDate(RandomGenerator.randomLocalDate());
		metric.setCreatorId(RandomGenerator.randomString());
		metric.setDescription(RandomGenerator.randomString());
		metric.setHasMax(RandomGenerator.randomBoolean());
		metric.setHasMin(RandomGenerator.randomBoolean());
		metric.setHasUserDefinedList(RandomGenerator.randomBoolean());
		metric.setId(RandomGenerator.randomString());
		metric.setLastVersionDate(RandomGenerator.randomLocalDate());
		metric.setMax(RandomGenerator.randomDouble());
		metric.setMetricatorId(RandomGenerator.randomString());
		metric.setMin(RandomGenerator.randomDouble());
		metric.setName(RandomGenerator.randomString());
		metric.setOrdered(RandomGenerator.randomBoolean());
		metric.setReleaseNote(RandomGenerator.randomString());
		metric.setScaleType(RandomGenerator.randomEnum(ScaleType.class));
		metric.setSet(RandomGenerator.randomEnum(Set.class));
		metric.setState(RandomGenerator.randomEnum(State.class));
		metric.setTags(RandomGenerator.randomArrayList());
		metric.setUnit(RandomGenerator.randomString());
		metric.setUserDefinedList(RandomGenerator.randomArrayList());
		metric.setVersion(RandomGenerator.randomString());

		return metric;
	}

	
}
