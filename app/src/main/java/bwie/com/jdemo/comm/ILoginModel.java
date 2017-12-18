package bwie.com.jdemo.comm;

import bwie.com.jdemo.bean.LoginBean;
import bwie.com.jdemo.utils.OnNetLisenter;


/**
 * Created by ASUS on 2017/11/8.
 */

public interface ILoginModel {
    //创建一个方法
    public void login(String account, String pwd, OnNetLisenter<LoginBean> netLisenter);
}
