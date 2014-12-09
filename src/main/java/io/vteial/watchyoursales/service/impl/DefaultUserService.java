package io.vteial.watchyoursales.service.impl;

import io.vteial.watchyoursales.model.User;
import io.vteial.watchyoursales.repository.UserRepository;
import io.vteial.watchyoursales.service.AutoNumberService;
import io.vteial.watchyoursales.service.UserService;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.base.Throwables;

@Service
@Slf4j
public class DefaultUserService extends AbstractDataService implements
		UserService {

	@Resource
	ClassPathResource jsonUserCpr;

	@Resource
	ObjectMapper jsonObjectMapper;

	@Resource
	AutoNumberService autoNumberService;

	@Resource
	UserRepository userRepository;

	@Transactional
	@Override
	public void reset() {
		log.info("Reset User started...");
		try {
			TypeFactory typeFactory = jsonObjectMapper.getTypeFactory();
			CollectionType collectionType = typeFactory
					.constructCollectionType(List.class, User.class);
			List<User> entitys = jsonObjectMapper.readValue(
					jsonUserCpr.getInputStream(), collectionType);
			this.userRepository.save(entitys);
		} catch (Throwable t) {
			Throwables.propagate(t);
		}
		log.info("Reset User finished...");
	}
}
