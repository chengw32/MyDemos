package com.test.custom.mydemos.Ui.L20;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.test.custom.mydemos.utils.LogUtil;

/**
 * Author 陈国武
 * Time 2017/8/4.
 * Des:
 */

public class MyPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View view, float position) {
        LogUtil.e("position"+position);
        //效果1
//			view.setScaleX(1-Math.abs(position));
//			view.setScaleY(1-Math.abs(position));
        //效果2
//			view.setScaleX(Math.max(0.9f,1-Math.abs(position)));
//			view.setScaleY(Math.max(0.9f,1-Math.abs(position)));
        //效果3 3D翻转
			view.setPivotX(position<0f?view.getWidth():0f);//左边页面：0~-1；右边的页面：1~0
			view.setPivotY(view.getHeight()*0.5f);
			view.setRotationY(position*45f);//0~90度
        //效果4 3D内翻转
//			view.setPivotX(position<0f?view.getWidth():0f);//左边页面：0~-1；右边的页面：1~0
//			view.setPivotY(view.getHeight()*0.5f);
//			view.setRotationY(-position*45f);//0~90度

//			view.setPivotX(view.getWidth()*0.5f);//左边页面：0~-1；右边的页面：1~0
//			view.setPivotY(view.getHeight()*0.5f);
//			view.setRotationY(-position*45f);//0~90度

    }
}
