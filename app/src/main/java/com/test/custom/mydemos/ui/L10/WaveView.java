package com.test.custom.mydemos.Ui.L10;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.test.custom.mydemos.R;
import com.test.custom.mydemos.utils.LogUtil;

/**
 * Author 陈国武
 * Time 2017/6/29.
 * Des:
 */

public class WaveView extends View {

    private final Paint mPaint;
    private Path mPath;
    private final int mWaveRadius = 300 ;
    private final float mWaveHeight = 20 ;
    private int mWaveSize ;
    private int mDeltaX ;

    private int centerX ;
    private int centerY = 100;
    private int circleRadius = 100;//圆半径
    private int boSize  = circleRadius*2 / mWaveRadius + 2;
    private PathMeasure mPathMeasure ;
    private Matrix mMatrix ;
    private Bitmap bm ;


    public WaveView(Context context) {

        this(context,null);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);

        mPathMeasure = new PathMeasure();

        mMatrix = new Matrix();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize =  2;
        bm = BitmapFactory.decodeResource(getResources(), R.drawable.timg,options);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWaveSize =  getWidth() / mWaveRadius + 2 ;//从
        centerX = getWidth() / 2 ;

    }

    @Override
    protected void onDraw(Canvas canvas) {

        /**
         * 以下代码是在某个圆内画水波纹
         * */
        mPaint.setColor(Color.RED);
        canvas.drawCircle(centerX,centerY,circleRadius,mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//
        mPaint.setColor(Color.WHITE);
        mPath.reset();
        mPath.moveTo(centerX - circleRadius - mWaveRadius + mDeltaX,centerY );
        for (int i = 0; i < boSize; i++) {
            mPath.rQuadTo(mWaveRadius/4,10,mWaveRadius / 2 , 0);
            mPath.rQuadTo(mWaveRadius/4,-10,mWaveRadius / 2 , 0);
        }

        mPath.lineTo(centerX + circleRadius,centerY + circleRadius);
        mPath.lineTo(centerX - circleRadius,centerY + circleRadius);
        mPath.close();
        canvas.drawPath(mPath,mPaint);
        mPaint.setXfermode(null);




        mPaint.setColor(Color.RED);
        float length = mPathMeasure.getLength();
        LogUtil.e("length---- "+length);
        mPathMeasure.nextContour();
        float length2 = mPathMeasure.getLength();
        LogUtil.e("length2---- "+length2);



        /**
         * 以下代码实现波浪效果
         * */
        mPath.reset();
        mPath.moveTo(-mWaveRadius + mDeltaX,canvas.getHeight() / 2);
        for (int i = 0; i < mWaveSize ; i++) {
            mPath.rQuadTo(mWaveRadius/4,mWaveHeight,mWaveRadius/2,0);
            mPath.rQuadTo(mWaveRadius/4,-mWaveHeight,mWaveRadius/2,0);
        }
        mPath.lineTo(canvas.getWidth(),canvas.getHeight());
        mPath.lineTo(0,canvas.getHeight());

        mPathMeasure.setPath(mPath,false);
        canvas.drawPath(mPath,mPaint);
        mMatrix.reset();
        mPathMeasure.getMatrix(mDeltaX+mWaveRadius,mMatrix,PathMeasure.TANGENT_MATRIX_FLAG | PathMeasure.POSITION_MATRIX_FLAG);
        mMatrix.preTranslate(- bm.getWidth()/2 ,-bm.getHeight() / 2);//跳转图片位置
        canvas.drawBitmap(bm,mMatrix,mPaint);


    }

    private ValueAnimator animator ;
    public void move(){
        animator = ValueAnimator.ofInt(0,mWaveRadius);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDeltaX = (int) animation.getAnimatedValue();
                LogUtil.e("----   "+mDeltaX);
                postInvalidate();
            }
        });
        animator.start();
        invalidate();
    }

    public void removeAnimator(){
        if (null != animator)animator.end();
    }

}
