package com.test.custom.mydemos.L9;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.test.custom.mydemos.R;
import com.test.custom.mydemos.utils.LogUtil;

/**
 * Author 陈国武
 * Time 2017/6/23.
 * Des: qq消息气泡 拖拉橡皮筋效果  爆炸效果
 */

public class PaoPaoView extends View {

    private final int PP_STATE_RESET = 0 ; //原始状态 重置
    private final int PP_STATE_PULL = 2 ; //橡皮筋状态
    private final int PP_STATE_MOVE = 3 ; //拖动状态
    private final int PP_STATE_MISS = 4 ; //爆炸消失
    private int PP_STATE ;
    private boolean isControl ; //移动的泡泡是否被操作

    private final int RESET_RADIUS = 20 ;//固定的泡泡的 初始半径

    private PPPoint movePP ,normalPP;//原始泡泡坐标

    private float  centerDis ; //圆心距
    private Paint arePaint  ;
    private float controlX ,controlY;//贝塞尔曲线的控制点 两个圆心的中点
    private Path arePath;
    private final float CONNECT_RADIUS = 100;
    /**
     *  气泡爆炸的bitmap数组
     */
    private Bitmap[] mBurstBitmapsArray;

    /**
     *  气泡爆炸的图片id数组
     */
    private int[] mBurstDrawablesArray = {R.drawable.burst_1, R.drawable.burst_2
            , R.drawable.burst_3, R.drawable.burst_4, R.drawable.burst_5};

    public PaoPaoView(Context context) {
        this(context,null);
    }

    public PaoPaoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PaoPaoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initArePaint();

        initMovePP();

        initNormalPP();

