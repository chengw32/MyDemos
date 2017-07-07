package com.test.custom.mydemos.L13;

import android.view.MotionEvent;
import android.view.View;

import com.test.custom.mydemos.BaseActivity;
import com.test.custom.mydemos.R;

public class L13 extends BaseActivity {


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_l13);

        View toucheTest = findViewById(R.id.toucheTest);
        toucheTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        toucheTest.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }


}
