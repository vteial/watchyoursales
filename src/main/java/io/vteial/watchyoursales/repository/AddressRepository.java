package io.vteial.watchyoursales.repository;

import io.vteial.watchyoursales.model.Address;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AddressRepository extends
		PagingAndSortingRepository<Address, Long> {

}
