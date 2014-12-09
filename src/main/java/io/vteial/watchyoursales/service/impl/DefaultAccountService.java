package io.vteial.watchyoursales.service.impl;

import io.vteial.watchyoursales.model.Account;
import io.vteial.watchyoursales.repository.AccountRepository;
import io.vteial.watchyoursales.service.AccountService;

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
public class DefaultAccountService extends AbstractDataService implements
		AccountService {

	@Resource
	ClassPathResource jsonAccountCpr;

	@Resource
	ObjectMapper jsonObjectMapper;

	@Resource
	AccountRepository accountRepository;

	@Transactional
	@Override
	public void reset() {
		log.info("Reset Account started...");
		try {
			TypeFactory typeFactory = jsonObjectMapper.getTypeFactory();
			CollectionType collectionType = typeFactory
					.constructCollectionType(List.class, Account.class);
			List<Account> entitys = jsonObjectMapper.readValue(
					jsonAccountCpr.getInputStream(), collectionType);
			this.accountRepository.save(entitys);
		} catch (Throwable t) {
			Throwables.propagate(t);
		}
		log.info("Reset Account finished...");
	}
}
