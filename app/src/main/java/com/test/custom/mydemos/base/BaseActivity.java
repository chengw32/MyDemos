package com.test.custom.mydemos.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;

import com.test.custom.mydemos.R;
import com.test.custom.mydemos.utils.LogUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Author 陈国武
 * Time 2017/5/23.
 * Des:
 */

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView();
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.base_title_layout);
        findViewById(R.id.title_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.tv_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHintDialog();
            }
        });
    }

    private AlertDialog dl ;
    private void showHintDialog(){
        if (null == dl){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getAssetsTxt(getClass().getSimpleName()));
        dl = builder.create();
        }
        dl.show();
    }

    private String getAssetsTxt(String fileName){
        String assetsPath = "";
        try {
            String shortClassName = getIntent().getComponent().getShortClassName();
            LogUtil.e("BaseActivity-------shortClassName--  "+shortClassName);
            int i = shortClassName.indexOf(".", 1);
            assetsPath = shortClassName.substring(1,i)+"/"+fileName+".txt" ;
            LogUtil.e("BaseActivity-------assetsPath--  "+assetsPath);
            InputStream is = getAssets().open(assetsPath);
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
            return "读取 Assets 下的文件出错 请查看Assets文件下的 "+assetsPath+" 路径下是否有文件 "+fileName+".txt";
        }
    }

    protected abstract void setContentView();

}
