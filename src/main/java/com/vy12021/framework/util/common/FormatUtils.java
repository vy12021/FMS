package com.vy12021.framework.util.common;

import org.springframework.stereotype.Component;

/**
 * Created by LIUYONG on 14-2-25.
 */
@Component
public class FormatUtils {

    public Integer[] convertStringArray(String[] stringArray) {
        Integer[] results = new Integer[stringArray.length];
        for(int i=0; i<stringArray.length; i++) {
            results[i] = Integer.parseInt(stringArray[i]);
        }
        return results;
    }

}
