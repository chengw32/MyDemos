package com.test.custom.mydemos.Ui.L3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
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
    private float mouthWidth  ;
    private float mouthHeight  ;
    private float eyeRadius  ;//眼睛半径
    private float lineRadius = 5 ;//线条厚度
    private Path mPath ;
    //间隙
    private int space = 100 ;

    public BaNaNaView(Context context) {
        this(context,null);
    }

    public BaNaNaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPath = new Path();
        bodyRect = new RectF();

    }

    @Override
    protected void onDraw(Canvas canvas) {

        drawBody(canvas);
        drawMouth(canvas);
        drawEye(canvas);
        drawTrousers(canvas);
        drawPocket(canvas);

    }

    private void drawPocket(Canvas canvas) {

//        mPath.moveTo(bodyRect.left + bodyWidth*3/8 ,bodyRect.bottom - bodyHeight*5/16);
//        mPath.lineTo(bodyRect.right - bodyWidth*3/8 ,bodyRect.bottom - bodyHeight*5/16);
        mPath.addRect(bodyRect.left + bodyWidth*3/8 ,bodyRect.bottom - bodyHeight*5/16,bodyRect.right - bodyWidth*3/8,bodyRect.bottom - bodyHeight*3/16, Path.Direction.CW);
        canvas.drawPath(mPath,getBlackStokeLinePaint());



    }

    private void drawTrousers(Canvas canvas) {


        mPath.moveTo(bodyRect.left,bodyRect.bottom - bodyHeight/4);
        mPath.lineTo(bodyRect.left + bodyWidth /4 ,bodyRect.bottom - bodyHeight/4);
        mPath.lineTo(bodyRect.left + bodyWidth /4,bodyRect.bottom - bodyHeight*3 / 8);
        mPath.lineTo(bodyRect.right - bodyWidth /4,bodyRect.bottom - bodyHeight*3 / 8);
        mPath.lineTo(bodyRect.right - bodyWidth /4,bodyRect.bottom - bodyHeight / 4);
        mPath.lineTo(bodyRect.right,bodyRect.bottom - bodyHeight / 4);
        mPath.lineTo(bodyRect.right,bodyRect.bottom );
        mPath.lineTo(bodyRect.left,bodyRect.bottom );
        mPath.close();

        //画 裤子区域颜色
        linePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawPath(mPath,getBlueFillPaint());

        //画 裤子边缘线
        canvas.drawPath(mPath,getBlackStokeLinePaint());

        linePaint.setXfermode(null);
        mPath.reset();
    }

    private void drawEye(Canvas canvas) {
        //上半身的中间画眼镜

        //画眼镜的白色区域
        canvas.drawCircle(width / 2 -  eyeRadius - lineRadius/2, (height / 2 + space / 2)/2,eyeRadius,getWhitFillPaint());
        canvas.drawCircle(width / 2 +  eyeRadius+lineRadius/2 , (height / 2 + space / 2)/2,eyeRadius,getWhitFillPaint());

        //画眼镜的边缘线
        canvas.drawCircle(width / 2 -  eyeRadius - lineRadius/2, (height / 2 + space / 2)/2,eyeRadius,getBlackStokeLinePaint());
        canvas.drawCircle(width / 2 +  eyeRadius+lineRadius/2 , (height / 2 + space / 2)/2,eyeRadius,getBlackStokeLinePaint());

        //画镜框？？？？
        canvas.drawLine( bodyRect.left ,bodyRect.top  + bodyHeight / 4,bodyRect.left+ (bodyWidth - eyeRadius*4)/2,bodyRect.top  + bodyHeight / 4,getBlackStokeLinePaint());
        canvas.drawLine( bodyRect.right - (bodyWidth - eyeRadius*4)/2,bodyRect.top + bodyHeight / 4,bodyRect.right,bodyRect.top + bodyHeight / 4,getBlackStokeLinePaint());
    }

    private void drawMouth(Canvas canvas) {

        mPath.moveTo(width/2 -mouthWidth/3 ,height / 2 - mouthHeight / 2);
        mPath.rQuadTo(mouthWidth/4,mouthHeight,mouthWidth*3/4,0);
        mPath.close();
        //画区域
        canvas.drawPath(mPath,getWhitFillPaint());

        //画线
        canvas.drawPath(mPath,getBlackStokeLinePaint());
        mPath.reset();

    }



    /**
     * Des：
     * Author：陈国武
     * Time：2017/8/9 14:52
     */

    private RectF bodyRect ;
    private void drawBody(Canvas canvas) {

        canvas.drawRoundRect(bodyRect,width/4,width/4,getYellowFillPaint());

    }

















    private Paint getBlueFillPaint(){
        getPaintInstance();
        linePaint.setColor(Color.rgb(32, 116, 160));
        linePaint.setStyle(Paint.Style.FILL);
        return linePaint ;
    }
    private Paint getYellowFillPaint(){
        getPaintInstance();
        linePaint.setColor(Color.YELLOW);
        linePaint.setStyle(Paint.Style.FILL);
        return linePaint ;
    }
    private Paint getWhitFillPaint(){
        getPaintInstance();
        linePaint.setColor(Color.WHITE);
        linePaint.setStyle(Paint.Style.FILL);
        return linePaint ;
    }

    public Paint getBlackStokeLinePaint() {
            getPaintInstance();
            linePaint.setColor(Color.BLACK);
            linePaint.setStrokeWidth(lineRadius);
            linePaint.setStyle(Paint.Style.STROKE);
        return linePaint;
    }




    Paint linePaint ;
    private Paint getPaintInstance(){
        if (null == linePaint)linePaint = new Paint();
        linePaint.setAntiAlias(true);
        return linePaint ;
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
        mouthWidth = bodyWidth* 0.3f ;
        mouthHeight = mouthWidth / 3 ;
        //两只眼睛占身体宽度的0.75
        eyeRadius = bodyWidth * 0.7f / 4f ;

        bodyRect.left = (width - bodyWidth)/2 ;
        bodyRect.top = (height - bodyHeight)/2 ;
        bodyRect.right = (width - bodyWidth)/2 + bodyWidth  ;
        bodyRect.bottom = (height - bodyHeight)/2 + bodyHeight ;
    }

}
