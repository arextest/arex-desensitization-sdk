package com.arextest.extension.desensitization;

public class DefaultDataDesensitization implements DataDesensitization {
    @Override
    public String encrypt(String info) throws Exception {
        return info;
    }

    @Override
    public String decrypt(String encryptInfo) throws Exception {
        return encryptInfo;
    }
}
