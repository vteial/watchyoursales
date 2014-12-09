package io.ahaitech.harmoney.service.impl;

import io.ahaitech.harmoney.model.Address;
import io.ahaitech.harmoney.repository.AddressRepository;
import io.ahaitech.harmoney.service.AddressService;
import io.ahaitech.harmoney.service.AutoNumberService;

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
public class DefaultAddressService extends AbstractDataService implements
		AddressService {

	@Resource
	ClassPathResource jsonAddressCpr;

	@Resource
	ObjectMapper jsonObjectMapper;

	@Resource
	AutoNumberService autoNumberService;

	@Resource
	AddressRepository addressRepository;

	@Transactional
	@Override
	public void reset() {
		log.info("Reset Address started...");
		try {
			TypeFactory typeFactory = jsonObjectMapper.getTypeFactory();
			CollectionType collectionType = typeFactory
					.constructCollectionType(List.class, Address.class);
			List<Address> entitys = jsonObjectMapper.readValue(
					jsonAddressCpr.getInputStream(), collectionType);
			this.addressRepository.save(entitys);
		} catch (Throwable t) {
			Throwables.propagate(t);
		}
		log.info("Reset Address finished...");
	}
}
