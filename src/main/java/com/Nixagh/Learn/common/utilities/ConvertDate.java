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
}
