package com.test.custom.mydemos.ui.L18;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;

import com.test.custom.mydemos.utils.LogUtil;

/**
 * Author 陈国武
 * Time 2017/7/25.
 * Des:
 */

public class SplashView extends View {


    private int STATE = 1 ;
    private static final int ROTATE_STATE = 1 ;
    private static final int WAVE_STATE = 2 ;

    private Paint mPaint ,mColorPaint ;
    private float mMaxRadius ;
    private float deTaRadius ;
    private int[] colors = {Color.GREEN,Color.RED,Color.YELLOW};


    private float mCircleRadius = 100 ;
    private float colorDegree = 30f;
    private float clolorWidth = 30 ;
    private float startDegree = 0 ;
    private float emptyDegree = 0 ; // 颜色间隔角度

    public SplashView(Context context) {
        this(context,null);
    }

    public SplashView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);

        mColorPaint = new Paint();
        mColorPaint.setStyle(Paint.Style.STROKE);
        mColorPaint.setAntiAlias(true);
        mColorPaint.setStrokeWidth(clolorWidth);
        mColorPaint.setStrokeCap(Paint.Cap.ROUND);
        emptyDegree = (360f - colors.length * colorDegree)/colors.length ;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMaxRadius = (float) Math.hypot(getWidth() / 2, getHeight() / 2);
        mPaint.setStrokeWidth(mMaxRadius*2);
        LogUtil.e("onMeasure");

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogUtil.e("onLayout");
        drawCircle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        LogUtil.e("onDraw");
        float centerX = canvas.getWidth()/2;
        float centerY = canvas.getHeight() /2 ;

        switch (STATE){
            case WAVE_STATE:
                //波纹扩散阶段
                canvas.drawCircle(centerX ,centerY ,mMaxRadius,mPaint);
                break;
            case ROTATE_STATE:

                canvas.drawCircle(centerX ,centerY ,mMaxRadius,mPaint);
                    startDegree = 0 ;
                for (int i = 0; i < colors.length; i++) {
                    LogUtil.e("startDegree  "+ startDegree);
                mColorPaint.setColor(colors[i]);
                canvas.drawArc(centerX - mCircleRadius,centerY - mCircleRadius ,centerX + mCircleRadius , centerY + mCircleRadius ,startDegree + deTaRadius, colorDegree,false,mColorPaint);
                    startDegree+= colorDegree+ emptyDegree;
                }
                break;
        }

    }

    private void drawWave(){
        ValueAnimator ani = ValueAnimator.ofFloat(mMaxRadius*2 ,0);
        ani.setDuration(1000);
        ani.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float radius = (float) animation.getAnimatedValue();
                mPaint.setStrokeWidth(radius);
                postInvalidate();
            }
        });
        ani.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                STATE = ROTATE_STATE ;
                postInvalidate();

            }
        });
        ani.start();
    }

    private void drawCircle(){

        //旋转
        ValueAnimator ani = ValueAnimator.ofFloat(0,360f);
        ani.setDuration(2000);
        ani.setInterpolator(new OvershootInterpolator(10f));
        ani.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float radius = (float) animation.getAnimatedValue();
                deTaRadius = radius ;
                postInvalidate();
            }
        });

        //放大聚合
        ValueAnimator ani2 = ValueAnimator.ofFloat(mCircleRadius,0);
        ani2.setDuration(500);
        ani2.setInterpolator(new AnticipateInterpolator(5f));
        ani2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                mCircleRadius = animatedValue ;
                postInvalidate();
            }
        });
        ani2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                STATE = WAVE_STATE ;
                postInvalidate();
                drawWave();

            }
        });

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ani).before(ani2);
        animatorSet.start();

    }

}
