package io.ahaitech.harmoney.repository;

import io.ahaitech.harmoney.model.Customer;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends
		PagingAndSortingRepository<Customer, Long> {

}
