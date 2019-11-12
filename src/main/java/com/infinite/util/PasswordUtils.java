package com.infinite.util;

import org.springframework.util.DigestUtils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class PasswordUtils {
    public static final String REGEX = PasswordEnum.REGEX.getMessage();
    private PasswordUtils() {}

    /**
     * 生成随即密码
     *
     * @param passwordLen 生成的密码的总长度
     * @return 密码的字符串
     */
    public static String genRandomNum(int passwordLen) throws NoSuchAlgorithmException {
        //35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 75;
        int i;  //生成的随机数
        int count = 0; //生成的密码的长度
        char[] str = {
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                '~', '!', '@', '#', '$', '%', '^', '&'
        };
        StringBuilder pwd = new StringBuilder();
        Random random = SecureRandom.getInstanceStrong();
        while (count < passwordLen) {
            //生成随机数，取绝对值，防止生成负数，
            i = Math.abs(random.nextInt(maxNum));  //生成的数最大为36-1
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        if (pwd.toString().matches(REGEX)) {
            return pwd.toString();
        }
        return genRandomNum(8);
    }

    public static Map<String, String> encrypt() throws NoSuchAlgorithmException {
        Map<String, String> passwordMap = new HashMap<>();
        String password = PasswordUtils.genRandomNum(8);
        String md5Password = password;
        String pwdSalt = null;
        String sha256Password = null;
        for (int j = 0; j < 666; j++) {
            md5Password = DigestUtils.md5DigestAsHex(md5Password.getBytes());
        }
        UUID uuid = UUID.randomUUID();
        pwdSalt = uuid.toString().replace("-","").toUpperCase();
        sha256Password = EcsEncryptUtil.encrypt(md5Password, pwdSalt, 1000);
        passwordMap.put("password",password);
        passwordMap.put("pwdSalt",pwdSalt);
        passwordMap.put("sha256Password",sha256Password);
        passwordMap.put("md5Password",md5Password);
        return passwordMap;
    }
}