package com.test.custom.mydemos.base;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.test.custom.mydemos.adapter.MyAdapter;

/**
 * Author 陈国武
 * Time 2017/7/24.
 * Des:
 */

public abstract class BaseListActivity extends ListActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listView = getListView();
        String[] uiLesson = getResources().getStringArray(getIdArrayValues());
        String className = getIntent().getComponent().getClassName();
        int lastIndex = className.lastIndexOf(".")+1;
        listView.setAdapter(new MyAdapter(BaseListActivity.this,uiLesson,className.substring(0,lastIndex)));

    }

    protected abstract int getIdArrayValues();
}
