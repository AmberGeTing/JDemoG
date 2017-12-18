package bwie.com.jdemo.comm;

import bwie.com.jdemo.bean.XinBean;
import bwie.com.jdemo.utils.OnNetLisenter;

/**
 * Created by ASUS on 2017/12/12.
 */

public interface IXinModel {
    public void login(OnNetLisenter<XinBean> onNetLisenter);
}
