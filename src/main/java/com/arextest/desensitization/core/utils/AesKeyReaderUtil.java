package com.arextest.desensitization.core.utils;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class AesKeyReaderUtil {
    public static byte[] aesKey = null;

    static {
        writeAesKey("./aesKey.bin");
        aesKey = readAesKey("./aesKey.bin");
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
        }
        return keyBytes;
    }

    private static void writeAesKey(String keyFilePath) {
        try {
            // 生成AES密钥
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);  // 可以根据需要调整密钥长度
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            // 将密钥写入文件
            FileOutputStream fos = new FileOutputStream(keyFilePath);
            fos.write(keyBytes);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
//        String message = "Hello, world! 小米";
//        String keyFilePath = "./aeskey.bin";
//        writeAesKey(keyFilePath);
//        byte[] bytes = readAesKey(keyFilePath);
//        DefaultDataDesensitization defaultDataDesensitization = new DefaultDataDesensitization();
//        String encrypt = defaultDataDesensitization.encrypt(message, bytes);
//        String decrypt = defaultDataDesensitization.decrypt(encrypt, bytes);
//        System.out.println(decrypt);

    }

}
