package io.ahaitech.harmoney.web.controllers;

import io.ahaitech.harmoney.model.User;
import io.ahaitech.harmoney.service.InvalidCredentialException;
import io.ahaitech.harmoney.service.SessionService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/sessions")
@Slf4j
public class SessionServiceController extends AbstractController {

	@Resource
	SessionService sessionService;

	@PostConstruct
	public void init() {
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody User authenticate(@RequestBody final User user,
			final HttpSession session, final HttpServletRequest request) {
		User sessionUser = null;
		try {
			sessionUser = sessionService.login(user, session);
		} catch (InvalidCredentialException t) {
			// log.error("Unhandled error...", t);
		}

		return sessionUser;
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public @ResponseBody User logout(HttpSession session) {

		User sessionUser = (User) session
				.getAttribute(SessionService.SESSION_USER_KEY);
		sessionService.logout(sessionUser, session);

		return sessionUser;
	}
}
