package metricapp.service.repository;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import metricapp.dto.user.UserBus;
import metricapp.entity.external.KeyValue;
import metricapp.entity.stakeholders.User;
import metricapp.exception.BusException;
import metricapp.service.spec.repository.BusInterface;
import metricapp.service.spec.repository.BusUserRepositoryInterface;
import metricapp.utility.JacksonMapper;
import metricapp.utility.ModelMapperFactory;

@Service
public class BusUserRepository implements BusUserRepositoryInterface {

	@Autowired
	private BusInterface busRepository;

	@Autowired
	private JacksonMapper mapper;

	@Autowired
	private ModelMapperFactory modelMapperFactory;

	@Override
	public User findUserByUsername(String username) throws BusException {
		KeyValue usernameKV = new KeyValue("username", username);

		try {
			String result = this.busRepository.getUser(usernameKV).get(0);
			UserBus userBus = mapper.fromJson(result, UserBus.class);
			return modelMapperFactory.getStandardModelMapper().map(userBus, User.class);
		} catch (IOException e) {
			throw new BusException(e);
		}
	}

	@Override
	public User registerUser(User user) throws BusException {
		try {
			UserBus userBus = modelMapperFactory.getStandardModelMapper().map(user, UserBus.class);
			String result = this.busRepository.registerUser(userBus).get(0);
			userBus = mapper.fromJson(result, UserBus.class);
			return modelMapperFactory.getStandardModelMapper().map(userBus, User.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusException(e);
		}
	}

}
