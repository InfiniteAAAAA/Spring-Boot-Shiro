package com.infinite.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MD5Utils {
    private static final String SALT = "mrbird";

    private static final String ALGORITH_NAME = "md5";

    private static final int HASH_ITERATIONS = 2;

    public static String encrypt(String pswd) {
        String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
        return newPassword;
    }

    public static String encrypt(String username, String pswd) {
        String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(username + SALT),
                HASH_ITERATIONS).toHex();
        return newPassword;
    }
    public static void main(String[] args) {
        String key = "Kobe Bryant";
        byte[] keyByte = key.getBytes(StandardCharsets.UTF_8);
        System.out.println(Base64Utils.encodeToString(keyByte));

        System.out.println(MD5Utils.encrypt("Bryant", "123456"));
    }

}