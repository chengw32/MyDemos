package com.test.custom.mydemos.NDK.L12;

import android.os.Bundle;
import android.view.View;

import com.test.custom.mydemos.R;
import com.test.custom.mydemos.base.BaseActivity;

public class L12 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void bzip(View v){
        Zip.zip("","","");
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_l12);
    }
}
