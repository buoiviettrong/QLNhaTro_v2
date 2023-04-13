package com.Nixagh.Learn.common.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertDate {
  public static String convert(Date date, String dateFormat) {
    try {
      DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
      DateFormat formatter1 = new SimpleDateFormat(dateFormat);
      return formatter1.format(formatter.parse(String.valueOf(date)));
    } catch (ParseException e) {
    }
    return "";
  }

  public static String convert(Date date, String from, String to) {
    try {
      DateFormat formatter = new SimpleDateFormat(from);
      DateFormat formatter1 = new SimpleDateFormat(to);
      return formatter1.format(formatter.parse(String.valueOf(date)));
    } catch (ParseException e) {
    }
    return "";
  }

  public static String convert(String date, String from, String to) {
    try {
      DateFormat formatter = new SimpleDateFormat(from);
      DateFormat formatter1 = new SimpleDateFormat(to);
      return formatter1.format(formatter.parse(date));
    } catch (ParseException e) {
    }
    return "";
  }
}
