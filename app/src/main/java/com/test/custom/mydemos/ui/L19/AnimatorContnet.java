package com.test.custom.mydemos.Ui.L19;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.test.custom.mydemos.utils.LogUtil;


/**
 * Author 陈国武
 * Time 2017/7/28.
 * Des: 真正执行动画的是这层view
 */

public class AnimatorContnet extends FrameLayout implements AniExecute {

    private static final int TRANSLATION_FROM_TOP = 0x01;
    private static final int TRANSLATION_FROM_BOTTOM = 0x02;
    private static final int TRANSLATION_FROM_LEFT = 0x04;
    private static final int TRANSLATION_FROM_RIGHT = 0x08;
    private boolean hasAlpha ;
    private int ani_move;
    private int width ,height;

    public void setAni_move(int ani_move) {
        this.ani_move = ani_move;
    }

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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = getWidth();
        height =    getHeight();
    }

    @Override
    public void startAni(float percent) {
        LogUtil.e("perchent     "+percent);
        if (hasAlpha)setAlpha(1f*percent);
        LogUtil.e("hasMoveTOP"+hasMove(TRANSLATION_FROM_TOP));
        if (hasMove(TRANSLATION_FROM_TOP))setTranslationY(height*percent);
        if (hasMove(TRANSLATION_FROM_LEFT))setTranslationX(-width*(1-percent));
    }

    @Override
    public void restoreAni() {
        if (hasAlpha)setAlpha(1f);
    }

    private boolean hasMove(int move){
        if (ani_move == -1)return false;
        //fromLeft|fromeBottom & fromBottom = fromBottom
        return (ani_move & move) == move ;
    }

}
