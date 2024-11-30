package top.soft.classoa.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Utils {

    /**
     * 对源数据生成MD5摘要
     * @param source 源数据
     * @return 摘要
     */
    public static String md5Digest(String source){
        return DigestUtils.md2Hex(source);
    }

    /**
     * 对源数据加盐混淆生成 MD5 摘要
     * @param source 源数据
     * @param salt 盐值
     * @return md5 摘要
     */
    public static String md5Digest(String source,Integer salt){
        char[] chars = source.toCharArray();
        for(int i = 0;i<chars.length;i++){
            chars[i] = (char)(salt + (int)chars[i]);
        }
        String target = new String(chars);
        return DigestUtils.md5Hex(target);
    }
}