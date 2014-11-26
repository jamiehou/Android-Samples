package com.ioryz.idiary;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("SimpleDateFormat") 
public class DateUtil {
	
	private static final String DATE_FORMAT_DEFAULT = "YYYY-MM-DD";

	public static String getDateStr(int y, int m, int d, String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.set(y, m, d);
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(cal.getTime());
	}
	
	public static String getDateStr(int y, int m, int d) {
		return getDateStr(y, m, d, DATE_FORMAT_DEFAULT);
	}
	
	public static String getDateStr(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	public static String getDateStr(Date date) {
		return getDateStr(date, DATE_FORMAT_DEFAULT);
	}
}
