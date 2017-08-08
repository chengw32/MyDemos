package com.test.custom.mydemos.Ui.L8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;

import com.test.custom.mydemos.utils.LogUtil;

/**
 * Author 陈国武
 * Time 2017/6/15.
 * Des:
 */

public class AnimatSearchView extends View {
    private Rect mRect;
    private Paint mPaint;
    private int storkeWidth = 5 ;
    private int mRadius = 50;
    private int mStartDegress = 45 ; //圆 开始的角度
    private int mDrawDegree = 360 ;//圆要画的角度的增量
    private Path mPath ;
    private float mPathRadius ;//手柄的起点坐标的累积值
    private float mPathSpeed = 8 ;//手柄的起点坐标的变化量
    private float mYradius ;//手柄的起点坐标和终点坐标的 y 坐标的差值
    private float mXradius ;//底下的线的左边的点的 x 轴往左边增加的累积量
    private float mCenterY ;//整个放大镜的 y 轴移动的累积量
    private float mCenterYspeed = 7;//整个放大镜的 y 轴移动的变化量
    private int mDegreeSpeed = 15 ;//圆圈阶段的角度变化量
    private float sinX; //半径正弦值


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
        mPath = new Path();
    }


    @Override
    protected void onDraw(Canvas canvas) {


        //画圆也可以用画path的方法实现 也就是整个效果可以用 drawPath 实现 path.arcTo 可以画圆

        canvas.drawColor(Color.BLUE);

        canvas.save();
        Gravity.apply(Gravity.CENTER, mRadius*2, mRadius*2, canvas.getClipBounds(), mRect);//在画布中心裁切出一个矩形区域
//        canvas.clipRect(mRect);
//        canvas.drawRect(mRect,mPaint);
        float x = canvas.getWidth() / 2;
        float y = canvas.getHeight() / 2;

//        int x = (mRect.right - mRect.left) / 2;
//        int y = (mRect.bottom - mRect.top) / 2;


//        canvas.drawCircle(x, y, mRadius, mPaint);
//        canvas.restore();
        mRect.left += mCenterY ;
        mRect.right += mCenterY;

        canvas.drawArc(new RectF(mRect),mStartDegress,mDrawDegree,false,mPaint);
//        float v = (float) (mRadius * Math.cos(45));
        sinX = (float) (Math.sin((45f /180f)*Math.PI)*mRadius);

        float x1 = x + sinX + mPathRadius + mCenterY;//手柄的起点 x 坐标
        float y1 = y + sinX + mPathRadius;
        float x2 = x + sinX * 2 + mCenterY;
        float y2 = y + sinX * 2;
        mYradius = y2 - y - sinX ;
        LogUtil.e("mYradius---- "+mYradius);
        LogUtil.e("mPathRadius---- "+mPathRadius);
        mPath.reset();//因为是成员变量 说要要重置
        mPath.moveTo(x1,y1);
        mPath.lineTo(x2,y2);
        mPath.lineTo(x2-mXradius -  mCenterY,y2);
        canvas.drawPath(mPath,mPaint);
//        canvas.restore();
//        canvas.drawLine( x1,y1,x2,y2,mPaint);
//
//        canvas.drawLine(0,y2,x2,y2,mPaint);

////        canvas.drawArc();
//        mPaint.setColor(Color.BLUE);
//        RectF mRectf = new RectF(mRect);
//        canvas.drawArc(mRectf,-45,360,true,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startAnmat();
                break;
        }
        return super.onTouchEvent(event);
    }


    private void startAnmat() {

        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mStartDegress < 405){
                    //从 45度开始画的 画一圈就是 45 + 360
                mStartDegress += mDegreeSpeed ;
                mDrawDegree -= mDegreeSpeed ;
                }else{
                    //圆圈画完画手柄 如果 Y 轴的累积量超过 手柄的 y 轴长度 则手柄画完了
                    if (mPathRadius <= mYradius){
                        mPathRadius += mPathSpeed;
                    }
                }

                if (mXradius <= getWidth()/2 + sinX * 2 ){
                mXradius += mPathSpeed ;
                mCenterY += mCenterYspeed ;
                invalidate();
                startAnmat();
                }


            }
        },10);

    }
}
