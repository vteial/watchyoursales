package io.ahaitech.harmoney.service.impl;

import io.ahaitech.harmoney.model.Country;
import io.ahaitech.harmoney.repository.CountryRepository;
import io.ahaitech.harmoney.service.AutoNumberService;
import io.ahaitech.harmoney.service.CountryService;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;

@Service
@Slf4j
public class DefaultCountryService extends AbstractDataService implements
		CountryService {

	@Resource
	ClassPathResource jsonCountryCpr;

	@Resource
	ObjectMapper jsonObjectMapper;

	@Resource
	AutoNumberService autoNumberService;

	@Resource
	CountryRepository countryRepository;

	List<Country> countrys;

	@Transactional
	@Override
	public void reset() {
		log.info("Reset Country started...");
		try {
			TypeFactory typeFactory = jsonObjectMapper.getTypeFactory();
			CollectionType collectionType = typeFactory
					.constructCollectionType(List.class, Country.class);
			List<Country> entitys = jsonObjectMapper.readValue(
					jsonCountryCpr.getInputStream(), collectionType);
			this.countryRepository.save(entitys);
		} catch (Throwable t) {
			Throwables.propagate(t);
		}
		log.info("Reset Country finished...");
	}

	@Transactional(readOnly = true)
	@PostConstruct
	@Override
	public void load() {

		List<Country> models = this.countryRepository.findAll();
		this.countrys = ImmutableList.copyOf(models);

	}

	@Transactional
	@PreDestroy
	@Override
	public void save() {
	}

	@Override
	public List<Country> findAll() {

		return this.countrys;

	}

}
