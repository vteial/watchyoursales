package io.vteial.watchyoursales.web.controllers;

import io.vteial.watchyoursales.service.CustomerService;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/customers")
@Slf4j
public class CustomerController extends AbstractController {

	@Resource
	CustomerService customerService;

}
