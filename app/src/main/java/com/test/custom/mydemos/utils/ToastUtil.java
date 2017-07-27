package com.test.custom.mydemos.utils;

import android.widget.Toast;

import com.test.custom.mydemos.application.MyApp;

/**
 * Author 陈国武
 * Time 2017/6/14.
 * Des:
 */

public class ToastUtil {

    private static Toast mToast;
    public static void show(String message){
        if (mToast == null) {
            mToast = Toast.makeText(MyApp.getInstance(), message, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(message);
        }
        mToast.show();
    }
}
