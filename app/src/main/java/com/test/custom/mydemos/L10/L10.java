package com.test.custom.mydemos.L10;

import android.os.Bundle;
import android.view.View;

import com.test.custom.mydemos.BaseActivity;
import com.test.custom.mydemos.R;

public class L10 extends BaseActivity {

    WaveView wv_test ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_l10);

        wv_test = (WaveView) findViewById(R.id.wv_test);
        wv_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wv_test.move();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wv_test.removeAnimator();
    }
}
