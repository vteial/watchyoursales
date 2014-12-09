package io.ahaitech.harmoney.web.controllers;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

@Controller
public class DefaultController {

	final Logger _log = LoggerFactory.getLogger(DefaultController.class);

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public @ResponseBody String ping() {
		return "Ping Pong!";
	}

	String deviceResponseTemplate = "Hello! Youre device is %s!";

	@RequestMapping("/whatIsMyDeviceType")
	public @ResponseBody String detectDevice(Device device) {
		String deviceType = "Unknown";

		if (device.isNormal()) {
			deviceType = "PC or Laptop or TV";
		} else if (device.isMobile()) {
			deviceType = "Mobile";
		} else if (device.isTablet()) {
			deviceType = "Tablet";
		}

		return String.format(this.deviceResponseTemplate, deviceType);
	}

	@Resource
	WebSocketMessageBrokerStats webSocketStats;

	@RequestMapping(value = "/_admin/sessions", method = RequestMethod.GET)
	public @ResponseBody WebSocketMessageBrokerStats sessions() {
		return webSocketStats;
	}

}
