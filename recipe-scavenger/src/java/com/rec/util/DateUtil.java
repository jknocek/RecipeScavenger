package com.rec.util;

import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Days;

public class DateUtil {

	public static boolean sameDay(Date firstDate, Date secondDate) {
		int days = Days.daysBetween(new DateTime(firstDate), new DateTime(secondDate)).getDays();
		
		if(days == 0) {
			return true;
		}
		
		return false;
	}
	
	public static int getDaysBetween(Date firstDate, Date secondDate) {
		int days = Days.daysBetween(new DateTime(firstDate), new DateTime(secondDate)).getDays();
		
		return days;	
	}
}
