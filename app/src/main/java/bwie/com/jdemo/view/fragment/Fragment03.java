package bwie.com.jdemo.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bwie.com.jdemo.R;
import bwie.com.jdemo.adapter.XinAdapter;
import bwie.com.jdemo.bean.Bean;
import bwie.com.jdemo.bean.CartBean;
import bwie.com.jdemo.bean.FenBean;
import bwie.com.jdemo.bean.GoodsBean;
import bwie.com.jdemo.bean.XiangBean;
import bwie.com.jdemo.bean.XinBean;
import bwie.com.jdemo.comm.OnClick;
import bwie.com.jdemo.presenter.XinPresenter;
import bwie.com.jdemo.view.IMainActivity;
import bwie.com.jdemo.view.XaingQingActivity;
import bwie.com.jdemo.view.XinXIangQingActivity;

/**
 * 新品的界面
 * Created by ASUS on 2017/12/4.
 */

public class Fragment03 extends Fragment implements IMainActivity,OnClick{
    private View view;
    private ImageView mFenxiang;
    private TextView mXinLeft;
    private TextView mXinCenter;
    private SimpleDraweeView mTou1;
    private SimpleDraweeView mTou2;
    private SimpleDraweeView mTou3;
    private TextView mXinRight;
    private TextView mXinRightCenter;
    private SimpleDraweeView mTou4;
    private SimpleDraweeView mTou5;
    private RecyclerView mXinLv;
    private XinPresenter xinPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment03, container, false);
        xinPresenter = new XinPresenter(this);
        xinPresenter.login();
        initView(view);
        return view;
    }

    private void initView(View view) {
        mFenxiang = (ImageView) view.findViewById(R.id.fenxiang);
        mXinLeft = (TextView) view.findViewById(R.id.xin_left);
        mXinCenter = (TextView) view.findViewById(R.id.xin_center);
        mTou1 = (SimpleDraweeView) view.findViewById(R.id.tou1);
        mTou2 = (SimpleDraweeView) view.findViewById(R.id.tou2);
        mTou3 = (SimpleDraweeView) view.findViewById(R.id.tou3);
        mXinRight = (TextView) view.findViewById(R.id.xin_right);
        mXinRightCenter = (TextView) view.findViewById(R.id.xin_right_center);
        mTou4 = (SimpleDraweeView) view.findViewById(R.id.tou4);
        mTou5 = (SimpleDraweeView) view.findViewById(R.id.tou5);
        mXinLv = (RecyclerView) view.findViewById(R.id.xin_lv);
    }

    @Override
    public void show(Bean bean) {

    }

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
          //获取数据进行显示
        List<XinBean.GoodsListBean> list = xinBean.getGoods_list();
        XinBean.GoodsListBean bean = list.get(1);
        Log.e("TAG",list.get(0).getGoods_name()+"===========");
        //先设置上面头部的内容
        mXinLeft.setText("邻里团");
        mXinRight.setText("精选专题");
      //设置头像
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(bean.getHd_thumb_url())
                .setAutoPlayAnimations(true)
                .build();
        mTou1.setController(controller);
        //设置头像
        XinBean.GoodsListBean bean1 = list.get(2);
        DraweeController controller1 = Fresco.newDraweeControllerBuilder()
                .setUri(bean1.getHd_thumb_url())
                .setAutoPlayAnimations(true)
                .build();
        mTou1.setController(controller);
        //设置头像
        XinBean.GoodsListBean bean2 = list.get(3);
        DraweeController controller2 = Fresco.newDraweeControllerBuilder()
                .setUri(bean2.getHd_thumb_url())
                .setAutoPlayAnimations(true)
                .build();
        mTou2.setController(controller2);
        //设置头像
        XinBean.GoodsListBean bean3 = list.get(4);
        DraweeController controller3 = Fresco.newDraweeControllerBuilder()
                .setUri(bean3.getHd_thumb_url())
                .setAutoPlayAnimations(true)
                .build();
        mTou3.setController(controller3);
        //设置头像
        XinBean.GoodsListBean bean4 = list.get(0);
        DraweeController controller4 = Fresco.newDraweeControllerBuilder()
                .setUri(bean4.getHd_thumb_url())
                .setAutoPlayAnimations(true)
                .build();
        mTou4.setController(controller4);
        //设置头像
        XinBean.GoodsListBean bean5 = list.get(1);
        DraweeController controller5 = Fresco.newDraweeControllerBuilder()
                .setUri(bean5.getHd_thumb_url())
                .setAutoPlayAnimations(true)
                .build();
        mTou5.setController(controller5);
        mXinLv.setLayoutManager(new GridLayoutManager(getContext(),2));
        //创建适配器
        XinAdapter xinAdapter = new XinAdapter(list, getContext(),this);
        mXinLv.setAdapter(xinAdapter);
    }


    @Override
    public void onClick(String position) {
        Intent intent = new Intent(getActivity(), XinXIangQingActivity.class);
        intent.putExtra("pid", position);
        startActivity(intent);
    }
}
