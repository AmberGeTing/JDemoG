package bwie.com.jdemo.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import bwie.com.jdemo.R;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

public class DaoMainActivity extends AppCompatActivity {

    private ImageView mBack;
    private TextView mTitle;


    /**
     * 开始
     */
    private Button mStart;
    /**
     * 停止
     */
    private Button mStop;
    private RelativeLayout mLl;
    private DanmakuView mDanmakuView;
    private boolean showDanmaku;


    private DanmakuContext danmakuContext;
    private BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };
    private EditText mEditText;
    /**
     * Send
     */
    private Button mSend;
    private LinearLayout mOperationLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dao_main);
        initView();

        //获取值
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        //设置值
        mTitle.setText(title);

        //设置视频
        mDanmakuView = (DanmakuView) findViewById(R.id.danmaku_view);
        mDanmakuView.enableDanmakuDrawingCache(true);
        mDanmakuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                showDanmaku = true;
                mDanmakuView.start();
                generateSomeDanmaku();
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });
        danmakuContext = DanmakuContext.create();
        mDanmakuView.prepare(parser, danmakuContext);
        //设置点击事件
        mDanmakuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mOperationLayout.getVisibility() == View.GONE) {
                    Toast.makeText(DaoMainActivity.this,"sdf",Toast.LENGTH_SHORT).show();
                    mOperationLayout.setVisibility(View.VISIBLE);
                } else {
                    mOperationLayout.setVisibility(View.GONE);
                }
            }
        });
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = mEditText.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    addDanmaku(content, true);
                    mEditText.setText("");
                }
            }
        });
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener (new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == View.SYSTEM_UI_FLAG_VISIBLE) {
                    onWindowFocusChanged(true);
                }
            }
        });
    }


    /**
     * 向弹幕View中添加一条弹幕
     *
     * @param content    弹幕的具体内容
     * @param withBorder 弹幕是否有边框
     */
    private void addDanmaku(String content, boolean withBorder) {
        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        danmaku.text = content;
        //danmaku.padding = 10;
        danmaku.textSize = sp2px(20);
        danmaku.textColor = Color.BLACK;
        danmaku.setTime(mDanmakuView.getCurrentTime());
        if (withBorder) {
            danmaku.borderColor = Color.GREEN;
        }
        mDanmakuView.addDanmaku(danmaku);
    }

    /**
     * 随机生成一些弹幕内容以供测试
     */
    private void generateSomeDanmaku() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (showDanmaku) {
                    int time = new Random().nextInt(300);
                    String content = "" + time + time;
                    addDanmaku(content, false);
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * sp转px的方法。
     */
    public int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 1.5f);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mDanmakuView != null && mDanmakuView.isPrepared()) {
            mDanmakuView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()) {
            mDanmakuView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showDanmaku = false;
        if (mDanmakuView != null) {
            mDanmakuView.release();
            mDanmakuView = null;
        }
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mTitle = (TextView) findViewById(R.id.title);
        mDanmakuView = (DanmakuView) findViewById(R.id.danmaku_view);




        mEditText = (EditText) findViewById(R.id.edit_text);
        mSend = (Button) findViewById(R.id.send);
        mOperationLayout = (LinearLayout) findViewById(R.id.operation_layout);
    }


}
