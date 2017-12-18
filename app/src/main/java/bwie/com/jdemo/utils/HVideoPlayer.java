package bwie.com.jdemo.utils;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.Random;

import bwie.com.jdemo.R;
import fm.jiecao.jcvideoplayer_lib.JCUtils;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

public class HVideoPlayer extends JCVideoPlayerStandard {

    private Context mContext;
    private EditText mEditText;
    private ImageView mSendImage;
    private ProgressBar mProgress;
    private OnSendMsgListener mSendListener;
    private ImageView mRewardBtn;
    private OnPayListener mShowPay;
    private OnFullScreenListener mOnFullScreenListener;

    private boolean showDanmaku;

    //寮瑰箷View
    private DanmakuView danmakuView;

    private DanmakuContext danmakuContext;

    private BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };

    public HVideoPlayer(Context context) {
        super(context);
    }

    public HVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void init(final Context context) {
        super.init(context);
        this.mContext = context;
        mEditText = (EditText) findViewById(R.id.msg_edittext);
        mSendImage = (ImageView) findViewById(R.id.send_img);
        mRewardBtn = (ImageView) findViewById(R.id.reward_img);

        mSendImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mEditText.getText().toString();
                mSendListener.sendMsg(content);
            }
        });
        mRewardBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mShowPay.showPay();
            }
        });
    }

    @Override
    public void startDismissControlViewTimer() {
        super.startDismissControlViewTimer();
    }
    private void initDanmu() {
        ViewGroup vp = (ViewGroup) (JCUtils.scanForActivity(getContext()))//.getWindow().getDecorView();
                .findViewById(Window.ID_ANDROID_CONTENT);

        LayoutParams lp = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.setMargins(0, sp2px(48), 0, sp2px(48));
        danmakuView = new DanmakuView(mContext);
        vp.addView(danmakuView, lp);
        danmakuView.enableDanmakuDrawingCache(true);
        danmakuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                showDanmaku = true;
                danmakuView.start();
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
        danmakuView.prepare(parser, danmakuContext);

    }

    @Override
    public void onError(int what, int extra) {
        super.onError(what, extra);
         if (what != 38 && what != -38) {
        hideDanmu();
    }
}

    public void addDanmaku(String content, boolean withBorder) {
        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        if (danmaku == null || danmakuView == null) {
            return;
        }
        danmaku.text = content;
        danmaku.padding = 5;
        danmaku.textSize = sp2px(20);
        danmaku.textColor = Color.WHITE;
        danmaku.setTime(danmakuView.getCurrentTime());
        if (withBorder) {
            danmaku.borderColor = Color.GREEN;
        }
        danmakuView.addDanmaku(danmaku);
    }


    private void generateSomeDanmaku() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (showDanmaku) {
                    int time = new Random().nextInt(500);
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
    public int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    public void setOnSendMsgListener(OnSendMsgListener lis) {
        this.mSendListener = lis;
    }

    public void setOnPayListener(OnPayListener lis) {
        this.mShowPay = lis;
    }

    public void danmaResume() {
        if (danmakuView != null && danmakuView.isPrepared() && danmakuView.isPaused()) {
            danmakuView.resume();
        }
    }

    public void danmaDes() {
        showDanmaku = false;
        if (danmakuView != null) {
            danmakuView.release();
            danmakuView = null;
        }
    }
    @Override
    public void setUp(String url, int screen, Object... objects) {

        FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;

        if (objects.length != 1) {
            mSendListener = (OnSendMsgListener) objects[1];
            mShowPay = (OnPayListener) objects[2];
            mOnFullScreenListener = (OnFullScreenListener) objects[3];
        }
        super.setUp(url, screen, objects[0], mSendListener, mShowPay, mOnFullScreenListener);
         if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
        initDanmu();
        mEditText.setVisibility(View.VISIBLE);
        mSendImage.setVisibility(View.VISIBLE);
        mOnFullScreenListener.onFullScreen(this);
    } else if (currentScreen == SCREEN_LAYOUT_NORMAL
                || currentScreen == SCREEN_LAYOUT_LIST) {
        mEditText.setVisibility(View.INVISIBLE);
        mSendImage.setVisibility(View.INVISIBLE);
    }

    //鐐瑰嚮杩斿洖鎸夐挳闅愯棌寮瑰箷
        backButton.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            hideDanmu();
            backPress();
        }
    });


        fullscreenButton.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (currentState == CURRENT_STATE_AUTO_COMPLETE) return;
            if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
                hideDanmu();
                backPress();
            } else {
                startWindowFullscreen();
            }
        }
    });
}

    public boolean isFullScreen() {
        return currentScreen == SCREEN_WINDOW_FULLSCREEN?true:false;
    }
    public void hideDanmu() {
        ViewGroup vp = (ViewGroup) (JCUtils.scanForActivity(getContext()))//.getWindow().getDecorView();
                .findViewById(Window.ID_ANDROID_CONTENT);
        if (danmakuView != null) {
            danmakuView.release();
            showDanmaku = false;
            vp.removeView(danmakuView);
            danmakuView = null;
        }
    }

    @Override
    public void onVideoSizeChanged() {
//        super.onVideoSizeChanged();

    }

    public void setOnFullScreenListener(OnFullScreenListener onFullScreenListener) {
        this.mOnFullScreenListener = onFullScreenListener;
    }
    public interface OnSendMsgListener {
        void sendMsg(String msg);
    }
    public interface OnPayListener {
        void showPay();
    }

    public interface OnFullScreenListener {
        void onFullScreen(HVideoPlayer hVideoPlayer);
    }

    @Override
    public int getLayoutId() {
        return R.layout.custom_video_player;
    }
}
