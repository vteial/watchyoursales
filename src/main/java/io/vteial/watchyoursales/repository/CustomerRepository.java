package io.vteial.watchyoursales.repository;

import io.vteial.watchyoursales.model.Customer;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends
		PagingAndSortingRepository<Customer, Long> {

}
