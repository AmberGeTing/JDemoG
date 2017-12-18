package bwie.com.jdemo.comm;

import bwie.com.jdemo.bean.PingJiaBean;
import bwie.com.jdemo.utils.OnNetLisenter;

/**
 * Created by ASUS on 2017/12/13.
 */

public interface IPingJiaModel {
    public void login(String pid, OnNetLisenter<PingJiaBean> onNetLisenter);
}
