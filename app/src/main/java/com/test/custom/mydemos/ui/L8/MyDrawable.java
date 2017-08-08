package com.test.custom.mydemos.Ui.L8;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;

import com.test.custom.mydemos.utils.LogUtil;

/**
 * Author 陈国武
 * Time 2017/6/13.
 * Des:
 */

public class MyDrawable extends Drawable {

    private int radius = 0;

    private Rect outColorRec;
    private Drawable mColorDrawable,mBlackDrawable;
    public MyDrawable(Drawable colorDrawable, Drawable blackDrawable){
        outColorRec = new Rect();
        mColorDrawable = colorDrawable;
        mBlackDrawable = blackDrawable;
    }


    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect content = getBounds();


        int w = content.width();
        int h = content.height();

//        Gravity.apply(Gravity.LEFT,w/2,h,content,outColorRec);//确定裁切矩阵的区域范围
//        canvas.clipRect(outColorRec);//裁切矩阵
        canvas.translate(-radius,0);
        mColorDrawable.draw(canvas);

        Gravity.apply(Gravity.LEFT,w/2,h,content,outColorRec);
        outColorRec.left = radius ;
        outColorRec.right = w/2 + radius ;
        canvas.clipRect(outColorRec);
        mBlackDrawable.draw(canvas);

    }


    @Override
    protected boolean onLevelChange(int level) {

        LogUtil.e("level--  "+level);
        radius = level;
        invalidateSelf();

        return super.onLevelChange(level);
    }



    @Override
    protected void onBoundsChange(Rect bounds) {
        int left = bounds.left;
        int top = bounds.top;
        int right = bounds.right;
        int bottom = bounds.bottom;
        LogUtil.e("right-"+right+"-bottom-"+bottom);
        mColorDrawable.setBounds(bounds);
        mBlackDrawable.setBounds(bounds);
//        mBlackDrawable.setBounds(new Rect(0,0,mBlackDrawable.getIntrinsicWidth(),mBlackDrawable.getIntrinsicHeight()));
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {

    }

    @Override
    public int getIntrinsicHeight() {
        return Math.max(mColorDrawable.getIntrinsicHeight(),mBlackDrawable.getIntrinsicHeight());
    }

    @Override
    public int getIntrinsicWidth() {
        return Math.max(mColorDrawable.getIntrinsicWidth(),mBlackDrawable.getIntrinsicWidth());
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }
}
