package top.soft.bookonline.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author 86188
 * @description: TODO
 * @date 2024/10/19 16:39
 */

public class Md5Util {
    public static String crypt(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("不能为空串或长度为0的字符串加密");
        }
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] hash = md.digest();
            for (byte b : hash) {
                if ((0xff & b) < 0x10) {
                    hexString.append("0").append(Integer.toHexString((0xFF & b)));
                } else {
                    hexString.append(Integer.toHexString(0xFF & b));
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString.toString();
    }
}