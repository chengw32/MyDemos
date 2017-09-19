package com.test.custom.mydemos.utils;

import android.app.Activity;
import android.app.AlertDialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Author 陈国武
 * Time 2017/9/19.
 * Des:
 */

public class DialogManager {

    private static DialogManager ins ;
    public static DialogManager getIns(){
        if (ins == null)ins = new DialogManager();
        return  ins ;
    }

    public void showDialog(Activity context,String path){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(getAssetsTxt(context,path));
         builder.create().show();
    }



    private String getAssetsTxt(Activity context,String path){
        try {
            InputStream is = context.getAssets().open(path);
            InputStreamReader reader = new InputStreamReader(is,"gbk");
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuffer buffer = new StringBuffer();
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
                buffer.append("\n");
            }
            return buffer.toString();
        } catch (IOException e) {
            return "读取 Assets 下的文件出错 请查看Assets文件下的 "+path+" 路径下是否有对应文件 ";
        }
    }


}
