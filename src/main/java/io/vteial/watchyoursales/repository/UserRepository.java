package io.vteial.watchyoursales.repository;

import io.vteial.watchyoursales.model.User;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends
		PagingAndSortingRepository<User, String> {

}
