package com.test.custom.mydemos.L4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.test.custom.mydemos.R;


/**
 * Author 陈国武
 * Time 2017/5/22.
 * Des:
 */

public class CircleHeadView extends View {

    private Bitmap bm ;
    private Paint mPaint ;

    public CircleHeadView(Context context) {
        super(context);
    }

    public CircleHeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        bm = ((BitmapDrawable) getResources().getDrawable(R.drawable.test)).getBitmap();
        mPaint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawColor(Color.RED);//设置画布背景

        BitmapShader bms = new BitmapShader(bm, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
        mPaint.setShader(bms);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(50);
        canvas.drawCircle(200,200,200,mPaint);
        canvas.drawText("SSSSSSSSSSSWEGBWEPBQ",20,450,mPaint);
        canvas.drawRect(20,470,480,670,mPaint);

        //图片等比例缩放
        float scale = Math.max(bm.getWidth(),bm.getHeight()) / Math.min(bm.getWidth(),bm.getHeight());
        Matrix mtx = new Matrix();
        mtx.setScale(scale,scale);
        bms.setLocalMatrix(mtx);
        canvas.drawCircle(200,900,200,mPaint);


    }
}
