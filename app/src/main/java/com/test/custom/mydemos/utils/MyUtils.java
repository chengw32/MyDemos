package com.test.custom.mydemos.utils;

/**
 * Author 陈国武
 * Time 2017/8/8.
 * Des:
 */

public class MyUtils {

    /**
     * Des：拼音首字母大写
     * Author：陈国武
     * Time：2017/8/8 9:31
     */
    
    public static String upFirstLetter(String str){

            char[] ch = str.toCharArray();
            if(ch[0] >= 'a' && ch[0] <= 'z'){
                ch[0] = (char)(ch[0] - 32);
            }
            return new String(ch);
    }

    public static String appendStr(Object... str){
        StringBuffer sb = new StringBuffer();
        for (Object item:str){
            sb.append(item);
            sb.append("\n");
        }
        return sb.toString();
    }

}
