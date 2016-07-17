package metricapp.dto.analytics;

import java.util.ArrayList;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import metricapp.dto.MessageDTO;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
public class AnalyticsCrudDTO extends MessageDTO {
	
	private static final long serialVersionUID = 3333729191385316613L;
	
	private long count;	
	public ArrayList<AnalyticsDTO> analyticsDTO;
	
	
	public AnalyticsCrudDTO() {
		this.setAnalyticsDTO(new ArrayList<AnalyticsDTO>());
	}
	
	public void addAnalyticsToList(AnalyticsDTO analytics) {
		try {
			this.analyticsDTO.add(analytics);
		} catch(NullPointerException e) {
			this.analyticsDTO = new ArrayList<AnalyticsDTO>();
			this.analyticsDTO.add(analytics);
		}		
	}
}
