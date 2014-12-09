package io.vteial.watchyoursales.repository;

import io.vteial.watchyoursales.model.Account;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends
		PagingAndSortingRepository<Account, Long> {

}
