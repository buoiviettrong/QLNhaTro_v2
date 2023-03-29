package com.Nixagh.Learn.common.utilities;

import java.security.SecureRandom;
import java.util.Base64;

public class Token {
  static SecureRandom secureRandom = new SecureRandom();
  private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
  public static final String generateToken() {
    byte[] randomBytes = new byte[24];
    secureRandom.nextBytes(randomBytes);
    return base64Encoder.encodeToString(randomBytes);
  }
}
