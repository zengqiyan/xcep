package com.xiao.xbcp.util;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具
 *
 * @author 曾启彦
 * @date 2020-09-05
 * <p>
 * All rights Reserved, Designed www.xiao100.com
 */
public class MD5Util {

    public static String getMD5(String str) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return result;
    }

    /**
     * 获取上传文件的md5
     *
     * @param file
     * @return
     */
    public static String getMD5(MultipartFile file) {
        try {
            byte[] uploadBytes = file.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(uploadBytes);
            String hashString = new BigInteger(1, digest).toString(16);
            return hashString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
