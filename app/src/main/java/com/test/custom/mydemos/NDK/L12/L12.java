package com.test.custom.mydemos.NDK.L12;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.test.custom.mydemos.R;
import com.test.custom.mydemos.base.BaseActivity;
import com.test.custom.mydemos.utils.LogUtil;

public class L12 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void bzip(View v){

        new ApkUpdateTask().execute();

    }

    class ApkUpdateTask extends AsyncTask<Void, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Void... params) {



//            String oldfile = ApkUtils.getSourceApkPath(MainActivity.this, getPackageName());

            LogUtil.e("开始合并");


            String newPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/apkNew.apk";
            String oldPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/apkOld.apk";
            String difPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/test.patch";


            int isSuccess =  Zip.zip(oldPath,newPath,difPath);

            LogUtil.e("isSuccess    "+isSuccess);

            if (isSuccess == 0) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                String apkPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/apkNew.apk";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("file://" + apkPath),
                        "application/vnd.android.package-archive");

                startActivity(intent);
            }
        }
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_l12);
    }
}
