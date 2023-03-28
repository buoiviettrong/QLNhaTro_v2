package com.Nixagh.Learn.common.utilities;

public class Token {
  public static String createToken(String username, String password, String Time) {
    // generation token
    String str = "";

    // return
    return username + ":" + password + ":" + Time;
  }
}
