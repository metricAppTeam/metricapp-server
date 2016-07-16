package metricapp.dto.team;

import org.modelmapper.PropertyMap;

import metricapp.entity.stakeholders.Team;

public class TeamMap extends PropertyMap<Team, TeamDTO>{

	@Override
	protected void configure() {
		
		map().setId(source.getId());
		map().setExpert(source.getExpert());
		map().setMetricators(source.getMetricators());
		map().setGrid_name(source.getGrid_name());
		map().setName(source.getName());
		map().setQuestioners(source.getQuestioners());
		map().setTs_create(source.getTs_create());
		map().setTs_update(source.getTs_update());
	}

}
