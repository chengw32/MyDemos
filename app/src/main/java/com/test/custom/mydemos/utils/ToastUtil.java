package com.test.custom.mydemos.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Author 陈国武
 * Time 2017/6/14.
 * Des:
 */

public class ToastUtil {

    private static Toast mToast;
    public static void show(Context context,String message){
        if (mToast == null) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(message);
        }
        mToast.show();
    }
}
