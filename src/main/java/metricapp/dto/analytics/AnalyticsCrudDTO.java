package metricapp.dto.analytics;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import metricapp.dto.SimpleDTO;
import metricapp.dto.analytics.AnalyticsDTO;
import metricapp.entity.analytics.Analytics;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
public class AnalyticsCrudDTO extends SimpleDTO {
	
	private static final long serialVersionUID = 3333729191385316613L;
	
	private long count;	
	public ArrayList<AnalyticsDTO> analyticsDTO;	
	
	public AnalyticsCrudDTO() {
		this.setAnalyticsDTO(new ArrayList<AnalyticsDTO>());
	}
	
	public void addAnalyticsDTO(AnalyticsDTO analytics) {
		if (this.analyticsDTO == null) {
			this.analyticsDTO = new ArrayList<AnalyticsDTO>();
		}
		this.analyticsDTO.add(analytics);		
		this.count = this.analyticsDTO.size();
	}
	
	public void addAllAnalyticsDTO(List<AnalyticsDTO> analytics) {
		if (this.analyticsDTO == null) {
			this.analyticsDTO = new ArrayList<AnalyticsDTO>();
		}
		for (AnalyticsDTO a : analytics) {
			this.analyticsDTO.add(a);
		}
		this.count = this.analyticsDTO.size();
	}
	
	public void addAnalytics(Analytics analytics, ModelMapper mapper) {
		if (this.analyticsDTO == null) {
			this.analyticsDTO = new ArrayList<AnalyticsDTO>();
		}
		AnalyticsDTO analyticsDTO = mapper.map(analytics, AnalyticsDTO.class);
		this.analyticsDTO.add(analyticsDTO);		
		this.count = this.analyticsDTO.size();
	}
	
	public void addAllAnalytics(List<Analytics> analytics, ModelMapper mapper) {
		if (this.analyticsDTO == null) {
			this.analyticsDTO = new ArrayList<AnalyticsDTO>();
		}
		for (Analytics a : analytics) {
			AnalyticsDTO analyticsDTO = mapper.map(a, AnalyticsDTO.class);
			this.analyticsDTO.add(analyticsDTO);
		}
		this.count = this.analyticsDTO.size();
	}
}
