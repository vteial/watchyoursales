package io.ahaitech.harmoney.repository;

import io.ahaitech.harmoney.model.Account;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends
		PagingAndSortingRepository<Account, Long> {

}
