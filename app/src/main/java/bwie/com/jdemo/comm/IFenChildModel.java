package bwie.com.jdemo.comm;

import bwie.com.jdemo.bean.FenChildBean;
import bwie.com.jdemo.utils.OnNetLisenter;

/**
 * Created by ASUS on 2017/12/13.
 */

public interface IFenChildModel {
    public void login(String cid, OnNetLisenter<FenChildBean> onNetLisenter);
}
