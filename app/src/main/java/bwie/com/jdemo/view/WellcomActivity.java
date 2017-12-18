package bwie.com.jdemo.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import bwie.com.jdemo.R;

public class WellcomActivity extends AppCompatActivity {
     private int i=0;
     private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcom);
        //设置五秒跳转
        Timer timer  = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    i++;
                    if(i==3){
                        //跳转
                        Intent intent = new Intent(WellcomActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },1000,1000);
    }
}
