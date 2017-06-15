package com.test.custom.mydemos.L8;

import android.os.Bundle;

import com.test.custom.mydemos.BaseActivity;
import com.test.custom.mydemos.R;

public class L8 extends BaseActivity {


    private int level = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final MyImageView iv_drawable = (MyImageView) findViewById(R.id.iv_drawable);
        MyDrawable drawable = new MyDrawable(getResources().getDrawable(R.mipmap.xiaowu),getResources().getDrawable(R.mipmap.xiaowu_black));
        iv_drawable.setImageDrawable(drawable);

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_l8);
    }

}
