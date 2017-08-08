package com.test.custom.mydemos.Ui.L19;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Author 陈国武
 * Time 2017/7/27.
 * Des:
 */

@RequiresApi(api = Build.VERSION_CODES.M)
public class AnimatorScrollView extends ScrollView{
    public AnimatorScrollView(Context context) {
        this(context,null);
    }

    public AnimatorScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private LinearLayout content ;
    Rect mRect = new Rect();
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        for (int i = 0; i < content.getChildCount(); i++) {
            View childView = content.getChildAt(i);
            //判断是否包含动画容器
            if(!(childView instanceof AniExecute)){
                continue;
            }
            AnimatorContnet childAt = (AnimatorContnet) childView;
            childAt.getHitRect(mRect);
            boolean localVisibleRect = getLocalVisibleRect(mRect);
            if (localVisibleRect){
                int top = childView.getTop();
                int childViewheight = childView.getHeight();
                int scrollViewHeight = getHeight();
                int startAniHeight = top -scrollViewHeight ;
                if (t >= startAniHeight && t <= startAniHeight + childViewheight){
                    int detaHeight = t + scrollViewHeight - top;
                    float i1 = (float) detaHeight / (float) childViewheight;

                    childAt.startAni(i1);

                }else{
                    childAt.restoreAni();
                }
            }else {
                    childAt.restoreAni();
            }
        }

    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        content = (LinearLayout) getChildAt(0);
    }

    @Override
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

}
