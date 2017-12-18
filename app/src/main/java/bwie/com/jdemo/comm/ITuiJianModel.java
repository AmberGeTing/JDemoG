package bwie.com.jdemo.comm;

import bwie.com.jdemo.bean.TuiJianBean;
import bwie.com.jdemo.utils.OnNetLisenter;

/**
 * Created by ASUS on 2017/12/14.
 */

public interface ITuiJianModel {
    public void login(OnNetLisenter<TuiJianBean> onNetLisenter);
}
