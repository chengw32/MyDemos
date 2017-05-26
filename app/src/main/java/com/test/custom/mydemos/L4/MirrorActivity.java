package com.test.custom.mydemos.L4;

import android.os.Bundle;

import com.test.custom.mydemos.BaseActivity;
import com.test.custom.mydemos.R;

public class MirrorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_mirror);
    }
}
