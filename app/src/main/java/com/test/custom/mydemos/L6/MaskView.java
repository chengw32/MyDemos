package com.test.custom.mydemos.L6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.test.custom.mydemos.R;

/**
 * Author 陈国武
 * Time 2017/5/31.
 * Des:
 */

public class MaskView extends View {

    private Bitmap xiaowu;
    private Paint paint ;

    public MaskView(Context context) {
        this(context,null);
    }

    public MaskView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        xiaowu = BitmapFactory.decodeResource(getResources(), R.mipmap.xiaowu);
    }

    @Override
    protected void onDraw(Canvas canvas) {


        paint.setMaskFilter(new EmbossMaskFilter(new float[]{1,1,1},0.1f,50,50));
        canvas.drawRect(new Rect(50,50,500,500),paint);
//        canvas.drawBitmap(xiaowu,null,new Rect(50,50,500,500),paint);

    }
}
