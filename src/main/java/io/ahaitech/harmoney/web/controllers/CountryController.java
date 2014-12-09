package io.ahaitech.harmoney.web.controllers;

import io.ahaitech.harmoney.model.Country;
import io.ahaitech.harmoney.service.CountryService;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/countrys")
public class CountryController extends AbstractController {

	final Logger _log = LoggerFactory.getLogger(CountryController.class);

	@Resource
	CountryService countryService;

	@RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
	public @ResponseBody List<Country> findAll(HttpSession session) {
		List<Country> countrys = this.countryService.findAll();

		return countrys;
	}
}
