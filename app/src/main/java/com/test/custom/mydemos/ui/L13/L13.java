package com.test.custom.mydemos.Ui.L13;

import android.view.MotionEvent;
import android.view.View;

import com.test.custom.mydemos.base.BaseActivity;
import com.test.custom.mydemos.R;
import com.test.custom.mydemos.utils.LogUtil;

public class L13 extends BaseActivity {


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_l13);


        View toucheTest = findViewById(R.id.toucheTest);
        toucheTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e("setOnClickListener");
            }
        });
        toucheTest.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtil.e("setOnTouchListener");
                return false;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }


}
