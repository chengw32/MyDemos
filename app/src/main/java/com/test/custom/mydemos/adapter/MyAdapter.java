package com.test.custom.mydemos.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.test.custom.mydemos.R;
import com.test.custom.mydemos.utils.DialogManager;
import com.test.custom.mydemos.utils.LogUtil;

/**
 * Author 陈国武
 * Time 2017/7/24.
 * Des:
 */

public class MyAdapter extends BaseAdapter {

    private String[] data ;
    private Activity mContext ;
    private String packPath ;


    public MyAdapter(Activity context, String[] data, String packPath){
        this.mContext = context ;
        this.data = data ;
        this.packPath = packPath ;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_layout, null);
        inflate.setTag(data[position]);
        TextView viewById = (TextView) inflate.findViewById(R.id.title);
        viewById.setText(data[position]);
        inflate.setOnClickListener(myOnclickListener);
        return inflate;
    }

    private View.OnClickListener  myOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String tag = (String) v.getTag();
            LogUtil.e("tag   "+tag);
            int i = tag.indexOf("-");
            String actName = tag.substring(0, i);
            String act = packPath + actName + "."+ actName;
            LogUtil.e("act   "+act);


            Class tt = null;
            try {
                tt = Class.forName(act);
                mContext.startActivity(new Intent(mContext,tt));
                mContext.overridePendingTransition(R.anim.in,0);
            } catch (ClassNotFoundException e) {
                //没有对应的 Activity
                int position = act.indexOf(".",23);
                int position2 = act.indexOf(".",24);
                //模块名字
                String molName = act.substring(position + 1, position2);
                //课程名字
                String path = molName +"/"+actName+".txt";
                LogUtil.e("path ---- " + path);

                DialogManager.getIns().showDialog(mContext,path);

            }

//            mContext.startActivity(new Intent(mContext, ClassUtils.getLocalClass(act)));
//            mContext.overridePendingTransition(R.anim.in,0);

        }
    } ;

}
