package bwie.com.jdemo.comm;

import bwie.com.jdemo.bean.XiangBean;
import bwie.com.jdemo.utils.OnNetLisenter;

/**
 * Created by ASUS on 2017/12/8.
 */

public interface IXaingModel {
    public void login(String url, OnNetLisenter<XiangBean> onNetLisenter);
}
