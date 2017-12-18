package bwie.com.jdemo.comm;

import bwie.com.jdemo.bean.XiangChildBean;
import bwie.com.jdemo.utils.OnNetLisenter;

/**
 * Created by ASUS on 2017/12/13.
 */

public interface IXiangChildModel {
    public void login(String pscid, String page, OnNetLisenter<XiangChildBean> onNetLisenter);
}
