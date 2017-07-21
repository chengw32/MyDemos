package com.test.custom.mydemos.L16;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PointF;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.test.custom.mydemos.BaseActivity;
import com.test.custom.mydemos.R;
import com.test.custom.mydemos.utils.LogUtil;

public class L16 extends BaseActivity {

    ImageView iv ;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_l16);

        iv = (ImageView) findViewById(R.id.test);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                objectAnimator();
//                paoWuXian();
                tanTanTan();
            }
        });


    }

    private void objectAnimator() {

//        Path path = new Path();
//        path.addCircle(100,100 ,100 , Path.Direction.CW);
//        ObjectAnimator oba = ObjectAnimator.ofMultiInt(iv, "translationX", path);
        ObjectAnimator oba = ObjectAnimator.ofFloat(iv,"translationX",0f,200f);
        oba.setDuration(500);
        oba.start();

        //自由落体位移公式 h=gt*t/2 g=9.8


    }


    private void paoWuXian(){
                LogUtil.e("paoWuXian-----"+"paoWuXian");
        ValueAnimator va = new ValueAnimator();
        va.setDuration(5000);
        final PointF pf = new PointF();
        va.setObjectValues(new PointF(0,100));
        va.setRepeatCount(3);
        va.setRepeatMode(ValueAnimator.RESTART);
        va.setEvaluator(new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                LogUtil.e("fraction-----"+fraction);
                pf.x = 100f * fraction *5 ;
                pf.y = 0.5f * 9.8f * fraction * fraction  * 25 ;
                return endValue;
            }
        });
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pf = (PointF) animation.getAnimatedValue();
                iv.setX(pf.x);
                iv.setY(pf.y);
            }
        });
        va.start();
    }

    //类似自由落体 弹跳效果
    private void tanTanTan(){
        ObjectAnimator oa = ObjectAnimator.ofFloat(iv,"translationY",0f,400f);
        oa.setDuration(2000);
        Path pt = new Path();
        pt.moveTo(0f,0f);
        pt.lineTo(0.5f,1f);
        pt.lineTo(1f,1f);
        oa.setInterpolator(new AccelerateDecelerateInterpolator());
        oa.start();

    }

}
