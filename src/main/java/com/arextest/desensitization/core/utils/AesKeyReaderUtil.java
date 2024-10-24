package com.arextest.desensitization.core.utils;

import java.util.Base64;

public class AesKeyReaderUtil {

  private static final String AES_KEY_FILE_PATH = "arex.desensitization.aesKey";
  public static byte[] aesKey = null;

  public static void validAesKeyFileExist() {
    aesKey = readAesKey(AES_KEY_FILE_PATH);
  }

  private static byte[] readAesKey(String keyFilePath) {
    String aesBase64 = System.getProperty(keyFilePath);

    if (aesBase64 == null || aesBase64.isEmpty()) {
      aesBase64 = System.getenv(keyFilePath);
    }

    if (aesBase64 == null || aesBase64.isEmpty()) {
      throw new RuntimeException("aes key not found");
    }

    byte[] keyBytes = Base64.getDecoder().decode(aesBase64);
    int length = keyBytes.length;
    if (length != 16 && length != 24 && length != 32) {
      throw new RuntimeException("aes key length error");
    }
    return keyBytes;
  }
}
