package io.ahaitech.harmoney.repository;

import io.ahaitech.harmoney.model.User;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends
		PagingAndSortingRepository<User, String> {

}
