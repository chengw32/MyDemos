package com.test.custom.mydemos.Ui.L3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.test.custom.mydemos.base.BaseActivity;
import com.test.custom.mydemos.R;

public class L3 extends BaseActivity {

    int progress = 0;

    @Override
    protected void setContentView() {
        setContentView(R.layout.round_progress);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final RoundProgress viewById = (RoundProgress) findViewById(R.id.pg);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progress <= 98){
                        viewById.setProgress(progress+=2);
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (progress >= 100){
                                int progressBgColor = viewById.getProgressBgColor();
                                if (Color.BLUE == progressBgColor){
                                    viewById.setProgressBgColor(Color.GREEN);
                                    viewById.setProgressColor(Color.BLUE);
                                }else {
                                    viewById.setProgressBgColor(Color.BLUE);
                                    viewById.setProgressColor(Color.GREEN);
                                }
                                progress = 0 ;
                            }
                        }
                    }
                }).start();
            }
        });

    }


}
