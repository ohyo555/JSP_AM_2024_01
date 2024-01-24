package com.KoreaIT.java.Jsp_AM.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

	public static String getNowDate_TimeStr(LocalDateTime localDateTime) {

		String formatedNow = localDateTime.format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss"));
		
		return formatedNow;
	}
}
