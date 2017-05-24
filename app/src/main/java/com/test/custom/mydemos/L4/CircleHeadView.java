package com.test.custom.mydemos.L4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
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
    private int bmWidth ;
    private int bmHeight ;
    private int radius ;

    public CircleHeadView(Context context) {
        super(context);
    }

    public CircleHeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        bm = ((BitmapDrawable) getResources().getDrawable(R.drawable.test)).getBitmap();
        mPaint = new Paint();
        bmWidth = bm.getWidth();
        bmHeight = this.bm.getHeight();
        radius = 200 ;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawColor(Color.RED);//设置画布背景

        //TileMode 平铺模式（图片大小 小鱼控件大小是平铺模式） CLAMP 图像边缘像素平铺  REPEAT 图片重复显示图片 MIRROR 镜面效果（倒影效果）
        BitmapShader bms = new BitmapShader(bm, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        mPaint.setShader(bms);
        mPaint.setStrokeWidth(3);
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(50);
        canvas.drawCircle(200,200,radius,mPaint);
        canvas.drawText("SSSSSSSSSSSWEGBWEPBQ",20,450,mPaint);

        //矩阵
        Matrix mtx = new Matrix();
        //图片等比例缩放
        if (bmWidth >= bmHeight){
            //宽图片 高度缩到圆的 radius 数值

        }else {
            //高图片 宽度缩到 radius 数值
//            float scalex = (float) radius*2 / (float) bmWidth ; // 宽度的缩小比例
            float scalex = (float) 0.5 ; // 宽度的缩小比例
             mtx.setScale(1,(float) 4.2);

        }
//        mtx.setRotate(30);
        bms.setLocalMatrix(mtx);
        canvas.drawCircle(200,700,radius,mPaint);


    }
}
