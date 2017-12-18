package bwie.com.jdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bwie.com.jdemo.R;
import bwie.com.jdemo.bean.Bean;
import bwie.com.jdemo.bean.CartBean;
import bwie.com.jdemo.bean.FenBean;
import bwie.com.jdemo.bean.GoodsBean;
import bwie.com.jdemo.bean.XiangBean;
import bwie.com.jdemo.bean.XinBean;
import bwie.com.jdemo.presenter.AddGoodsPresenter;
import bwie.com.jdemo.presenter.XiangPresenter;

public class XaingQingActivity extends AppCompatActivity implements IMainActivity, View.OnClickListener {

    private AddGoodsPresenter addGoodsPresenter;
    /**
     * 加入购物车
     */

    private XiangPresenter xiangPresenter;


    /**
     * 购物车
     */
    private TextView mCpCart;
    /**
     * 加入购物车
     */
    private TextView mCpAddcart;
    private ImageView mCpBack;
    private SimpleDraweeView mCpImg;
    private TextView mCpTitle;
    private TextView mCpPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xaing_qing);
        xiangPresenter = new XiangPresenter(this);
        addGoodsPresenter = new AddGoodsPresenter(this);
        initView();
        //获取pid


        xiangPresenter.login();
        addGoodsPresenter.login();
        //设置点击事件--跳转到购物车
        mCpBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回上一个界面
                XaingQingActivity.this.finish();
            }
        });
    }

    private void initView() {
        mCpBack = (ImageView) findViewById(R.id.cp_back);
        mCpImg = (SimpleDraweeView) findViewById(R.id.cp_img);
        mCpTitle = (TextView) findViewById(R.id.cp_title);
        mCpPrice = (TextView) findViewById(R.id.cp_price);
        mCpCart = (TextView) findViewById(R.id.cp_cart);
        mCpAddcart = (TextView) findViewById(R.id.cp_addcart);
        mCpCart.setOnClickListener(this);
        mCpAddcart.setOnClickListener(this);
    }

    //别的数据
    @Override
    public void show(Bean bean) {

    }

    @Override
    public void show2(XiangBean sbean) {
        XiangBean.DataBean data = sbean.getData();

        //设置图片
        String images = data.getImages();
        String[] split = images.split("\\|");
        String s = split[0];
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(s)
                .setAutoPlayAnimations(true)
                .build();
        mCpImg.setController(controller);
        mCpTitle.setText(data.getTitle());
        mCpPrice.setText(data.getPrice() + "");
    }

    @Override
    public void show3(final GoodsBean gbean) {
        final String code = gbean.getCode();
        //传值
        mCpAddcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否添加成功
                if ("0".equals(code)) {
                    Toast.makeText(XaingQingActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public String getPid() {
        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");
        Log.e("TAG",pid+"****************");
        return pid;
    }

    @Override
    public String getUid() {
        return "71";
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

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.cp_cart:
                break;
            case R.id.cp_addcart:
                break;
        }
    }
}
