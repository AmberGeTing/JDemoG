package bwie.com.jdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import bwie.com.jdemo.R;
import bwie.com.jdemo.bean.Bean;
import bwie.com.jdemo.bean.CartBean;
import bwie.com.jdemo.bean.FenBean;
import bwie.com.jdemo.bean.GoodsBean;
import bwie.com.jdemo.bean.PingBean;
import bwie.com.jdemo.bean.XiangBean;
import bwie.com.jdemo.bean.XinBean;
import bwie.com.jdemo.presenter.MainPresenter;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,IMainActivity {

    private ImageView mLBack;
    /**
     * 用户名/手机号/邮箱
     */
    private EditText mZUsername;
    /**
     * 请输入密码
     */
    private EditText mZPwd;
    private ImageView mYin;
    /**
     * 注册
     */
    private Button mLRegister;
    private LinearLayout mActivityLogin;
    private MainPresenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mainPresenter = new MainPresenter(this);
        initView();
    }

    private void initView() {
        mLBack = (ImageView) findViewById(R.id.l_back);
        mZUsername = (EditText) findViewById(R.id.z_username);
        mZPwd = (EditText) findViewById(R.id.z_pwd);
        mYin = (ImageView) findViewById(R.id.yin);
        mLRegister = (Button) findViewById(R.id.l_register);
        mLRegister.setOnClickListener(this);
        mActivityLogin = (LinearLayout) findViewById(R.id.activity_login);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case  R.id.l_back:
                //返回登陆界面
                RegisterActivity.this.finish();
                break;
            case R.id.l_register:
                Toast.makeText(RegisterActivity.this,"点击注册",Toast.LENGTH_SHORT).show();
                mainPresenter.register();
                break;
        }
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
        return mZUsername.getText().toString().trim();
    }

    @Override
    public String getPwd() {
        return mZPwd.getText().toString().trim();
    }

    @Override
    public void show4(String str, String str2) {
        Toast.makeText(RegisterActivity.this,str+str2,Toast.LENGTH_SHORT).show();
        //判断
        if("0".equals(str2)){
            RegisterActivity.this.finish();
            Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showList(List<CartBean.DataBean> groupList, List<List<CartBean.DataBean.ListBean>> childList) {

    }

    @Override
    public void showXin(XinBean xinBean) {

    }




}
