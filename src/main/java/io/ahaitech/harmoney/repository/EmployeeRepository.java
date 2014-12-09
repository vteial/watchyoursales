package io.ahaitech.harmoney.repository;

import io.ahaitech.harmoney.model.Employee;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends
		PagingAndSortingRepository<Employee, Long> {

}
