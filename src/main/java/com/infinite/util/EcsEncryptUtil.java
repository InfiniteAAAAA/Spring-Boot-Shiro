package com.infinite.util;


public class EcsEncryptUtil {

    /**
     * @param unEncryStr 待加密的明文
     * @param sale       针对明文生成的唯一的标识
     * @param count      SHA循环散列次数
     * @return 加密后的密文     为一个64位的字串
     */
    public static String encrypt(String unEncryStr, String sale, int count) {

        StringBuffer temp = new StringBuffer();
        //将要加密的明文unEncryStr字符左移2位
        temp.append(unEncryStr.substring(2, unEncryStr.length())).append(unEncryStr.substring(0, 2));
        unEncryStr = temp.toString();
        temp.setLength(0);//清空
        //将左移后的明文与salt分段拼接
        temp.append(unEncryStr.substring(0, 2))
                .append(sale.substring(2, sale.length()))
                .append(unEncryStr.substring(2, unEncryStr.length())).append(sale.substring(0, 2));
        String strTemp = temp.toString();
        //循环散列
        for (int i = 0; i <= count; i++) {
            strTemp = SHA256.shaEncrypt(strTemp);
        }
        //将散列后的结果左移2位
        temp.setLength(0);
        temp.append(strTemp.substring(2, strTemp.length())).append(strTemp.substring(0, 2));
        return temp.toString();

    }
}