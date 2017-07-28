package com.test.custom.mydemos.ui.L19;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Author 陈国武
 * Time 2017/7/28.
 * Des: 真正执行动画的是这层view
 */

public class AnimatorContnet extends FrameLayout implements AniExecute {
    private boolean hasAlpha ;

    public void setHasAlpha(boolean hasAlpha) {
        this.hasAlpha = hasAlpha;
    }

    public AnimatorContnet(@NonNull Context context) {
        this(context,null);
    }

    public AnimatorContnet(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public void startAni() {

        if (hasAlpha)setAlpha(0f);
    }

    @Override
    public void restoreAni() {

    }


}
