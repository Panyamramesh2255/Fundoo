package com.java8.util;

import java.text.ParseException;
import java.util.Date;

public class DateFormat {
	public static void main(String[] args) throws ParseException {

		Date date = new Date();
		System.out.println("current date is " + date);
		String date_to_string = java.text.DateFormat.getInstance().format(date);
		System.out.println("formatted date is.. " + date_to_string);
		Date d = java.text.DateFormat.getDateInstance().parse("31 Mar, 2015");
		System.out.println("current date in user format is " + d);

	}

}
