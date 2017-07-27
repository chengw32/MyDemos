package com.test.custom.mydemos.ui.L5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.test.custom.mydemos.R;

/**
 * Author 陈国武
 * Time 2017/5/27.
 * Des:
 */

public class RoundCornerImageView extends View {
    private Paint paint;
    private Bitmap heiyihu;
    private Bitmap yuanjiao;
    private Bitmap xyjy;
    private Bitmap xiaowu;

    public RoundCornerImageView(Context context) {
        this(context,null);
    }

    public RoundCornerImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        paint = new Paint();
        heiyihu = BitmapFactory.decodeResource(getResources(), R.mipmap.test);
        yuanjiao = BitmapFactory.decodeResource(getResources(), R.mipmap.shade);
        xyjy = BitmapFactory.decodeResource(getResources(), R.mipmap.xiaowu);
        xiaowu = BitmapFactory.decodeResource(getResources(), R.mipmap.xiaowu);


    }


    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        canvas.drawBitmap(yuanjiao,0,0,paint);
//        canvas.drawBitmap(heiyihu,0,0,paint);
        canvas.drawCircle(100,100,100,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));


        canvas.drawBitmap(xiaowu,0,0,paint);

//        canvas.drawBitmap(xyjy,0,0,paint);

    }
}
