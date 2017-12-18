package bwie.com.jdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bwie.com.jdemo.R;
import bwie.com.jdemo.bean.Bean;
import bwie.com.jdemo.bean.CartBean;
import bwie.com.jdemo.bean.GoodsBean;
import bwie.com.jdemo.bean.XiangBean;
import bwie.com.jdemo.bean.XinBean;
import bwie.com.jdemo.presenter.AddGoodsPresenter;
import bwie.com.jdemo.presenter.XiangPresenter;

public class SouSuoXiangActivity extends AppCompatActivity implements IMainActivity{

    private ImageView mCpBack;
    private SimpleDraweeView mCpImg;
    private TextView mCpTitle;
    private TextView mCpPrice;
    /**
     * 购物车
     */
    private TextView mCpCart;
    /**
     * 加入购物车
     */
    private TextView mCpAddcart;
    private XiangPresenter xiangPresenter;
    private AddGoodsPresenter addGoodsPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou_suo_xiang);
        xiangPresenter = new XiangPresenter(this);
        addGoodsPresenter = new AddGoodsPresenter(this);
        addGoodsPresenter.login();
        xiangPresenter.login();
        initView();

    }

    private void initView() {
        mCpBack = (ImageView) findViewById(R.id.cp_back);
        mCpImg = (SimpleDraweeView) findViewById(R.id.cp_img);
        mCpTitle = (TextView) findViewById(R.id.cp_title);
        mCpPrice = (TextView) findViewById(R.id.cp_price);
        mCpCart = (TextView) findViewById(R.id.cp_cart);
        mCpAddcart = (TextView) findViewById(R.id.cp_addcart);
    }

    @Override
    public void show(Bean bean) {

    }

    @Override
    public void show2(XiangBean sbean) {
         //创建适配器
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
    public void show3(GoodsBean gbean) {

    }

    @Override
    public String getPid() {
        Intent intent = getIntent();
        String pid = intent.getStringExtra("pids");
        Log.e("TAG",pid+"****************");
        return "10";
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
}
