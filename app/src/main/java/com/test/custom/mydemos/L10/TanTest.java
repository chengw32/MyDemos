package com.test.custom.mydemos.L10;

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
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.test.custom.mydemos.R;
import com.test.custom.mydemos.utils.LogUtil;

/**
 * Author 陈国武
 * Time 2017/7/3.
 * Des:
 */

public class TanTest extends View {
    private Paint paint ;
    private PathMeasure pm ;
    private Path path;
    private Matrix matrix ;
    private Bitmap bm ;
    private float length ;
    public TanTest(Context context) {
        super(context);
    }

    public TanTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        pm = new PathMeasure();

        path = new Path();
        path.addCircle(200,200,100, Path.Direction.CW);
        pm.setPath(path,false);
        length = pm.getLength();
        LogUtil.e("length--- "+ length);

        matrix = new Matrix();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize =  4;
        bm = BitmapFactory.decodeResource(getResources(), R.drawable.timg,options);
    }


    @Override
    protected void onDraw(Canvas canvas) {


        canvas.drawPath(path,paint);

        pm.getMatrix(mDeltaX, matrix, PathMeasure.POSITION_MATRIX_FLAG | PathMeasure.TANGENT_MATRIX_FLAG);
        matrix.preTranslate(- bm.getWidth()/2 ,-bm.getHeight() / 2);

        canvas.drawBitmap(bm,matrix,null);

    }
    private ValueAnimator animator ;
    private int mDeltaX ;
    public void  move(){
        animator = ValueAnimator.ofInt(0,(int)length);
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
    }
    public void removeAnimator(){
        if (null != animator)animator.end();
    }
}
