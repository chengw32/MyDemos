package com.smartParking.mine.activity.myCar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Author 陈国武
 * Time 2017/11/17.
 * Des:
 */

@SuppressLint("AppCompatCustomView")
public class CircleTextView extends TextView {

    private Color bgColor ;

    public CircleTextView(Context context) {
        super(context);
    }

    public CircleTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void draw(Canvas canvas) {

        int circle_x = getWidth()/2;
        int circle_y =getHeight()/2;
        int circle_radius =getWidth()/2;

        //画圆形背景
        Paint paint_bg = new Paint();
        paint_bg.setAntiAlias(true);
        paint_bg.setColor(Color.WHITE);
        canvas.drawCircle(circle_x,circle_y,circle_radius,paint_bg);
        //画灰边缘
        paint_bg.setColor(Color.GRAY);
        paint_bg.setStyle(Paint.Style.STROKE);
        paint_bg.setStrokeWidth(1);
        canvas.drawCircle(circle_x,circle_y,circle_radius,paint_bg);


        super.draw(canvas);

    }
}
