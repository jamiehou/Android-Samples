package com.ioryz.downloaddemo;

import java.util.Locale;
import java.util.regex.Pattern;

public class StringUtil {
	
	private static Locale defloc = Locale.getDefault();
	@SuppressWarnings("unused")
	private static final boolean CASE_SENSITIVE = true;
	private static final boolean CASE_NON_SENSITIVE = false;

	/**
	 * Check if give string is a valid url
	 * 
	 * @param str	url string
	 * @return	true:valid url; false:invalid url
	 */
	public static boolean isUrlValid(String str) {
		return isUrlValid(str, CASE_NON_SENSITIVE);
	}
	
	public static boolean isUrlValid(String str, boolean isCaseSensitve) {
		if (!isCaseSensitve)
			str = str.toLowerCase(defloc);
		
		String regex = "^((https|http|ftp|rtsp|mms)?://)"
				+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp user @
				+ "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP format URL- 199.194.52.184
				+ "|" // allow IP and Domain
				+ "([0-9a-z_!~*'()-]+\\.)*" // Domain - www.
				+ "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 2nd level domain
				+ "[a-z]{2,6})" // first level domain- .com or .museum
				+ "(:[0-9]{1,4})?" // Port: 80
				+ "((/?)|" // a slash isn't required if there is no file name
				+ "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
		
		return Pattern.matches(regex, str);
	}
}
