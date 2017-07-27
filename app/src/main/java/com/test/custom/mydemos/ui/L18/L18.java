package com.test.custom.mydemos.ui.L18;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.test.custom.mydemos.R;
import com.test.custom.mydemos.base.BaseActivity;

public class L18 extends BaseActivity {

    private SplashView sv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentLayout = (FrameLayout) getWindow().getDecorView().findViewById(android.R.id.content);
        sv = new SplashView(this);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        sv.setLayoutParams(lp);
        contentLayout.addView(sv);

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_l18);
    }
}
