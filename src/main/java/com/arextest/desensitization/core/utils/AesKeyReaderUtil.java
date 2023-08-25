package com.arextest.desensitization.core.utils;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class AesKeyReaderUtil {
    public static byte[] aesKey = null;

//    static {
//        writeAesKey("./extension/aesKey.bin");
//        aesKey = readAesKey("./extension/aesKey.bin");
//        System.out.println("AesKeyReaderUtil init");
//    }

    public static void validAesKeyFileExist() {
//        aesKey = readAesKey("./extension/aesKey.bin");
        writeAesKey("./extension/aesKey.bin");
        aesKey = readAesKey("./extension/aesKey.bin");
        System.out.println("AesKeyReaderUtil init");
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
        return keyBytes;
    }

    private static void writeAesKey(String keyFilePath) {
        try {
            File extensionFolder = new File("./extension");
            extensionFolder.mkdirs();
            // 生成AES密钥
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);  // 可以根据需要调整密钥长度
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            // 将密钥写入文件
            FileOutputStream fos = new FileOutputStream(keyFilePath);
            fos.write(keyBytes);
            fos.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
