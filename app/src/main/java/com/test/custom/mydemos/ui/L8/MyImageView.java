package com.test.custom.mydemos.ui.L8;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.test.custom.mydemos.utils.LogUtil;
import com.test.custom.mydemos.utils.ToastUtil;

/**
 * Author 陈国武
 * Time 2017/6/14.
 * Des:
 */

public class MyImageView extends ImageView {



    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private int xStart , xRadius;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();

        switch (event.getAction() ){
            case MotionEvent.ACTION_MOVE:
                ToastUtil.show("move--x-----"+x);
                 xRadius = xStart - x;
                LogUtil.e("xMove--  "+xRadius);
                setImageLevel(xRadius);
                break;
            case MotionEvent.ACTION_DOWN:
                xStart =  x;
                ToastUtil.show("down-x-----"+x);

                break;
            case MotionEvent.ACTION_UP:
                ToastUtil.show("up-x-----"+x);
                restoreView();
                break;
        }

        return true;
    }

    private void restoreView() {
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (xRadius > 0){
                    if (!(xRadius == 0)){
                        xRadius -= 10 ;
                        if (xRadius < 0)xRadius=0;
                        setImageLevel(xRadius);
                        restoreView();
                    }
                }else{
                    if (!(xRadius == 0)){
                        xRadius += 10 ;
                        if (xRadius > 0)xRadius=0;
                        setImageLevel(xRadius);
                        restoreView();
                    }
                }

            }
        },10);
    }


}
