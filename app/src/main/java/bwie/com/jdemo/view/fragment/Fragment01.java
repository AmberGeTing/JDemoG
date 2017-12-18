package bwie.com.jdemo.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gongwen.marqueen.MarqueeFactory;
import com.gongwen.marqueen.SimpleMF;
import com.gongwen.marqueen.SimpleMarqueeView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import bwie.com.jdemo.R;
import bwie.com.jdemo.adapter.BannerAdapter;
import bwie.com.jdemo.adapter.DaoAdapter;
import bwie.com.jdemo.adapter.MainAdapter;
import bwie.com.jdemo.adapter.MiAOAdapter;
import bwie.com.jdemo.bean.Bean;
import bwie.com.jdemo.bean.CartBean;
import bwie.com.jdemo.bean.DaoSession;
import bwie.com.jdemo.bean.GoodsBean;
import bwie.com.jdemo.bean.XiangBean;
import bwie.com.jdemo.bean.XinBean;
import bwie.com.jdemo.comm.OnClick;
import bwie.com.jdemo.presenter.FristPresenter;
import bwie.com.jdemo.presenter.XinPresenter;
import bwie.com.jdemo.utils.GreenDao;
import bwie.com.jdemo.view.DaoMainActivity;
import bwie.com.jdemo.view.IMainActivity;
import bwie.com.jdemo.view.LoginActivity;
import bwie.com.jdemo.view.MiaoShaActivity;
import bwie.com.jdemo.view.MipcaActivityCapture;
import bwie.com.jdemo.view.SouSuoActivity;
import bwie.com.jdemo.view.WebViewActivity;
import bwie.com.jdemo.view.XaingQingActivity;
import bwie.com.jdemo.view.XiaoXiActivity;
import bwie.com.jdemo.view.ZhiBoActivity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by ASUS on 2017/12/4.
 */

