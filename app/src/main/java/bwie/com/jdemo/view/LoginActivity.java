package bwie.com.jdemo.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bwie.com.jdemo.R;
import bwie.com.jdemo.bean.Bean;
import bwie.com.jdemo.bean.CartBean;
import bwie.com.jdemo.bean.DaoMaster;
import bwie.com.jdemo.bean.DaoSession;
import bwie.com.jdemo.bean.FenBean;
import bwie.com.jdemo.bean.GoodsBean;
import bwie.com.jdemo.bean.PingBean;
import bwie.com.jdemo.bean.UserBean;
import bwie.com.jdemo.bean.UserBeanDao;
import bwie.com.jdemo.bean.XiangBean;
import bwie.com.jdemo.bean.XinBean;
import bwie.com.jdemo.presenter.MainPresenter;
import bwie.com.jdemo.utils.GreenDao;
import bwie.com.jdemo.view.fragment.Fragment01;

public class LoginActivity extends AppCompatActivity implements IMainActivity, View.OnClickListener {

    private ImageView mBack;
    /**
     * 用户名/手机号/邮箱
     */
    private EditText mEtUsername;
    /**
     * 请输入密码
     */
    private EditText mEtPwd;
    private ImageView mYin;
    /**
     * 登录
     */
    private Button mLLogin;
    /**
     * 手机快速注册
     */
    private TextView mRegist;
    /**
     * 忘记密码
     */
    private TextView mWang;
    private ImageView mWeixin;
    private ImageView mQq;
    private LinearLayout mActivityLogin;
    private MainPresenter mainPresenter;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mainPresenter = new MainPresenter(this);
        initView();

    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mEtUsername = (EditText) findViewById(R.id.et_username);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
        mYin = (ImageView) findViewById(R.id.yin);
        mLLogin = (Button) findViewById(R.id.l_login);
        mRegist = (TextView) findViewById(R.id.regist);
        mWang = (TextView) findViewById(R.id.wang);
        mWeixin = (ImageView) findViewById(R.id.weixin);
        mQq = (ImageView) findViewById(R.id.qq);
        mActivityLogin = (LinearLayout) findViewById(R.id.activity_login);

        mBack.setOnClickListener(this);
        mEtUsername.setOnClickListener(this);
        mEtPwd.setOnClickListener(this);
        mYin.setOnClickListener(this);
        mLLogin.setOnClickListener(this);
        mRegist.setOnClickListener(this);
        mWang.setOnClickListener(this);
        mWeixin.setOnClickListener(this);
        mQq.setOnClickListener(this);
        mActivityLogin.setOnClickListener(this);
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
        return mEtUsername.getText().toString().trim();
    }

    @Override
    public String getPwd() {
        return mEtPwd.getText().toString().trim();
    }


    @Override
    public void show4(String str, String str2) {
        Toast.makeText(LoginActivity.this, str, Toast.LENGTH_SHORT).show();
        if("登录成功".equals(str)){
            //跳转到首页
           /* Intent intent = new Intent(LoginActivity.this, Fragment01.class);
            startActivity(intent);*/
            SharedPreferences mSharedPreferences = getSharedPreferences("loginUser", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString("state", "登录");
            editor.putString("name",mEtUsername.getText().toString());
            editor.commit();
            finish();
        }
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
            case R.id.back:
                LoginActivity.this.finish();
                Toast.makeText(LoginActivity.this,"返回上一级",Toast.LENGTH_SHORT).show();
                break;
            case R.id.et_username:
                break;
            case R.id.et_pwd:
                break;
            case R.id.yin:
                break;
            case R.id.l_login:
                mainPresenter.login();
                break;
            case R.id.regist:
                //跳转到注册的界面
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.wang:
                break;
            case R.id.weixin:
                break;
            case R.id.qq:
                break;
            case R.id.activity_login:
                break;
        }
    }
}
