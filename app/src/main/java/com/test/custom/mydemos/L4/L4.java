package com.test.custom.mydemos.L4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.test.custom.mydemos.BaseActivity;
import com.test.custom.mydemos.R;

public class L4 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        findViewById(R.id.tag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(L4.this,MirrorActivity.class));
            }
        });

    }

    @Override
    protected int setContentView() {
        return R.layout.activity_l4;
    }



}
