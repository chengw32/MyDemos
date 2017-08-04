package com.test.custom.mydemos.ui.L20;

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
        int imageId = args.getInt("imageId");
        image.setImageResource(imageId);
        return view;
    }
}
