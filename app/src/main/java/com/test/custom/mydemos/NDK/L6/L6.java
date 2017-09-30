package com.test.custom.mydemos.NDK.L6;

import android.widget.TextView;

import com.test.custom.mydemos.R;
import com.test.custom.mydemos.base.BaseActivity;
import com.test.custom.mydemos.utils.LogUtil;

public class L6 extends BaseActivity {

    private String t = "test";

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_l15);
        TextView jni = (TextView) findViewById(R.id.jni);
        testC();
        getMethodFromJava();
        jni.setText("JNI获取JAVA的成员变量的值(test)并拼接字符串(wtf)最后再返回JAVA层: "+t);
        TextView jni2 = (TextView) findViewById(R.id.jni2);
        jni2.setText("JNI访问JAVA层普通方法返回的值5，最后返回到JAVA层："+String.valueOf(getMethodFromJava()));
        TextView jni3 = (TextView) findViewById(R.id.jni3);
        jni3.setText("JNI访问java静态方法获取该静态方法返回的值15，再在JNI层加10，最后返回JAVA层："+getStaticMethodFromJava());
        TextView jni4 = (TextView) findViewById(R.id.jni4);
        jni4.setText("JNI访问java的Date类的构造方法并调用Date类的getTime方法获值并返回JAVA层："+getClassFromJava());
        TextView jni5 = (TextView) findViewById(R.id.jni5);
        jni5.setText("JNI访问java的字符串并返回JAVA层："+getStringFromJava("程序猿"));

        getArrayFromJava(xxx);
        for (int i = 0; i < xxx.length; i++) {
            LogUtil.e(""+xxx[i]);
        }
    }

    //native 方法只是作为 jni 与 c 语言沟通的一个 触发/桥接 函数
    public  native String testC();
    public native int  getMethodFromJava();
    public native int  getStaticMethodFromJava();
    public native long  getClassFromJava();
    public native String  getStringFromJava(String tt);

    public int[] xxx= {5,55,3,9,46};
    public native void   getArrayFromJava(int[] tt);



    public int getNumFromJava(int num){
        return 5 ;
    }
    public static int getStaticNumFromJava(int num){
        return 15 +num ;
    }


}
