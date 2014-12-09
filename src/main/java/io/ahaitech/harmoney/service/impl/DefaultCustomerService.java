package io.ahaitech.harmoney.service.impl;

import io.ahaitech.harmoney.model.Customer;
import io.ahaitech.harmoney.repository.CustomerRepository;
import io.ahaitech.harmoney.service.AutoNumberService;
import io.ahaitech.harmoney.service.CustomerService;

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
