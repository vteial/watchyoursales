package io.ahaitech.harmoney.service;

public interface DataService {

	void load();

	void save();

	void reset();

	String exportToJson();

	String exportToCsv();

}
