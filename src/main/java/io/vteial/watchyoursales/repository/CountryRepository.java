package io.vteial.watchyoursales.repository;

import io.vteial.watchyoursales.model.Country;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {

}
