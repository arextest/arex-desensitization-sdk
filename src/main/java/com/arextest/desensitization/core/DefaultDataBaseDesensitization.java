package com.arextest.desensitization.core;

import com.arextest.desensitization.extension.DataBaseDesensitization;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class DefaultDataBaseDesensitization implements DataBaseDesensitization {

    @Override
    public String encrypt(String s) {
//        byte[] contentBytes = s.getBytes();
//        try {
//            byte[] encryptedContent = encryptAES(contentBytes, null);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return Base64.getEncoder().encodeToString(contentBytes);
        return "";
    }

    @Override
    public String decrypt(String s) {
//        byte[] base64DecodeContent = Base64.getDecoder().decode(s);
//        try {
//            byte[] decryptedContent = decryptAES(base64DecodeContent, null);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return new String(base64DecodeContent);
        return "";
    }


//    /**
//     * encrypt
//     *
//     * @throws Exception
//     */
//    private byte[] encryptAES(byte[] data, byte[] key) throws Exception {
//        SecretKey secretKey = new SecretKeySpec(key, "AES");
//        Cipher cipher = Cipher.getInstance("AES");
//        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//        return cipher.doFinal(data);
//    }
//
//    /**
//     * decrypt
//     */
//    private byte[] decryptAES(byte[] data, byte[] key) throws Exception {
//        SecretKey secretKey = new SecretKeySpec(key, "AES");
//        Cipher cipher = Cipher.getInstance("AES");
//        cipher.init(Cipher.DECRYPT_MODE, secretKey);
//        return cipher.doFinal(data);
//    }
}
