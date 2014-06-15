package com.vy12021.framework.util.common;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by LIUYONG on 14-2-25.
 */
@Component
public class FormatUtils {

    public Integer[] convertStringArray(String[] stringArray) {
        Integer[] results = new Integer[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            results[i] = Integer.parseInt(stringArray[i]);
        }
        return results;
    }

    public String Md5(String plainText) {
        StringBuffer buf = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) i += 256;
                if (i < 16) buf.append("0");
                buf.append(Integer.toHexString(i));
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return buf.toString();
    }

}
