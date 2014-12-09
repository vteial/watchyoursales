package io.vteial.watchyoursales.service.impl;

import io.vteial.watchyoursales.model.Customer;
import io.vteial.watchyoursales.repository.CustomerRepository;
import io.vteial.watchyoursales.service.AutoNumberService;
import io.vteial.watchyoursales.service.CustomerService;

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
public class DefaultCustomerService extends AbstractDataService implements
		CustomerService {

	@Resource
	ClassPathResource jsonCustomerCpr;

	@Resource
	ObjectMapper jsonObjectMapper;

	@Resource
	AutoNumberService autoNumberService;

	@Resource
	CustomerRepository customerRepository;

	@Transactional
	@Override
	public void reset() {
		log.info("Reset Customer started...");
		try {
			TypeFactory typeFactory = jsonObjectMapper.getTypeFactory();
			CollectionType collectionType = typeFactory
					.constructCollectionType(List.class, Customer.class);
			List<Customer> entitys = jsonObjectMapper.readValue(
					jsonCustomerCpr.getInputStream(), collectionType);
			this.customerRepository.save(entitys);
		} catch (Throwable t) {
			Throwables.propagate(t);
		}
		log.info("Reset Customer finished...");
	}

}
