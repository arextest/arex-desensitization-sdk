package com.arextest.desensitization.extension;

/**
 * The extension interface is to encrypt the infos in DataBase.
 * To avoid the following behaviors:
 * 1. remove the using encryption method, which will cause the previous data can not be decrypted
 */
public interface DataBaseDesensitization {

    /**
     * To encrypt the contents of the database
     * Note: The encryption method must be reversible encryption for playback
     *
     * @param info the contents of the database
     * @return the encrypted contents
     */
    String encrypt(String info);

    /**
     * To decrypt the contents of the database
     * Note: The decrypted information is for playback and only circulates inside arex
     * if the information is not decrypted, it will cause the error of the replay
     *
     * @param encryptInfo the encrypted contents
     * @return the decrypted contents
     */
    String decrypt(String encryptInfo);
}
