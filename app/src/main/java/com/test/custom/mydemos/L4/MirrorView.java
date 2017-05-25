package com.test.custom.mydemos.L4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.test.custom.mydemos.R;

/**
 * Author 陈国武
 * Time 2017/5/25.
 * Des:
 */

public class MirrorView extends View {


    private Bitmap bitmap;
    private Bitmap scaleBitmap ;
    private int scale = 2 ;
    private int radius = 150 ;
    private  ShapeDrawable sd;
    private Matrix matrix ;

    public MirrorView(Context context) {
        super(context);
    }

    public MirrorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        scaleBitmap = bitmap ;
        scaleBitmap = Bitmap.createScaledBitmap(scaleBitmap, scaleBitmap.getWidth() * scale, scaleBitmap.getHeight() * scale, true);
        BitmapShader bitmapShader = new BitmapShader(scaleBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        sd = new ShapeDrawable(new OvalShape());
        sd.getPaint().setShader(bitmapShader);
        sd.setBounds(0,0,radius * 2,radius * 2);

        matrix = new Matrix();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap,0,0,null);
        sd.draw(canvas);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        int x = (int) event.getX();
        int y = (int) event.getY();

        matrix.setTranslate(radius - x * scale,radius - y * scale);
        sd.getPaint().getShader().setLocalMatrix(matrix);
        sd.setBounds(x-radius,y-radius,x+radius,y+radius);//随手指移动放大镜区域
        invalidate();

        Log.e("x-y","----x= "+x+"-----y= "+y);

        return true;
    }
}
