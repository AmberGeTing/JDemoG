package bwie.com.jdemo.comm;

import bwie.com.jdemo.bean.CartBean;
import bwie.com.jdemo.utils.OnNetLisenter;

/**
 * Created by ASUS on 2017/12/9.
 */

public interface ICartModel {
    public void login(OnNetLisenter<CartBean> onNetLisenter);
}
