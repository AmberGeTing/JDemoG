package bwie.com.jdemo.presenter;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bwie.com.jdemo.bean.LoginBean;
import bwie.com.jdemo.bean.RegisterBean;
import bwie.com.jdemo.comm.ILoginModel;
import bwie.com.jdemo.comm.IRegisterModel;
import bwie.com.jdemo.model.LoginModel;
import bwie.com.jdemo.model.RegisterModel;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.view.IMainActivity;


/**
 * Created by ASUS on 2017/11/8.
 */

public class MainPresenter {
    private IMainActivity iMainActivity;
    private String account;
    private String pwd;
    private ILoginModel iLoginModel;
    private IRegisterModel iRegisterModel;

    public MainPresenter(IMainActivity iMainActivity) {
        this.iMainActivity = iMainActivity;
        iLoginModel = new LoginModel();
        iRegisterModel = new RegisterModel();
    }
    //创建一个登陆的方法
    public void login(){
        account = iMainActivity.getAccount();
        pwd = iMainActivity.getPwd();
        //判断密码和用户名是否正确
        if(pwdTrue(pwd)&&nameTrue(account)){
            //登陆
            iLoginModel.login(account, pwd, new OnNetLisenter<LoginBean>() {
                @Override
                public void onSuccess(LoginBean loginBean) {
                    iMainActivity.show4(loginBean.getMsg(),loginBean.getCode());
                }

                @Override
                public void onFaulare(Throwable e) {

                }
            });
        }
    }
    //创建一个注册方法
    public void register(){
        String account = iMainActivity.getAccount();
        String pwd = iMainActivity.getPwd();
        //判断密码和用户名是否正确
        if(pwdTrue(pwd)&&nameTrue(account)) {
            //登陆
            iRegisterModel.register(account, pwd, new OnNetLisenter<RegisterBean>() {
                @Override
                public void onSuccess(RegisterBean registerBean) {
                    iMainActivity.show4(registerBean.getCode(),registerBean.getMsg());
                }

                @Override
                public void onFaulare(Throwable e) {

                }
            });
        }
    }
    //判断密码
    public boolean pwdTrue(String pwd){
        if(TextUtils.isEmpty(pwd)){
            iMainActivity.show4("密码不能为空","++");
            return false;
        }
        if(pwd.length()!=6){
            iMainActivity.show4("密码长度为6位数","++");
            return false;
        }
        return true;
    }
    //判断用户名
    public boolean nameTrue(String account){
        /*if (!isMobileNO(account)) {
            iMainActivity.show4("请输入正确的手机号","++");
            return false;
        }*/
        if(TextUtils.isEmpty(account)){
            iMainActivity.show4("手机号不能为空","++");
            return false;
        }
       return true;
    }
   /* *//*
    判断是否是手机号
     *//*
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^(13[0-9]|14[57]|15[0-35-9]|17[6-8]|18[0-9])[0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }*/

}
