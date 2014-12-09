package io.vteial.watchyoursales.repository;

import io.vteial.watchyoursales.model.Employee;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends
		PagingAndSortingRepository<Employee, Long> {

}
