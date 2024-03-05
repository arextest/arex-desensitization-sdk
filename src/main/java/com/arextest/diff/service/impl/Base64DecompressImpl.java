package com.arextest.diff.service.impl;

import com.arextest.diff.service.DecompressService;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Base64DecompressImpl implements DecompressService {

  private static Logger log = LoggerFactory.getLogger(Base64DecompressImpl.class.getName());

  private static final Pattern BASE64_PATTERN = Pattern.compile(
      "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$");

  public Base64DecompressImpl() {
  }

  public String getAliasName() {
    return "Base64";
  }

  public String decompress(String str, String args) {
    if (str == null || str.isEmpty()) {
      return null;
    }

    if (!isBase64(str)) {
      return str;
    }

    try {
      return this.unCompress(str);
    } catch (Exception e) {
      log.warn("Base64DeCompress decompress error, message: {}", str, e);
      return str;
    }
  }

  public String unCompress(String value) throws Exception {
    byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
    return new String(Base64.getDecoder().decode(bytes), StandardCharsets.UTF_8);
  }

  public static boolean isBase64(String str) {
    if (str == null || str.isEmpty()) {
      return false;
    }
    return BASE64_PATTERN.matcher(str).matches();
  }

}