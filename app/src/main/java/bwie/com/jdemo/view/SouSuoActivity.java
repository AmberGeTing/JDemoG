package bwie.com.jdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import bwie.com.jdemo.R;
import bwie.com.jdemo.adapter.SouSuoAdapter;
import bwie.com.jdemo.bean.SouSuoBean;
import bwie.com.jdemo.comm.ISouSuo;
import bwie.com.jdemo.presenter.SouSuoPresenter;

public class SouSuoActivity extends AppCompatActivity implements ISouSuo {
    private SouSuoPresenter souSuoPresenter;
    private String keywords;
    private String page;
    private RecyclerView mSouRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        souSuoPresenter = new SouSuoPresenter(this);

        setContentView(R.layout.activity_sou_suo);
        initView();
        Intent intent = getIntent();
        keywords = intent.getStringExtra("keywords");
        page = intent.getStringExtra("page");
        souSuoPresenter.login();
    }

    @Override
    public void show(SouSuoBean souSuoBean) {
        //展示数据
        List<SouSuoBean.DataBean> lists = souSuoBean.getData();
        mSouRv.setLayoutManager(new GridLayoutManager(this,2));
        //创建适配器
        SouSuoAdapter souSuoAdapter = new SouSuoAdapter(lists, this);
        mSouRv.setAdapter(souSuoAdapter);
        souSuoAdapter.setOnItemClickListener(new SouSuoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //跳转到详情页面
                Intent intent = new Intent(SouSuoActivity.this, SouSuoXiangActivity.class);
                intent.putExtra("pids", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public String keyWords() {
        return keywords;
    }

    @Override
    public String page() {
        return page;
    }

    private void initView() {
        mSouRv = (RecyclerView) findViewById(R.id.sou_rv);
    }
}
