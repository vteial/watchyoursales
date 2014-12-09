package io.vteial.watchyoursales.service.impl;

import io.vteial.watchyoursales.model.Role;
import io.vteial.watchyoursales.repository.RoleRepository;
import io.vteial.watchyoursales.service.AutoNumberService;
import io.vteial.watchyoursales.service.RoleService;

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
public class DefaultRoleService extends AbstractDataService implements
		RoleService {

	@Resource
	ClassPathResource jsonRoleCpr;

	@Resource
	ObjectMapper jsonObjectMapper;

	@Resource
	AutoNumberService autoNumberService;

	@Resource
	RoleRepository roleRepository;

	@Transactional
	@Override
	public void reset() {
		log.info("Reset Role started...");
		try {
			TypeFactory typeFactory = jsonObjectMapper.getTypeFactory();
			CollectionType collectionType = typeFactory
					.constructCollectionType(List.class, Role.class);
			List<Role> entitys = jsonObjectMapper.readValue(
					jsonRoleCpr.getInputStream(), collectionType);
			this.roleRepository.save(entitys);
		} catch (Throwable t) {
			Throwables.propagate(t);
		}
		log.info("Reset Role finished...");
	}
}
