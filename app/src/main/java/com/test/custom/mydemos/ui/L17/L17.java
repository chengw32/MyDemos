package com.test.custom.mydemos.ui.L17;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.test.custom.mydemos.R;
import com.test.custom.mydemos.base.BaseActivity;
import com.test.custom.mydemos.utils.LogUtil;

import java.util.Random;

public class L17 extends BaseActivity {
    private View iv_test;
    private float resetX,resetY;
    private int screenWidth ,screenHeight ;
    private int srcWidth ,srcHeight;
    private ViewGroup.LayoutParams lp;
    private RelativeLayout content ;
    private Random random = new Random();
    private  Drawable [] drawables ;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_l17);

        content = (RelativeLayout) findViewById(R.id.content);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Drawable drawable0 = getResources().getDrawable(R.mipmap.heart1, null);
        Drawable drawable1 = getResources().getDrawable(R.mipmap.heart2, null);
        Drawable drawable2 = getResources().getDrawable(R.mipmap.heart3, null);
        Drawable drawable3 = getResources().getDrawable(R.mipmap.heart4, null);
        Drawable drawable4 = getResources().getDrawable(R.mipmap.heart5, null);
        Drawable drawable5 = getResources().getDrawable(R.mipmap.heart6, null);
        drawables = new Drawable[6];
        drawables[0] = drawable0 ;
        drawables[1] = drawable1 ;
        drawables[2] = drawable2 ;
        drawables[3] = drawable3 ;
        drawables[4] = drawable4 ;
        drawables[5] = drawable5 ;
        srcWidth = drawable0.getIntrinsicWidth();
        srcHeight = drawable0.getIntrinsicHeight();
        lp = new ViewGroup.LayoutParams(srcWidth,srcHeight);

        iv_test = findViewById(R.id.iv_test);
        iv_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            getImageView();

            }
        });
    }



    private PointF controlStart,controlEnd ;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        resetX = iv_test.getX();
        resetY = iv_test.getY();

        screenWidth = content.getWidth();
        screenHeight = content.getHeight();

        LogUtil.e("screenWidth "+screenWidth);

        controlStart =  new PointF(resetX,resetY);
        controlEnd =  new PointF(resetX,0);
    }


    public ImageView getImageView() {
        ImageView mImageView = new ImageView(L17.this);
        mImageView.setLayoutParams(lp);
        mImageView.setX(resetX);
        mImageView.setY(resetY);
        mImageView.setImageDrawable(drawables[random.nextInt(drawables.length-1)]);
        content.addView(mImageView);
        addAnimat(mImageView);
        return mImageView;
    }

    private void addAnimat(final ImageView mImageView){

        AnimatorSet setBefore = getBeforeAni(mImageView);

        //再播放透明度，位移动画集合
        AnimatorSet setAfter = new AnimatorSet();
        setAfter.setDuration(1000);
        //透明度动画
        ObjectAnimator alphaAni = ObjectAnimator.ofFloat(mImageView,"Alpha",1f,0f);
        //贝塞尔位移动画
        ValueAnimator oa1 = ValueAnimator.ofObject(new BezierEvaluator(getControlLeft(),getControlRight()),controlStart,controlEnd);
        oa1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pf = (PointF) animation.getAnimatedValue();
                mImageView.setX(pf.x);
                mImageView.setY(pf.y);
            }
        });
        setAfter.playTogether(alphaAni,oa1);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(setBefore,setAfter);
//        set.play(setAfter).after(setBefore);
//        set.playSequentially(setBefore,setAfter);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();


    }

    public PointF getControlLeft() {
        PointF pf = new PointF(0,random.nextInt(screenHeight));
        return pf;
    }
    public PointF getControlRight() {
        PointF pf = new PointF(screenWidth,random.nextInt(screenHeight));
        return pf;
    }


    private AnimatorSet getBeforeAni(ImageView mImageView){
        //先播放缩放动画集合
        AnimatorSet setBefore = new AnimatorSet();
        int i = random.nextInt(6);
        switch (i){
            case 1 :
                setBefore.setDuration(400);
                ObjectAnimator scaleXAni = ObjectAnimator.ofFloat(mImageView,"ScaleX",0f,1f);
                ObjectAnimator scaleYAni = ObjectAnimator.ofFloat(mImageView,"ScaleY",0f,1f);
                setBefore.playTogether(scaleXAni,scaleYAni);
                break;
            case 2 :
                setBefore.setDuration(500);
                ObjectAnimator scaleXAni2 = ObjectAnimator.ofFloat(mImageView,"ScaleX",0f,1f);
                setBefore.play(scaleXAni2);
                break;
            case 3 :
                setBefore.setDuration(500);
                ObjectAnimator scaleXAni3 = ObjectAnimator.ofFloat(mImageView,"ScaleY",0f,1f);
                setBefore.play(scaleXAni3);
                break;
            case 4 :
                setBefore.setDuration(1000);
                ObjectAnimator scaleXAni4 = ObjectAnimator.ofFloat(mImageView,"Rotation",0,360f);
                setBefore.play(scaleXAni4);
                break;
            case 5 :
                setBefore.setDuration(1000);
                ObjectAnimator scaleXAni5 = ObjectAnimator.ofInt(mImageView,"ColorFilter", Color.RED,Color.BLUE);
                setBefore.play(scaleXAni5);
                break;
        }

        return  setBefore ;
    }

}
