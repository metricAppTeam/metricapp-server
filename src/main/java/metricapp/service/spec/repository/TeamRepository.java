package metricapp.service.spec.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import metricapp.entity.stakeholders.Team;
@RepositoryRestResource(exported = false)
public interface TeamRepository extends MongoRepository<Team, String> {

	public Team findTeamById(String id);
	
}
