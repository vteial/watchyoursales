package io.vteial.watchyoursales.service.impl;

import io.vteial.watchyoursales.service.DataService;

import java.text.DecimalFormat;

import javax.annotation.Resource;

public abstract class AbstractDataService extends AbstractService implements
		DataService {

	@Override
	public void load() {
	}

	@Override
	public void save() {
	}

	@Override
	public void reset() {
	}

	@Override
	public String exportToJson() {
		return null;
	}

	@Override
	public String exportToCsv() {
		return null;
	}

}
