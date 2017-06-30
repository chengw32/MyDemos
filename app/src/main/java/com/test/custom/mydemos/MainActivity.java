package com.test.custom.mydemos;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {




    /**
     * 命名规范：
     * 1 每节课都建一个文件夹 LXX (XX 是数字)
     * 2 每节课的 activity 都是 LXX 命名  同上
     * 3 demos 容器里面的内容 都是以 LXX- 开头,“-” 一定要加 因为要根据 “-”截取前面字符作为类名 用这个类名做跳转 所以 1 2 点命名都要一致
     * 4 每节课的 assets 文件夹下面都有 LXX 笔记 在详情页要读取用
     * */







    List<String> demos = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String s = demos.get(position);
                int i = s.indexOf("-");
                String substring = s.substring(0, i);
                String act = "com.test.custom.mydemos." + substring + "." + substring ;
                Class tt = null;
                try {
                    tt = Class.forName(act);
                    startAct(tt);
                } catch (ClassNotFoundException e) {
                    Toast.makeText(MainActivity.this,"ClassNotFoundException-----ClassName:"+act,Toast.LENGTH_SHORT).show();
                }
            }
        });

        demos.add("L3-画笔基本使用_圆形进度条_画小黄人");
        demos.add("L4-各种渲染-shader-圆形头像-BitmapShader-平铺模式-添加矩阵（矩阵旋转角度、缩放等操作）-shapeDrawable");
        demos.add("L5-xfermode的几种叠加模式-");
        demos.add("L6-MaskFilter");
        demos.add("L7-Canvas----Region 区域的叠加方式--- canvas 的平移、旋转、缩放、裁切操作----- saveLayer ---");
        demos.add("L8-实战案例—滑动图片变色(自定义 Drawable)-搜索框动画");
        demos.add("L9-贝塞尔曲线-案例 qq消息气泡拖拽销毁");
        demos.add("L10-PathMeasure-");


        listView.setAdapter(new MyAdapter());
    }

    private void startAct(Class act){
        Log.e("ClassName","-----"+act);
        startActivity(new Intent(MainActivity.this,act));
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return demos.size();
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
            View inflate = getLayoutInflater().inflate(R.layout.item_layout, null);
            TextView viewById = (TextView) inflate.findViewById(R.id.title);
            viewById.setText(demos.get(position));
            return inflate;
        }
    }

}
