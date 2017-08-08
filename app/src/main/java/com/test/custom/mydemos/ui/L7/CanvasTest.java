package com.test.custom.mydemos.Ui.L7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.test.custom.mydemos.R;

/**
 * Author 陈国武
 * Time 2017/6/1.
 * Des:
 */

public class CanvasTest extends View {

    private Paint mPaint;
    private Bitmap xiaowu;

    public CanvasTest(Context context) {
        super(context);
    }

    public CanvasTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        xiaowu = BitmapFactory.decodeResource(getResources(), R.mipmap.xiaowu);
        mPaint.setShader(new BitmapShader(xiaowu, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR));
    }


    @Override
    protected void onDraw(Canvas canvas) {


//        Path path = new Path();
//        path.addCircle(300,300,150, Path.Direction.CCW);
        Region region1 = new Region(200,10,250,200);

        Region region = new Region(10,10,300,50);
        region.op(region1, Region.Op.XOR);
//        region1.setPath(path,region);
        RegionIterator regionIterator = new RegionIterator(region);
        Rect rect = new Rect();
        while (regionIterator.next(rect)){
            canvas.drawRect(rect,mPaint);
        }


        mPaint.setShader(null);

        canvas.save();

        canvas.drawLine(0,0,canvas.getWidth(),0,mPaint);
        canvas.drawCircle(130,400,50,mPaint);


        canvas.translate(50,50);
        canvas.save();
        canvas.drawLine(0,0,canvas.getWidth(),0,mPaint);
        canvas.drawCircle(130,400,50,mPaint);



//        canvas.rotate( 30);
//        canvas.save();
//        canvas.drawLine(0,0,canvas.getWidth(),0,mPaint);
//        canvas.drawCircle(130,400,50,mPaint);

        mPaint.setColor(Color.GRAY);
        canvas.drawCircle(130,400,30,mPaint);


        canvas.saveLayer(0,0,canvas.getWidth(),canvas.getHeight(),mPaint,Canvas.ALL_SAVE_FLAG);
        canvas.drawColor(Color.RED);
        canvas.rotate(10);
        canvas.saveLayer(50,50,canvas.getWidth(),canvas.getHeight(),mPaint,Canvas.ALL_SAVE_FLAG);
        canvas.drawColor(Color.GREEN);
        canvas.drawCircle(50,50,50,mPaint);
        canvas.rotate(-30);
        canvas.saveLayer(50,50,canvas.getWidth(),canvas.getHeight(),mPaint,Canvas.ALL_SAVE_FLAG);
        canvas.drawColor(Color.GRAY);



    }

}
