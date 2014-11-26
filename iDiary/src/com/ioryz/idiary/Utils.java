package com.ioryz.idiary;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("SimpleDateFormat") 
public class Utils {

	public static String getDateStrByYMD(int y, int m, int d, String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.set(y, m, d);
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(cal.getTime());
	}
	
	public static String getDateStrByDate(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
}
