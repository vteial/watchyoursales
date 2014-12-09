package io.ahaitech.harmoney.web.interceptors;

import io.ahaitech.harmoney.model.User;
import io.ahaitech.harmoney.service.SessionService;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.common.collect.Maps;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

	final Logger _log = LoggerFactory.getLogger(SecurityInterceptor.class);

	Map<String, Boolean> publicPaths = Maps.newHashMap();

	@PostConstruct
	public void init() {
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (request.getRequestURI().contains(".")) {
			return super.preHandle(request, response, handler);
		}

		String ctxPath = request.getContextPath();
		String path = request.getRequestURI().substring(ctxPath.length());
		if (this.publicPaths.containsKey(path)) {
			return super.preHandle(request, response, handler);
		}

//		HttpSession session = request.getSession(true);
//		User user = (User) session
//				.getAttribute(SessionService.SESSION_USER_KEY);
//		if (user == null) {
//			response.sendError(401);
//			return false;
//		}

		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