        initBitmap();

    }

    private void initBitmap() {
        mBurstBitmapsArray = new Bitmap[mBurstDrawablesArray.length];
        for (int i = 0; i < mBurstDrawablesArray.length; i++) {
            //将气泡爆炸的drawable转为bitmap
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), mBurstDrawablesArray[i]);
            mBurstBitmapsArray[i] = bitmap;
        }
    }

    /**
     * Des：两个小球相连的区域的画笔
     * Author：陈国武
     * Time：2017/6/27 14:33
     */

    private void initArePaint() {
        arePaint = new Paint();
        arePaint.setAntiAlias(true);
        arePaint.setColor(Color.RED);
        arePath = new Path();
    }

    /**
     * Des：固定的点
     * Author：陈国武
     * Time：2017/6/26 17:29
     */
    
    private void initNormalPP() {

        normalPP = new PPPoint();
        normalPP.paint = new Paint();
        normalPP.radius = RESET_RADIUS ;
        normalPP.backgroundColor = Color.RED;
        normalPP.paint.setAntiAlias(true);
        normalPP.paint.setColor(Color.RED);

    }

    /**
     * Des：拖动的泡泡view
     * Author：陈国武
     * Time：2017/6/26 17:29
     */
    
    private void initMovePP() {
        movePP = new PPPoint();
        movePP.textRect = new Rect();
        movePP.text = "99" ;
        movePP.rect = new Rect();

        movePP.radius = 20 ;
        movePP.backgroundColor = Color.RED;
        movePP.textColor = Color.WHITE ;
        movePP.textSize = 3 ;
        movePP.paint = new Paint();
        movePP.paint.setStyle(Paint.Style.FILL);
        movePP.textPaint = new Paint();
        movePP.paint.setAntiAlias(true);
        movePP.textPaint.setAntiAlias(true);
        movePP.paint.setColor(Color.RED);
        movePP.textPaint.setColor(Color.WHITE);
        movePP.textPaint.setTextAlign(Paint.Align.CENTER);
        movePP.textPaint.setTextSize(10);
        movePP.textPaint.getTextBounds(movePP.text,0,movePP.text.length(),movePP.textRect); //将 text 的矩阵获取到 textPaint已经是在字体居中所以只要Y轴偏移就好了
        
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        LogUtil.e("---------------onMeasure");
        LogUtil.e("------------2 的平方---"+Math.pow(2,2));
        LogUtil.e("------------2 的平方 的平方根---"+Math.sqrt(Math.pow(2,2)));

        movePP.x = getWidth() / 2 ;
        movePP.y = getHeight() / 2 ;
        normalPP.x = getWidth() / 2 ;
        normalPP.y = getHeight() / 2 ;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawLine(0,canvas.getHeight() / 2,canvas.getWidth(),canvas.getHeight() / 2,ppp.paint);
//        canvas.drawLine(canvas.getWidth() / 2 ,0,canvas.getWidth() / 2,canvas.getHeight(),ppp.paint);
        canvas.drawColor(Color.BLUE);


        switch (PP_STATE){
            case PP_STATE_RESET:
                reset(canvas);
                break;
            case PP_STATE_PULL:
                  drawNormalPP(canvas);
                  drawConnectAre(canvas);
                  drawMovePP(canvas);
                break;
            case PP_STATE_MOVE:
                  drawMovePP(canvas);
                break;
            case PP_STATE_MISS:
                  drawBoomPP(canvas);
                break;

        }
    }

    /**
     * Des：重置成初始化状态
     * Author：陈国武
     * Time：2017/6/28 10:39
     */

    private void reset(Canvas canvas) {
        normalPP.radius = RESET_RADIUS ; //还原初始半径
        canvas.drawCircle(normalPP.x,normalPP.y,normalPP.radius,normalPP.paint);

    }

    /**
     * Des：泡泡爆炸
     * Author：陈国武
     * Time：2017/6/28 10:22
     */
    private int  mCurDrawableIndex;
    private void drawBoomPP(Canvas canvas) {


        movePP.rect.set((int)(movePP.x - movePP.radius),(int)(movePP.y - movePP.radius),(int)(movePP.x + movePP.radius),(int)(movePP.y + movePP.radius));
        canvas.drawBitmap(mBurstBitmapsArray[mCurDrawableIndex],null,
                movePP.rect,movePP.paint);

    }

    /**
     * Des：连接部分区域 橡皮筋效果
     * Author：陈国武
     * Time：2017/6/26 17:39
     * @param canvas
     */

    private void drawConnectAre(Canvas canvas) {



        if (normalPP.radius >= 10)normalPP.radius = normalPP.radius - centerDis / 8;

        controlX = (normalPP.x + movePP.x)/2 ;
        controlY = (normalPP.y + movePP.y)/2 ;

        //θ角度对应的x y 的差值
        float valueX = normalPP.radius *  ( movePP.y - normalPP.y ) / centerDis ;
        float valueY = normalPP.radius *  ( movePP.x - normalPP.x ) / centerDis ;

        //原点的A点坐标
        float Ax = normalPP.x - valueX;
        float Ay = valueY + normalPP.y;
        //原点D点的坐标
        float Dx = normalPP.x + valueX;
        float Dy = normalPP.y - valueY;


        //移动的点θ角度对应的x y 的差值
        float moveValueX = movePP.radius *  ( movePP.y - normalPP.y ) / centerDis ;
        float moveValueY = movePP.radius *  ( movePP.x - normalPP.x ) / centerDis ;

        //移动点B点的坐标
        float Bx = movePP.x - moveValueX;
        float By = moveValueY + movePP.y;
        //移动点C点的坐标
        float Cx = movePP.x + moveValueX;
        float Cy = movePP.y - moveValueY;

        arePath.reset();
        arePath.moveTo(Ax,Ay);
        arePath.quadTo(controlX,controlY,Bx,By);
        arePath.lineTo(Cx,Cy);
        arePath.quadTo(controlX,controlY,Dx,Dy);
        arePath.close();
        canvas.drawPath(arePath,arePaint);



    }

    /**
     * Des：画 移动的 泡泡
     * Author：陈国武
     * Time：2017/6/26 10:51
     */

    private void drawMovePP(Canvas canvas) {

        canvas.drawCircle(movePP.x,movePP.y,movePP.radius,movePP.paint);

        canvas.drawText(movePP.text,movePP.x,movePP.y + movePP.textRect.height() / 2 ,movePP.textPaint);


    }

    /**
     * Des：画原地的小球
     * Time：2017/6/23 11:15
     * @param canvas
     */

    private void drawNormalPP(Canvas canvas) {
     canvas.drawCircle(normalPP.x,normalPP.y,normalPP.radius,normalPP.paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        centerDis = (float) Math.sqrt(Math.pow(event.getX() - normalPP.x ,2)+ Math.pow(event.getY() - normalPP.y,2));
        LogUtil.e("event----  "+ PP_STATE);
        LogUtil.e("centerDis----  "+ centerDis);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                if (centerDis < normalPP.radius){
                    //按压点在泡泡上
                    isControl = true ;
                }else {
                    //按压点不在泡泡上
                    isControl = false;
                    PP_STATE = PP_STATE_RESET ;
                }

                break;
            case MotionEvent.ACTION_MOVE:

                if (isControl){
                    if ( centerDis > CONNECT_RADIUS){
                        PP_STATE = PP_STATE_MOVE ;
                    }else{
                        PP_STATE = PP_STATE_PULL ;
                    }
                    movePP.x = event.getX();
                    movePP.y = event.getY();
                  invalidate();
                }

                break;
            case MotionEvent.ACTION_UP:

                if (isControl){
                    if (centerDis > 100){
                    PP_STATE = PP_STATE_MISS ;
                        boomAnimat();
                    }else {
                        centerDis = 0 ;
                        PP_STATE = PP_STATE_RESET ;
                      invalidate();
                    }
                    isControl = false ;
                }

                break;
        }
        return true;
    }


    private void boomAnimat() {
        //做一个int型属性动画，从0~mBurstDrawablesArray.length结束
        ValueAnimator anim = ValueAnimator.ofInt(0, mBurstDrawablesArray.length-1);
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(500);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //设置当前绘制的爆炸图片index
                mCurDrawableIndex = (int) animation.getAnimatedValue();
                invalidate();
                LogUtil.e("mCurDrawableIndex"+mCurDrawableIndex);
            }
        });
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //修改动画执行标志
                LogUtil.e("onAnimationEnd");
                PP_STATE = PP_STATE_RESET ;
                invalidate();
            }
        });
        anim.start();
    }
}
