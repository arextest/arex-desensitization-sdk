package com.arextest.desensitization.core;

import com.arextest.desensitization.core.utils.AesKeyReaderUtil;
import com.arextest.extension.desensitization.DataDesensitization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AesDataDesensitization implements DataDesensitization {

    private static Logger LOGGER = LoggerFactory.getLogger(AesDataDesensitization.class);

    static {
        LOGGER.info("AesDataDesensitization init and validAesKeyFileExist");
        try {
            AesKeyReaderUtil.validAesKeyFileExist();
        } catch (Exception e) {
            LOGGER.error("AesDataDesensitization init error", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public String encrypt(String s) throws Exception {
        if (AesKeyReaderUtil.aesKey == null) {
            return s;
        }
        byte[] contentBytes = s.getBytes();
        byte[] encryptedContent = encryptAES(contentBytes, AesKeyReaderUtil.aesKey);
        return Base64.getEncoder().encodeToString(encryptedContent);
//        return reverseString(s);
    }

    @Override
    public String decrypt(String s) throws Exception {
        if (AesKeyReaderUtil.aesKey == null) {
            return s;
        }
        byte[] base64DecodeContent = Base64.getDecoder().decode(s);
        byte[] decryptedContent = decryptAES(base64DecodeContent, AesKeyReaderUtil.aesKey);
        return new String(decryptedContent);
//        return reverseString(s);
    }

    private String reverseString(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        return new StringBuffer(s).reverse().toString();
    }

    /**
     * encrypt
     *
     * @throws Exception
     */
    private byte[] encryptAES(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    /**
     * decrypt
     */
    private byte[] decryptAES(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }
}
