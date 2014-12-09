package io.ahaitech.harmoney.service.impl;

import io.ahaitech.harmoney.model.Branch;
import io.ahaitech.harmoney.repository.BranchRepository;
import io.ahaitech.harmoney.service.AutoNumberService;
import io.ahaitech.harmoney.service.BranchService;

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
public class DefaultBranchService extends AbstractDataService implements
		BranchService {

	@Resource
	ClassPathResource jsonBranchCpr;

	@Resource
	ObjectMapper jsonObjectMapper;

	@Resource
	AutoNumberService autoNumberService;

	@Resource
	BranchRepository branchRepository;

	@Transactional
	@Override
	public void reset() {
		log.info("Reset Branch started...");
		try {
			TypeFactory typeFactory = jsonObjectMapper.getTypeFactory();
			CollectionType collectionType = typeFactory
					.constructCollectionType(List.class, Branch.class);
			List<Branch> entitys = jsonObjectMapper.readValue(
					jsonBranchCpr.getInputStream(), collectionType);
			this.branchRepository.save(entitys);
		} catch (Throwable t) {
			Throwables.propagate(t);
		}
		log.info("Reset Branch finished...");
	}
}
