package metricapp.entity.metric;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;

import lombok.Getter;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import metricapp.entity.Element;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
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
	
}
