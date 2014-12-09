package io.ahaitech.harmoney.web.controllers;

import io.ahaitech.harmoney.service.AutoNumberService;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//@Api(value = "autoNumbers", description = "AutoNumbers")
@Controller
@RequestMapping("/api/autoNumbers")
@Slf4j
public class AutoNumberController {

	@Resource
	AutoNumberService autoNumberService;

	@RequestMapping(value = "/{id}/nextNumber", method = RequestMethod.GET)
	public @ResponseBody long nextNumber(@PathVariable("id") final String id) {
		long val = 0;

		val = this.autoNumberService.getNextNumber(id);

		return val;
	}

}
