package io.ahaitech.harmoney.repository;

import io.ahaitech.harmoney.model.Address;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AddressRepository extends
		PagingAndSortingRepository<Address, Long> {

}
