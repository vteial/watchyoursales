package io.vteial.watchyoursales.web.interceptors;

import io.vteial.watchyoursales.model.User;
import io.vteial.watchyoursales.service.LockService;
import io.vteial.watchyoursales.service.SessionService;
import io.vteial.watchyoursales.utils.Helper;

import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.MDC;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.common.collect.Maps;

@Slf4j
public class TransactionInterceptor extends HandlerInterceptorAdapter {

	Map<String, Boolean> excludedPaths = Maps.newHashMap();

	@Resource
	LockService lockservice;

	@PostConstruct
	public void init() {
		excludedPaths.put("/sessionService/authenticate", true);
		excludedPaths.put("/sessionService/resetPassword", true);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String reqMethod = request.getMethod();
		if (reqMethod.equals(HttpMethod.POST.toString())
				|| reqMethod.equals(HttpMethod.PUT.toString())
				|| reqMethod.equals(HttpMethod.DELETE.toString())) {

			String ctxPath = request.getContextPath();
			String reqPath = request.getRequestURI()
					.substring(ctxPath.length());

			Long now = new Long(System.currentTimeMillis());
			request.setAttribute(Helper.WEB_REQUEST_ST_KEY, now);

			MDC.put(Helper.WEB_REQUEST_ID_KEY, UUID.randomUUID().toString());
			HttpSession session = request.getSession();
			MDC.put(Helper.WEB_SESSION_ID_KEY, session.getId());
			User sessionUser = (User) session
					.getAttribute(SessionService.SESSION_USER_KEY);
			if (sessionUser != null) {
				MDC.put(Helper.WEB_SESSION_USER_ID_KEY, sessionUser.getId());
			}
			log.info("{} started...", reqPath);

			if (!reqPath.contains("tranReceiptTellerTransfers")) {
				if (!this.excludedPaths.containsKey(reqPath)) {
					this.lockservice.lock();
				}
			}
		}

		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		String reqMethod = request.getMethod();
		if (reqMethod.equals(HttpMethod.POST.toString())
				|| reqMethod.equals(HttpMethod.PUT.toString())
				|| reqMethod.equals(HttpMethod.DELETE.toString())) {
			String ctxPath = request.getContextPath();
			String reqPath = request.getRequestURI()
					.substring(ctxPath.length());
			log.info("{} finished...", reqPath);

			Long et = new Long(System.currentTimeMillis());
			request.setAttribute(Helper.WEB_REQUEST_ET_KEY, et);
			Long st = (Long) request.getAttribute(Helper.WEB_REQUEST_ST_KEY);

			long rt = et.longValue() - st.longValue();
			log.info("Runtime : {}", rt);
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		String reqMethod = request.getMethod();
		if (reqMethod.equals(HttpMethod.POST.toString())
				|| reqMethod.equals(HttpMethod.PUT.toString())
				|| reqMethod.equals(HttpMethod.DELETE.toString())) {
			log.info("after completion started...");

			String ctxPath = request.getContextPath();
			String reqPath = request.getRequestURI()
					.substring(ctxPath.length());

			if (!reqPath.contains("tranReceiptTellerTransfers")) {
				if (!this.excludedPaths.containsKey(reqPath)) {
					this.lockservice.unlock();
				}
			}
			log.info("after completion finished...");

			MDC.remove(Helper.WEB_REQUEST_ID_KEY);
			MDC.remove(Helper.WEB_SESSION_ID_KEY);
			MDC.remove(Helper.WEB_SESSION_USER_ID_KEY);
		}
	}
}
