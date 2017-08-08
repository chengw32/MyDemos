package com.test.custom.mydemos.Ui.L20;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.test.custom.mydemos.R;
import com.test.custom.mydemos.base.BaseActivity;

public class L20 extends BaseActivity {

    private int [] images = {R.mipmap.xiaowu,R.mipmap.xiaowu_black,R.mipmap.yihu,R.mipmap.logo,R.mipmap.heart1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewPager vp = (ViewPager) findViewById(R.id.vp);
        vp.setAdapter(new MyAdapter(getSupportFragmentManager()));
        vp.setPageTransformer(false,new MyPageTransformer());

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_l20);
    }

    class MyAdapter extends FragmentStatePagerAdapter{

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return MyFragment.newInstance(images[position]);
        }

        @Override
        public int getCount() {
            return images.length;
        }
    }
}
