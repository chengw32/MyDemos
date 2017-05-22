package com.test.custom.mydemos;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.test.custom.mydemos.L4.L4;
import com.test.custom.mydemos.roundprogress.RoundProgressActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    List<String> demos = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(MainActivity.this, RoundProgressActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, L4.class));
                        break;
                }
            }
        });

        demos.add("L3-画笔基本使用_圆形进度条_画小黄人");
        demos.add("L4-各种渲染-shader-圆形头像-");


        listView.setAdapter(new MyAdapter());
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
