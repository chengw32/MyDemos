package com.test.custom.mydemos.application;

import android.app.Application;

/**
 * Author 陈国武
 * Time 2017/7/24.
 * Des:
 */

public class MyApp extends Application {

    static {
        System.loadLibrary("native-lib");
    }
    private static MyApp mInstance ;

    @Override
    public void onCreate() {
        mInstance = this ;
    }

    public static MyApp getInstance(){
        return mInstance ;
    }

}
