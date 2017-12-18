package bwie.com.jdemo.comm;

import bwie.com.jdemo.bean.RegisterBean;
import bwie.com.jdemo.utils.OnNetLisenter;


/**
 * Created by ASUS on 2017/11/8.
 */

public interface IRegisterModel {
    public void register(String account, String pwd, OnNetLisenter<RegisterBean> onNetLisenter);
}
