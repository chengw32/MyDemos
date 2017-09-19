package com.test.custom.mydemos.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;

import com.test.custom.mydemos.R;
import com.test.custom.mydemos.utils.DialogManager;
import com.test.custom.mydemos.utils.LogUtil;

/**
 * Author 陈国武
 * Time 2017/5/23.
 * Des:
 */

public abstract class BaseActivity extends FragmentActivity {

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
                showDialog();
            }
        });
    }


    private void showDialog(){
        String simpleName = getClass().getSimpleName();//类名
        String className = getIntent().getComponent().getClassName();
        int position = className.indexOf(".",23);
        int position2 = className.indexOf(".",24);
        //模块名字
        String molName = className.substring(position + 1, position2);
        //课程名字
        String path = molName +"/"+simpleName+".txt";
        LogUtil.e("simpleName    " + simpleName);
        LogUtil.e("className    " + className);
        LogUtil.e("path    " + path);
        DialogManager.getIns().showDialog(this,path);
    }

    protected abstract void setContentView();

}
