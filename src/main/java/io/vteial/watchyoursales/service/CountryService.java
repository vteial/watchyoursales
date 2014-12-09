package io.vteial.watchyoursales.service;

import io.vteial.watchyoursales.model.Country;

import java.util.List;

public interface CountryService extends DataService {

	List<Country> findAll();

}
