package com.test.custom.mydemos.Ui.L4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.test.custom.mydemos.R;
import com.test.custom.mydemos.base.BaseActivity;

public class L4 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        TextView viewById = (TextView) findViewById(R.id.tag);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(L4.this,MirrorActivity.class));
            }
        });

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_l4);
    }



}
