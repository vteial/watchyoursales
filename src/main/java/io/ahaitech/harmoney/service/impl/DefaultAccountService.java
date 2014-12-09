package io.ahaitech.harmoney.service.impl;

import io.ahaitech.harmoney.model.Account;
import io.ahaitech.harmoney.repository.AccountRepository;
import io.ahaitech.harmoney.service.AccountService;

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
