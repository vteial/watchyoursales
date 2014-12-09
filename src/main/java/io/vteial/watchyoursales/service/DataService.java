package io.vteial.watchyoursales.service;

public interface DataService {

	void load();

	void save();

	void reset();

	String exportToJson();

	String exportToCsv();

}
