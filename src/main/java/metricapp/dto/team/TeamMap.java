package metricapp.dto.team;

import org.modelmapper.PropertyMap;

import metricapp.entity.stakeholders.Team;

public class TeamMap extends PropertyMap<Team, TeamDTO>{

	@Override
	protected void configure() {
		
		map().setId(source.getId());
		map().setExpert(source.getExpert());
		map().setMetricators(source.getMetricators());
		map().setGridName(source.getGridName());
		map().setName(source.getName());
		map().setQuestioners(source.getQuestioners());
		map().setTsCreate(source.getTsCreate());
		map().setTsUpdate(source.getTsUpdate());
	}

}
