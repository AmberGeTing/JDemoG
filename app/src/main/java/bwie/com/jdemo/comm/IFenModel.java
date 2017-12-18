package bwie.com.jdemo.comm;

import bwie.com.jdemo.bean.FenBean;
import bwie.com.jdemo.utils.OnNetLisenter;

/**
 * Created by ASUS on 2017/12/12.
 */

public interface IFenModel {
    public void login(OnNetLisenter<FenBean> onNetLisenter);
}
