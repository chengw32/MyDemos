package com.test.custom.mydemos.ui.L19;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.test.custom.mydemos.R;

/**
 * Author 陈国武
 * Time 2017/7/28.
 * Des:
 */

public class AnimatorLinearLayout extends LinearLayout {
    public AnimatorLinearLayout(Context context) {
        this(context,null);
    }

    public AnimatorLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
       MyLayoutParams myLayoutParams = (MyLayoutParams) params;
        if (myLayoutParams.hasAlphaChange){
            AnimatorContnet contnet = new AnimatorContnet(getContext());
            contnet.setHasAlpha(myLayoutParams.hasAlphaChange);
            contnet.addView(child,params);
            addView(contnet);

        }else {
            super.addView(child, params);
        }

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MyLayoutParams(getContext(),attrs);
    }

    class MyLayoutParams extends  LayoutParams {
        boolean hasAlphaChange ;
        public MyLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);

            TypedArray a =  c.obtainStyledAttributes(attrs, R.styleable.MyAnimator);
            hasAlphaChange = a.getBoolean(R.styleable.MyAnimator_ani_alpha, false);
            a.recycle();
        }
    }

}
