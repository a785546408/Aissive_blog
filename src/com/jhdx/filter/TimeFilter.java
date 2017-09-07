package com.jhdx.filter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFilter {

	public static String getDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = sdf.format(date);
		return time;
	}
}
