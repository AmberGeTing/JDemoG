package bwie.com.jdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bwie.com.jdemo.R;
import bwie.com.jdemo.adapter.PinDanAdapter;
import bwie.com.jdemo.adapter.PingAdapter;
import bwie.com.jdemo.adapter.TuiJianAdapter;
import bwie.com.jdemo.bean.PingJiaBean;
import bwie.com.jdemo.bean.TuiJianBean;
import bwie.com.jdemo.bean.XinXiangBean;
import bwie.com.jdemo.presenter.PingJiaPresenter;
import bwie.com.jdemo.presenter.TuiJianPresenter;
import bwie.com.jdemo.presenter.XinXiangPresenter;

public class XinXIangQingActivity extends AppCompatActivity implements IXinXiang{

    private SimpleDraweeView mXinXImg;
    private TextView mXinXTi;
    private TextView mXinXTv;
    private LinearLayout mXinMore;
    private RecyclerView mPindanRv;
    private LinearLayout mXinPingMore;
    private RecyclerView mPingRv;
    private SimpleDraweeView mDianpuimg;
    private TextView mDianpuname;
    private LinearLayout mJindian;
    private RecyclerView mDianputuijian;
    private TextView mXiangqingTv;
    private SimpleDraweeView mXiangqingImg;
    private XinXiangPresenter xinXiangPresenter;
    private PingJiaPresenter pingJiaPresenter;
    private TuiJianPresenter tuiJianPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xin_xiang_qing);
        xinXiangPresenter = new XinXiangPresenter(this);
        pingJiaPresenter = new PingJiaPresenter(this);
        tuiJianPresenter = new TuiJianPresenter(this);
        xinXiangPresenter.login();
        pingJiaPresenter.login();
        tuiJianPresenter.login();
        initView();
        mPingRv.setLayoutManager(new LinearLayoutManager(this));
        //点击查看拼单更多
        mXinMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //点击近点逛逛
        mJindian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void initView() {
        mXinXImg = (SimpleDraweeView) findViewById(R.id.xin_x_img);
        mXinXTi = (TextView) findViewById(R.id.xin_x_ti);
        mXinXTv = (TextView) findViewById(R.id.xin_x_tv);
        mXinMore = (LinearLayout) findViewById(R.id.xin_more);
        mPindanRv = (RecyclerView) findViewById(R.id.pindan_rv);
        mXinPingMore = (LinearLayout) findViewById(R.id.xin_ping_more);
        mPingRv = (RecyclerView) findViewById(R.id.ping_rv);
        mDianpuimg = (SimpleDraweeView) findViewById(R.id.dianpuimg);
        mDianpuname = (TextView) findViewById(R.id.dianpuname);
        mJindian = (LinearLayout) findViewById(R.id.jindian);
        mDianputuijian = (RecyclerView) findViewById(R.id.dianputuijian);
        mXiangqingTv = (TextView) findViewById(R.id.xiangqing_tv);
        mXiangqingImg = (SimpleDraweeView) findViewById(R.id.xiangqing_img);
    }

    @Override
    public String getpdduid() {
        //获取值
        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");
        return pid;
    }

    @Override
    public void show(XinXiangBean xinXiangBean) {
        //获取数据展示
        DraweeController controller6 = Fresco.newDraweeControllerBuilder()
                .setUri(xinXiangBean.getImage_url())
                .setAutoPlayAnimations(true)
                .build();
        mXinXImg.setController(controller6);
        mXinXTi.setText(xinXiangBean.getGoods_name());
        mXinXTv.setText(xinXiangBean.getGoods_desc());
        //设置下面拼单的显示
        List<XinXiangBean.SkuBean> list = xinXiangBean.getSku();
         //创建适配器
        PinDanAdapter pinDanAdapter = new PinDanAdapter(list, this);
        //设置
        mPindanRv.setLayoutManager(new LinearLayoutManager(this));
        mPindanRv.setAdapter(pinDanAdapter);
        //设置店铺图片
        DraweeController controller1 = Fresco.newDraweeControllerBuilder()
                .setUri(xinXiangBean.getImage_url())
                .setAutoPlayAnimations(true)
                .build();
        mDianpuimg.setController(controller1);
        mDianpuname.setText(xinXiangBean.getCountry());
        //设置商品详情
        mXiangqingTv.setText(xinXiangBean.getGoods_desc());
        DraweeController controller2 = Fresco.newDraweeControllerBuilder()
                .setUri(xinXiangBean.getHd_thumb_url())
                .setAutoPlayAnimations(true)
                .build();
        mXiangqingImg.setController(controller2);
    }

    @Override
    public void showping(PingJiaBean pingJiaBean) {
         //展示评价的列表
        List<PingJiaBean.DataBean> data = pingJiaBean.getData();
        //创建适配器

        PingAdapter pingAdapter = new PingAdapter(data, this);
        mPingRv.setAdapter(pingAdapter);
    }

    @Override
    public void showTui(TuiJianBean tuiJianBean) {
         //获取数据
        List<TuiJianBean.ListBean> list = tuiJianBean.getList();
        mDianputuijian.setLayoutManager(new GridLayoutManager(this,3));
        TuiJianAdapter tuiJianAdapter = new TuiJianAdapter(list, this);
        mDianputuijian.setAdapter(tuiJianAdapter);
    }
}