public class Fragment01 extends Fragment implements IMainActivity, OnClick {
    private FristPresenter fristPresenter;
    private List<Bean.DataBean> list;
    private List<String> lunList = new ArrayList<>();
    private View view;
    private ImageView mSao;
    private LinearLayoutManager layoutManager;
    /**
     * 请输入搜索的内容
     */
    private EditText mSou;
    private ImageView mXiao;
    private RecyclerView mLun;
    private RecyclerView mDao;
    private SimpleDraweeView mShowImg;
    private RecyclerView mMain;
    private SharedPreferences sp;
    /**
     * 02
     */
    private TextView mTvHour;
    /**
     * 15
     */
    private TextView mTvMinute;
    /**
     * 36
     */
    private TextView mTvSecond;
    private LinearLayout mLlXsqg;
    private RecyclerView mMiaosha;
    private ImageView mHao;
    private ImageView mHao2;
    private long mHour = 02;
    private long mMin = 15;
    private long mSecond = 36;
    private boolean isRun = true;
    private XinPresenter xinPresenter;
    private final static int SCANNIN_GREQUEST_CODE = 1;
    private String state = "";
    private GreenDao greenDao;
    private DaoSession daoSession;
    private ImageView zhibo;
    private TextView sousuo;
    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                computeTime();
                if (mHour < 10) {
                    mTvHour.setText("0" + mHour + "");
                } else {
                    mTvHour.setText("0" + mHour + "");
                }
                if (mMin < 10) {
                    mTvMinute.setText("0" + mMin + "");
                } else {
                    mTvMinute.setText(mMin + "");
                }
                if (mSecond < 10) {
                    mTvSecond.setText("0" + mSecond + "");
                } else {
                    mTvSecond.setText(mSecond + "");
                }
            }
        }
    };
    private SimpleMarqueeView mSimpleMarqueeView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getContext());
        fristPresenter = new FristPresenter(this);
        fristPresenter.login();
        xinPresenter = new XinPresenter(this);
        xinPresenter.login();
        startRun();
        //在创建视图的时候,先清空数据库
        SharedPreferences sp = getContext().getSharedPreferences("loginUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
        new DisplayImageOptions.Builder()
                .showImageForEmptyUri(null)
                .showImageOnFail(null)
                .showImageOnFail(null).build();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment01, container, false);
        initView(view);
        zhibo = view.findViewById(R.id.zhibo);
        sousuo = view.findViewById(R.id.sousuo);
        //设置二维码扫描
        //点击二维码的框,扫描二维码
        mSao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //扫描二维码
                Intent intent = new Intent(getActivity(), MipcaActivityCapture.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, 1);
            }
        });
        //点击直播的时候--跳转到直播的界面
          zhibo.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(getActivity(), ZhiBoActivity.class);
                  startActivity(intent);
              }
          });

          //点击搜索--进行搜索
        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //搜索--跳转到展示内容的界面
               //先判断输入的内容是否为空
                String s = mSou.getText().toString().trim();
                if(TextUtils.isEmpty(s)){
                    Toast.makeText(getContext(),"搜索内容不能为空!!!", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getActivity(), SouSuoActivity.class);
                    intent.putExtra("keywords",s);
                    intent.putExtra("page","1");
                    startActivity(intent);
                }

            }
        });

        //点击消息的时候
        mXiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断用户是否登录--如果登录--跳转到消息界面
                //如果没有登录--跳转到登陆界面
                //查询数据库
                SharedPreferences sp = getContext().getSharedPreferences("loginUser", Context.MODE_PRIVATE); //取得user_id和手机号 numbers = sp.getString("user_mobile", "");//如果取不到值就取后面的""
                state = sp.getString("state", "0");
                //判断
                if ("登录".equals(state)) {
                    //跳转到消息界面
                    Intent intent2 = new Intent(getActivity(), XiaoXiActivity.class);
                    startActivity(intent2);
                } else {
                    //跳转到登录界面
                    Intent intent3 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent3);
                }
            }
        });

        //设置跑马灯

        final List<String> datas = Arrays.asList("《北京烟火》", "忙忙碌碌的生活 我们像个陀螺", "拥挤的公交车总有梦想盛开着", "爱情不是童话里美丽的空中楼阁", "绚烂之后降落 柴米油盐的生活");
       /* List<Integer> img = new ArrayList<>();
        img.add(R.drawable.a);
        img.add(R.drawable.b);
        img.add(R.drawable.e);
        img.add(R.drawable.f);*/

        SimpleMF<String> marqueeFactory = new SimpleMF(getContext());
        marqueeFactory.setData(datas);
        mSimpleMarqueeView.setMarqueeFactory(marqueeFactory);
        mSimpleMarqueeView.startFlipping();
        //点击事件
        marqueeFactory.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
                Toast.makeText(getContext(), holder.data, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void show(Bean bean) {
        list = bean.getData();
        Log.e("TAG", list.size() + "===========");
        //设置轮播
        for (int i = 0; i < list.size(); i++) {
            Bean.DataBean dataBean = list.get(i);
            lunList.add(dataBean.getIcon());
        }
        BannerAdapter adapter = new BannerAdapter(getActivity(), lunList);
        //设置轮播
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mLun.setLayoutManager(layoutManager);
        mLun.setHasFixedSize(true);
        mLun.setAdapter(adapter);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mLun);
        mLun.scrollToPosition(list.size() * 10);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                mLun.smoothScrollToPosition(layoutManager.findFirstVisibleItemPosition() + 1);
            }
        }, 2000, 2000, TimeUnit.MILLISECONDS);


        //轮播下面的列表展示
        mDao.setLayoutManager(new GridLayoutManager(getContext(), 4));
        //创建适配器
        DaoAdapter daoAdapter = new DaoAdapter(list, getContext());
        //设置点击事件
        daoAdapter.setOnItemClickListener(new DaoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //跳转到指定的界面
                Intent intent = new Intent(getActivity(), DaoMainActivity.class);
                Bean.DataBean dataBean = list.get(position);
                String title = dataBean.getTitle();
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
        mDao.setAdapter(daoAdapter);
        Bean.MiaoshaBean miaosha = bean.getMiaosha();
        //秒杀的集合
        final List<Bean.MiaoshaBean.ListBeanX> miaoshaList = miaosha.getList();
        mMiaosha.setLayoutManager(new LinearLayoutManager(getContext()));

        MainAdapter mainAdapter = new MainAdapter(miaoshaList, getContext(), (OnClick) this);
        mMain.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mMain.setAdapter(mainAdapter);
    }

    /**
     * 开启倒计时
     */
    private void startRun() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 倒计时计算
     */
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
            }
        }
    }

    //扫描完之后回调
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SCANNIN_GREQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("result");
                Log.i("xxx", result.toString());
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url", result);
                startActivity(intent);
            }
        }
    }

    //别的数据
    @Override
    public void show2(XiangBean sbean) {

    }

    @Override
    public void show3(GoodsBean gbean) {

    }

    @Override
    public String getPid() {
        return null;
    }

    @Override
    public String getUid() {
        return null;
    }

    @Override
    public String getAccount() {
        return null;
    }

    @Override
    public String getPwd() {
        return null;
    }

    @Override
    public void show4(String str, String str2) {

    }

    @Override
    public void showList(List<CartBean.DataBean> groupList, List<List<CartBean.DataBean.ListBean>> childList) {

    }

    @Override
    public void showXin(XinBean xinBean) {
        //导航界面--秒杀倒计时下面的内容
        List<XinBean.GoodsListBean> lists = xinBean.getGoods_list();
        mMiaosha.setLayoutManager(new GridLayoutManager(getContext(), 3));
        MiAOAdapter miAOAdapter = new MiAOAdapter(lists, getContext());
        miAOAdapter.setOnItemClickListener(new MiAOAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //跳转到秒杀的界面
                Intent intent = new Intent(getContext(), MiaoShaActivity.class);
                intent.putExtra("pid", position);
                startActivity(intent);
            }
        });
        mMiaosha.setAdapter(miAOAdapter);
    }

    private void initView(View view) {
        mSao = (ImageView) view.findViewById(R.id.sao);
        mSou = (EditText) view.findViewById(R.id.sou);
        mXiao = (ImageView) view.findViewById(R.id.xiao);
        mLun = (RecyclerView) view.findViewById(R.id.lun);
        mDao = (RecyclerView) view.findViewById(R.id.dao);
        mMain = (RecyclerView) view.findViewById(R.id.main);
        mTvHour = (TextView) view.findViewById(R.id.tv_hour);
        mTvMinute = (TextView) view.findViewById(R.id.tv_minute);
        mTvSecond = (TextView) view.findViewById(R.id.tv_second);
        mLlXsqg = (LinearLayout) view.findViewById(R.id.ll_xsqg);
        mMiaosha = (RecyclerView) view.findViewById(R.id.miaosha);
        mHao = (ImageView) view.findViewById(R.id.hao);
        mHao2 = (ImageView) view.findViewById(R.id.hao2);


        mSimpleMarqueeView = (SimpleMarqueeView) view.findViewById(R.id.simpleMarqueeView);
    }


    @Override
    public void onClick(String position) {
        Intent intent = new Intent(getActivity(), XaingQingActivity.class);
        intent.putExtra("pid", position);
        startActivity(intent);
    }


}
