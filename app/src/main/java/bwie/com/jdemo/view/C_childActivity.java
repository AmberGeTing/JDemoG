package bwie.com.jdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import bwie.com.jdemo.R;
import bwie.com.jdemo.adapter.ProducrtsAdapter;
import bwie.com.jdemo.bean.XiangChildBean;
import bwie.com.jdemo.presenter.XiangChildPresenter;
import bwie.com.jdemo.utils.OnListenerClickApi;

public class C_childActivity extends AppCompatActivity implements IXiangChild,OnListenerClickApi{

    private ImageView mPBack;
    private RecyclerView mPRv;
     private XiangChildPresenter xiangChildPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_child);
        Intent intent = getIntent();
        String pscid = intent.getStringExtra("pscid");
        xiangChildPresenter = new XiangChildPresenter(this);
        xiangChildPresenter.login(pscid,"1");
        initView();
        mPBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mPBack = (ImageView) findViewById(R.id.p_back);
        mPRv = (RecyclerView) findViewById(R.id.p_rv);
    }

    @Override
    public void show(XiangChildBean xiangChildBean) {
        List<XiangChildBean.DataBean> data = xiangChildBean.getData();
        mPRv.setLayoutManager(new GridLayoutManager(this,2));
        ProducrtsAdapter producrtsAdapter = new ProducrtsAdapter(C_childActivity.this,data,this);
        mPRv.setAdapter(producrtsAdapter);
    }


    @Override
    public void setOnClieck(String id) {

    }
}
