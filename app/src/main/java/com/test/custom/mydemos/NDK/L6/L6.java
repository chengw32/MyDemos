package com.test.custom.mydemos.NDK.L6;

import android.widget.TextView;

import com.test.custom.mydemos.R;
import com.test.custom.mydemos.base.BaseActivity;

import static com.test.custom.mydemos.NDK.L6.MyJni.getMyC;

public class L6 extends BaseActivity {



    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_l15);
        TextView jni = (TextView) findViewById(R.id.jni);
        jni.setText(getMyC());
    }
}
