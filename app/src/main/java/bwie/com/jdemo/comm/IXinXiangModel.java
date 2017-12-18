package bwie.com.jdemo.comm;

import bwie.com.jdemo.bean.XinXiangBean;
import bwie.com.jdemo.utils.OnNetLisenter;

/**
 * Created by ASUS on 2017/12/13.
 */

public interface IXinXiangModel {
    public void login(String pdduid, OnNetLisenter<XinXiangBean> onNetLisenter);
}
