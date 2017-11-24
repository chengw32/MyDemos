package com.test.custom.mydemos.utils;

import com.test.custom.mydemos.ErrorActivity;

/**
 * Author 陈国武
 * Time 2017/7/24.
 * Des:
 */

public class ClassUtils {

    public static Class getLocalClass (String cla){
        Class tt = null;
        try {
            tt = Class.forName(cla);
           return  tt ;
        } catch (ClassNotFoundException e) {
            ToastUtil.show("ClassNotFoundException-----ClassName:"+cla);
            LogUtil.e("ClassNotFoundException-----ClassName:"+cla);
            tt = ErrorActivity.class;
            return  tt ;
        }
    }

}
