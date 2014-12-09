package io.ahaitech.harmoney.utils;

import java.util.Date;

import org.joda.time.DateTime;

public class DateTimeHelper {

	public static DateTime createStartOfTheDay() {
		DateTime dt = new DateTime();
		dt = new DateTime(dt.getYear(), dt.getMonthOfYear(),
				dt.getDayOfMonth(), 0, 0, 0);
		return dt;
	}

	public static DateTime createEndOfTheDay() {
		DateTime dt = new DateTime();
		dt = new DateTime(dt.getYear(), dt.getMonthOfYear(),
				dt.getDayOfMonth(), 23, 59, 59);
		return dt;
	}

	public static DateTime createStartOfTheDay(Date date) {
		DateTime dt = new DateTime(date);
		dt = new DateTime(dt.getYear(), dt.getMonthOfYear(),
				dt.getDayOfMonth(), 0, 0, 0);
		return dt;
	}

	public static DateTime createEndOfTheDay(Date date) {
		DateTime dt = new DateTime(date);
		dt = new DateTime(dt.getYear(), dt.getMonthOfYear(),
				dt.getDayOfMonth(), 23, 59, 59);
		return dt;
	}

	public static DateTime createWithTimeOffFromNow() {
		DateTime dt = new DateTime();
		dt = new DateTime(dt.getYear(), dt.getMonthOfYear(),
				dt.getDayOfMonth(), 0, 0, 0);
		return dt;
	}

	public static DateTime createWithTimeOffFromDate(Date date) {
		DateTime dt = new DateTime(date);
		dt = new DateTime(dt.getYear(), dt.getMonthOfYear(),
				dt.getDayOfMonth(), 0, 0, 0);
		return dt;
	}
}
