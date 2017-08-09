package com.test.custom.mydemos.Ui.L3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author 陈国武
 * Time 2017/8/9.
 * Des:
 */

public class BaNaNaView extends View {

    private int width,height ;
    private int bodyWidth ;
    private int bodyHeight;
    //间隙
    private int space = 100 ;

    public BaNaNaView(Context context) {
        this(context,null);
    }

    public BaNaNaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);



    }

    @Override
    protected void onDraw(Canvas canvas) {

        drawBody(canvas);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
            height = h ;
            width = w ;
        //宽高比 0.7
        if (w >= h){
            //以高为标准， 小黄人的高度为控件高度减去一定空隙
            bodyHeight = h - space ;
            bodyWidth = (int) (0.6 * bodyHeight);

        }else {
            //以宽为标准
            bodyWidth = w - space ;
            bodyHeight = (int) (w / 0.6);
        }
    }

    /**
     * Des：
     * Author：陈国武
     * Time：2017/8/9 14:52
     */

    private void drawBody(Canvas canvas) {

        canvas.drawRoundRect((width - bodyWidth)/2,(height - bodyHeight)/2,(width - bodyWidth)/2 + bodyWidth,(height - bodyHeight)/2 + bodyHeight,width/4,width/4,getBodyPaint());

    }
















    Paint bodyPaint ;
    public Paint getBodyPaint() {
        if (null == bodyPaint){
            bodyPaint = new Paint();
            bodyPaint.setColor(Color.YELLOW);
            bodyPaint.setAntiAlias(true);
            bodyPaint.setStyle(Paint.Style.STROKE);
            bodyPaint.setStrokeWidth(20);
        }
        return bodyPaint;
    }

    Paint linePaint ;
    public Paint getLinePaint() {
        if (null == linePaint){
            linePaint = new Paint();
            linePaint.setColor(Color.BLACK);
            linePaint.setStrokeWidth(20);
        }
        return linePaint;
    }

}
