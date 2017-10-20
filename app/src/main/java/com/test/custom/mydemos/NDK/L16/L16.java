package com.test.custom.mydemos.NDK.L16;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.test.custom.mydemos.R;
import com.test.custom.mydemos.base.BaseActivity;
import com.test.custom.mydemos.utils.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class L16 extends BaseActivity {

    public native int getSoPlugIntValue(int a,int b);
    public native String getSoPlugStrValue(String str1,String str2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File dir = this.getDir("jniLibs", Activity.MODE_PRIVATE);
        File distFile = new File(dir.getAbsolutePath() + File.separator + "sotest.so");

        LogUtil.e("distFile  "+distFile.getAbsolutePath());
//        String path = Environment.getRootDirectory().toString();
//        copyFileFromAssets(this, "sotest.so", distFile.getAbsolutePath());
        String dataDir = "/data/user/0/com.test.custom.mydemos/lib/sotest.so";
        copyFileFromAssets(this, "sotest.so", dataDir);
        LogUtil.e("dataDir  "+dataDir);
        TextView viewById = (TextView) findViewById(R.id.test);
        viewById.setText("拼接字符串 wt f :  "+getSoPlugStrValue("wt","f")+"\n"+"从 so 获取的 5+9 的值 : "+getSoPlugIntValue(5,9));

    }


    public static boolean copyFileFromAssets(Context context, String fileName, String path) {
        boolean copyIsFinish = false;
        try {
            InputStream is = context.getAssets().open(fileName);
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();
            copyIsFinish = true;
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.e("IOException  " + e.getMessage());
        }
        return copyIsFinish;
}

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main2);
    }
}
