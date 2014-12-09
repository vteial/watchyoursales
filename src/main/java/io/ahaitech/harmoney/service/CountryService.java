package io.ahaitech.harmoney.service;

import io.ahaitech.harmoney.model.Country;

import java.util.List;

public interface CountryService extends DataService {

	List<Country> findAll();

}
