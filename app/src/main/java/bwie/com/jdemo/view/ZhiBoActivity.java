package bwie.com.jdemo.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bwie.com.jdemo.R;
import bwie.com.jdemo.adapter.TableFragmentPagerAdapter;
import bwie.com.jdemo.utils.HVideoPlayer;
import bwie.com.jdemo.view.fragment.CommentFragment;
import bwie.com.jdemo.view.fragment.RewardFragment;
import fm.jiecao.jcvideoplayer_lib.JCUserAction;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class ZhiBoActivity extends AppCompatActivity {
    private HVideoPlayer mVideoPlayerStandard;
    private int mRoomId;
    private EditText mMsgEditText;
    private String mBeginTime;

    private TabLayout mTableLayout;
    private List<Fragment> mFragmentList =  new ArrayList<Fragment>();


    public HVideoPlayer mFullScreenPlayer;
    private TableFragmentPagerAdapter mViewPageadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_bo);
        initView();
    }
    public void initView(){
        mVideoPlayerStandard = (HVideoPlayer) findViewById(R.id.custom_videoplayer_standard);
        mMsgEditText = (EditText) findViewById(R.id.message);
        mTableLayout=findViewById(R.id.tabLayout);


        Button sendBtn = (Button) findViewById(R.id.sendMsg);
        mFragmentList.add(CommentFragment.newInstance());
        mFragmentList.add(RewardFragment.newInstance());
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPageadapter = new TableFragmentPagerAdapter(getSupportFragmentManager(),
                mFragmentList);
        viewPager.setAdapter(mViewPageadapter);
        mTableLayout.setupWithViewPager(viewPager);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msgContent = mMsgEditText.getText().toString();
                sendMessage(msgContent);
            }
        });

        mVideoPlayerStandard.setOnSendMsgListener(new HVideoPlayer.OnSendMsgListener() {
            @Override
            public void sendMsg(String msg) {
                sendMessage(msg);
            }
        });

        mVideoPlayerStandard.setOnPayListener(new HVideoPlayer.OnPayListener() {
            @Override
            public void showPay() {
                Toast.makeText(ZhiBoActivity.this,"本人直播间",Toast.LENGTH_SHORT).show();
            }
        });
        mVideoPlayerStandard.setOnFullScreenListener(new HVideoPlayer.OnFullScreenListener() {
            @Override
            public void onFullScreen(HVideoPlayer hVideoPlayer) {
                mFullScreenPlayer = hVideoPlayer;
            }
        });

        mVideoPlayerStandard.setJcUserAction(new JCUserAction() {
            @Override
            public void onEvent(int type, String url, int screen, Object... objects) {
                switch (type){
                    case JCVideoPlayer.CURRENT_STATE_PLAYING:

                        break;
                    case JCVideoPlayer.CURRENT_STATE_PAUSE:

                        break;

                }
            }
        });
        mVideoPlayerStandard.setUp("http://125.88.92.166:30001/PLTV/88888956/224/3221227695/1.m3u8"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "本人直播间");
    }

    private void sendMessage(String msgContent) {

        if (mFullScreenPlayer != null && mFullScreenPlayer.isFullScreen()) {
            mFullScreenPlayer.addDanmaku(msgContent, true);
        }
    }


    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            //闅愯棌寮瑰箷
            if (mFullScreenPlayer != null) {
                mFullScreenPlayer.hideDanmu();
                mFullScreenPlayer=null;
            }
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoPlayerStandard.danmaDes();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
        if (mFullScreenPlayer != null) {
            mFullScreenPlayer.hideDanmu();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoPlayerStandard.danmaResume();
    }
}

