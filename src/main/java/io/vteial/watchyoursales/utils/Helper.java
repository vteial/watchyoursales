package io.vteial.watchyoursales.utils;

import io.vteial.watchyoursales.model.User;

import java.text.DecimalFormat;

import org.slf4j.Logger;

public class Helper extends DateTimeHelper {

	public static String WEB_REQUEST_ST_KEY = "WEB_REQUEST_ST";

	public static String WEB_REQUEST_ET_KEY = "WEB_REQUEST_ET";

	public static String WEB_REQUEST_ID_KEY = "WEB_REQUEST_ID";

	public static String WEB_SESSION_ID_KEY = "WEB_SESSION_ID";

	public static String WEB_SESSION_USER_ID_KEY = "WEB_SESSION_USER_ID";

	public static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (Throwable t) {
		}
	}

	public static void sleep(long ms, Logger log, User sessionUser) {
		try {
			log.info("---------Sleep started  for {}", sessionUser.getId());
			Thread.sleep(ms);
			log.info("---------Sleep finished for {}", sessionUser.getId());
		} catch (Throwable t) {
		}
	}

	public static double format(DecimalFormat formatter, double value) {

		try {
			String fvalue = formatter.format(value);
			value = formatter.parse(fvalue).doubleValue();
		} catch (Throwable t) {
		}

		return value;
	}

}
