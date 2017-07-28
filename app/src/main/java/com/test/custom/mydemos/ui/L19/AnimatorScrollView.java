package com.test.custom.mydemos.ui.L19;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Author 陈国武
 * Time 2017/7/27.
 * Des:
 */

@RequiresApi(api = Build.VERSION_CODES.M)
public class AnimatorScrollView extends ScrollView{
    public AnimatorScrollView(Context context) {
        this(context,null);
    }

    public AnimatorScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private LinearLayout content ;
    Rect mRect = new Rect();
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        for (int i = 0; i < content.getChildCount(); i++) {
            AnimatorContnet childAt = (AnimatorContnet) content.getChildAt(i);
            childAt.getHitRect(mRect);
            boolean localVisibleRect = getLocalVisibleRect(mRect);
            if (localVisibleRect){
                childAt.startAni();
            }else {
                childAt.restoreAni();

            }
        }

    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        content = (LinearLayout) getChildAt(0);
    }

    @Override
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

}
