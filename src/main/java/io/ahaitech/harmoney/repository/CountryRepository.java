package io.ahaitech.harmoney.repository;

import io.ahaitech.harmoney.model.Country;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {

}
