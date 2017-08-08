package com.test.custom.mydemos.Ui.L20;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.test.custom.mydemos.R;

/**
 * Author 陈国武
 * Time 2017/8/4.
 * Des:
 */

public class MyFragment extends Fragment {

    private  ImageView image2;
    public static MyFragment newInstance(int imageId) {

        Bundle args = new Bundle();
        args.putInt("imageId",imageId);
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        View view = inflater.inflate(R.layout.activity_l20_item, null);
        ImageView image = (ImageView) view.findViewById(R.id.image);
        image2 = (ImageView) view.findViewById(R.id.image2);

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               rotate();
            }
        });
        int imageId = args.getInt("imageId");
        image.setImageResource(imageId);
        return view;
    }

    public void rotate(){
        ValueAnimator ani = ValueAnimator.ofFloat(0.0f,360f);
        ani.setDuration(500);
        ani.setRepeatCount(Integer.MAX_VALUE);
        ani.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float degree = (float) animation.getAnimatedValue();
                image2.setRotationY(degree);//0~90度
            }
        });
        ani.start();
    }

}
