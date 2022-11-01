package com.flightapp.common;

import java.util.Date;
import java.util.Calendar;

public class Utils {

	public static Date addHoursToDate(Date date, int hours) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.HOUR_OF_DAY, hours);
	    return calendar.getTime();
	}
	
}
