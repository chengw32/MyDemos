package com.test.custom.mydemos.L4;

import android.os.Bundle;

import com.test.custom.mydemos.BaseActivity;
import com.test.custom.mydemos.R;

public class MirrorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(new MirrorView(this));

    }

    @Override
    protected int setContentView() {
        return R.layout.activity_mirror;
    }
}
