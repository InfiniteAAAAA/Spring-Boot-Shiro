package com.infinite.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SHA256 {

    private static final Logger logger = LoggerFactory.getLogger(SHA256.class);

    /**
     * 对字符串使用SHA-256加密
     * @param strSrc  要加密的字符串
     * @return
     */
    public static String shaEncrypt(String strSrc) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-256");

            md.update(bt);
            strDes = bytes2Hex(md.digest());//to HexString
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.toString());
            return e.toString();
        }

        return strDes;
    }

    public static String bytes2Hex(byte[] bts) {
        StringBuilder des = new StringBuilder();
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des.append("0");
            }
            des.append(tmp);
        }
        return des.toString().toUpperCase();
    }
}
