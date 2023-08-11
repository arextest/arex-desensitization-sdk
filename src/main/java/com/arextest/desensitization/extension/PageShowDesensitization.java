package com.arextest.desensitization.extension;

/**
 * The extension interface is to encrypt the infos in frontPage.
 * To avoid the following behaviors:
 * 1. different encryption methods are used the same aliasName
 * 2. remove the using encryption method, which will cause the previous data can not be decrypted
 */
public interface PageShowDesensitization {

    /**
     * To get the alias of the encryption method, used for unique positioning in the system
     *
     * @return the encrypted contents
     */
    String getAliasName();

    /**
     * To encrypt the field of compared content
     * Note: The method can be reversible or irreversible
     *
     * @param info the value of the field of compared json
     * @return the encrypted value
     */
    String encrypt(String info);

    /**
     * To decrypt the field of compared content
     * Note: This method can be empty, in which case the encrypted fields displayed on the page cannot be restored
     *
     * @param encryptInfo the encrypted value of field
     * @return the decrypted value
     */
    String decrypt(String encryptInfo);

}
