package com.xiayk.utils;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

    public static String firstLetterToLowerCase(String str){
        if (!StringUtils.isEmpty(str)){
            char[] chars = str.toCharArray();
            chars[0] = (char) (chars[0] >= 65 && chars[0] <= 90 ? chars[0] + 32 : chars[0]);
            return String.valueOf(chars);
        }
        return null;
    }
}
