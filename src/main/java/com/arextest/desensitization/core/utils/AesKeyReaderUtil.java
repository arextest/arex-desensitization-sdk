package com.arextest.desensitization.core.utils;

import java.io.File;
import java.io.FileInputStream;

public class AesKeyReaderUtil {

    private static final String AES_KEY_FILE_PATH = "./extension/aesKey.bin";
    public static byte[] aesKey = null;

    public static void validAesKeyFileExist() {
        aesKey = readAesKey(AES_KEY_FILE_PATH);
    }

    private static byte[] readAesKey(String keyFilePath) {
        byte[] keyBytes = null;

        File file = new File(keyFilePath);
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(keyFilePath);
                keyBytes = new byte[(int) fis.available()];
                fis.read(keyBytes);
                fis.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("aes key file not exist");
        }

        int length = keyBytes.length;
        if (length != 16 && length != 24 && length != 32) {
            throw new RuntimeException("aes key file length error");
        }
        return keyBytes;
    }
}
