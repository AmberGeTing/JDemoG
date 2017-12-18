package bwie.com.jdemo.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import bwie.com.jdemo.R;
import bwie.com.jdemo.adapter.FenAdapter;
import bwie.com.jdemo.adapter.FenRightAdapter;
import bwie.com.jdemo.bean.FenBean;
import bwie.com.jdemo.bean.FenChildBean;
import bwie.com.jdemo.presenter.FenChildPresenter;
import bwie.com.jdemo.presenter.FenPresenter;
import bwie.com.jdemo.utils.GlideImageLoader;
import bwie.com.jdemo.utils.OnClickApi;
import bwie.com.jdemo.view.IMain;

/**
 * Created by ASUS on 2017/12/4.
 */

public class Fragment02 extends Fragment implements OnClickApi, IMain {
    private View view;
    private RecyclerView mCRv;
    private RecyclerView mLun;
    private ExpandableListView mCExpand;
    private FenPresenter fenPresenter;
    private List<String> lunList = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private FenRightAdapter fenRightAdapter;
    private FenAdapter fenAdapter;
    private FenChildPresenter fenChildPresenter;
    private String cid = "1";
    private Banner mCBanner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment02, container, false);
        fenPresenter = new FenPresenter(this);
        fenChildPresenter = new FenChildPresenter(this);
        fenPresenter.login();
        fenChildPresenter.login(cid);
        initView(view);
        lunList.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=807055029,3341205176&fm=27&gp=0.jpg");
        lunList.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=466531044,4070525749&fm=27&gp=0.jpg");
        lunList.add("http://img1.imgtn.bdimg.com/it/u=980240160,1262175739&fm=27&gp=0.jpg");
        lunList.add("http://img4.imgtn.bdimg.com/it/u=2144844902,1660255202&fm=200&gp=0.jpg");
        mCBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mCBanner.setImages(lunList);
        //banner设置方法全部调用完毕时最后调用
        mCBanner.start();

        return view;
    }


    @Override
    public void showFen(FenBean xinBean) {
        //获取数据
        List<FenBean.DataBean> lists = xinBean.getData();
        //设置轮播
        for (int i = 0; i < lists.size(); i++) {
            FenBean.DataBean dataBean = lists.get(i);
            lunList.add(dataBean.getIcon());
        }

        //设置真是数据的适配器
        mCRv.setLayoutManager(new LinearLayoutManager(getContext()));
        fenAdapter = new FenAdapter(getContext(), lists, this);
        mCRv.setAdapter(fenAdapter);

    }

    private void initView(View view) {
        mCRv = (RecyclerView) view.findViewById(R.id.c_rv);

        mCExpand = (ExpandableListView) view.findViewById(R.id.c_expand);
        mCBanner = (Banner) view.findViewById(R.id.c_banner);
    }

    @Override
    public void setOnClickListener(String cid) {
        fenChildPresenter.login(cid);
        fenRightAdapter.notifyDataSetChanged();
    }

    @Override
    public void show(List<FenChildBean.DataBean> groupList, List<List<FenChildBean.DataBean.ListBean>> childlist) {
        //创建适配器
        fenRightAdapter = new FenRightAdapter(groupList, childlist, getContext());
        mCExpand.setAdapter(fenRightAdapter);
        mCExpand.setGroupIndicator(null);
        for (int i = 0; i < groupList.size(); i++) {
            mCExpand.expandGroup(i);
        }
    }

}
