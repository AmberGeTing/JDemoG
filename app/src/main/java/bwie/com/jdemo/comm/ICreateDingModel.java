package bwie.com.jdemo.comm;

import bwie.com.jdemo.bean.ChuangDingDan;
import bwie.com.jdemo.utils.OnNetLisenter;

/**
 * Created by ASUS on 2017/12/16.
 */

public interface ICreateDingModel {
    public void login(String price, OnNetLisenter<ChuangDingDan> onNetLisenter);
}
