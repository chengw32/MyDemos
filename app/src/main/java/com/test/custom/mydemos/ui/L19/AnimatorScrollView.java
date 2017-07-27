package com.test.custom.mydemos.ui.L19;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
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

    Rect mRect = new Rect();
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        getHitRect(mRect);
        boolean localVisibleRect = getLocalVisibleRect(mRect);

    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View childAt = getChildAt(0);
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
