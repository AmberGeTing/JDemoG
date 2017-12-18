package bwie.com.jdemo.view;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hjm.bottomtabbar.BottomTabBar;

import bwie.com.jdemo.R;
import bwie.com.jdemo.view.fragment.Fragment01;
import bwie.com.jdemo.view.fragment.Fragment02;
import bwie.com.jdemo.view.fragment.Fragment03;
import bwie.com.jdemo.view.fragment.Fragment04;
import bwie.com.jdemo.view.fragment.Fragment05;

public class MainActivity extends AppCompatActivity {
    private BottomTabBar mb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mb=(BottomTabBar)findViewById(R.id.bottom_tab_bar);

        mb.init(getSupportFragmentManager())
                .setImgSize(100,100)
                .setFontSize(0)
                .setTabPadding(4,6,10)
                .setChangeColor(Color.RED,Color.DKGRAY)
                .addTabItem("",R.drawable.ac0, Fragment01.class)
                .addTabItem("",R.drawable.abw, Fragment02.class)
                .addTabItem("",R.drawable.aby, Fragment03.class)
                .addTabItem("",R.drawable.abu, Fragment04.class)
                .addTabItem("",R.drawable.ac2, Fragment05.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });
    }
}
