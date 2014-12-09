package io.vteial.watchyoursales.service.impl;

import io.vteial.watchyoursales.service.AutoNumberService;

import java.text.DecimalFormat;

import javax.annotation.Resource;

public abstract class AbstractService {

	@Resource(name = "rateFormatterAndParser")
	protected DecimalFormat rateFormatter;

	@Resource(name = "amountFormatterAndParser")
	protected DecimalFormat amountFormatter;

	@Resource
	protected AutoNumberService autoNumberService;

}
