package com.arextest.desensitization.core;

import com.arextest.desensitization.core.utils.AesKeyReaderUtil;
import com.arextest.desensitization.extension.DataDesensitization;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class DefaultDataDesensitization implements DataDesensitization {
    @Override
    public String encrypt(String s) throws Exception {
        if (AesKeyReaderUtil.aesKey == null) {
            return s;
        }
        byte[] contentBytes = s.getBytes();
        byte[] encryptedContent = encryptAES(contentBytes, AesKeyReaderUtil.aesKey);
        return Base64.getEncoder().encodeToString(encryptedContent);
    }

    @Override
    public String decrypt(String s) throws Exception {
        if (AesKeyReaderUtil.aesKey == null) {
            return s;
        }
        byte[] base64DecodeContent = Base64.getDecoder().decode(s);
        byte[] decryptedContent = decryptAES(base64DecodeContent, AesKeyReaderUtil.aesKey);
        return new String(decryptedContent);
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
