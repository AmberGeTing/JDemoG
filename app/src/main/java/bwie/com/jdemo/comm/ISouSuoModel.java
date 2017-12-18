package bwie.com.jdemo.comm;

import bwie.com.jdemo.bean.SouSuoBean;
import bwie.com.jdemo.utils.OnNetLisenter;

/**
 * Created by ASUS on 2017/12/16.
 */

public interface ISouSuoModel {
    public void login(String keywords, String page, OnNetLisenter<SouSuoBean> onNetLisenter);
}
