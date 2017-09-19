package com.test.custom.mydemos.NDK.L6;


public class MyJni {
    static {
        System.loadLibrary("native-lib");
    }

    public static native String getMyC();
}
