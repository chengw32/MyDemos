package com.test.custom.mydemos.L8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

/**
 * Author 陈国武
 * Time 2017/6/15.
 * Des:
 */

public class AnimatSearchView extends View {
    private Rect mRect;
    private Paint mPaint;
    private int storkeWidth = 2 ;
    private int mRadius = 20;

    public AnimatSearchView(Context context) {
        this(context, null);
    }

    public AnimatSearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(storkeWidth);
        mPaint.setAntiAlias(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {


        canvas.drawColor(Color.BLUE);

        canvas.save();
        Gravity.apply(Gravity.CENTER, mRadius*2+storkeWidth*2, mRadius*2+storkeWidth*2, canvas.getClipBounds(), mRect);
        canvas.clipRect(mRect);
        int x = canvas.getWidth() / 2;
        int y = canvas.getHeight() / 2;
        canvas.drawCircle(x, y, mRadius, mPaint);
        canvas.drawArc(new RectF(mRect),0,360,true,mPaint);
        canvas.restore();
        float v = (float) (mRadius * Math.sin(45));

        canvas.drawLine( x + v,y+v,x+v*2, y+ v*2,mPaint);

////        canvas.drawArc();
//        mPaint.setColor(Color.BLUE);
//        RectF mRectf = new RectF(mRect);
//        canvas.drawArc(mRectf,-45,360,true,mPaint);
    }
}
