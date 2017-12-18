package bwie.com.jdemo.comm;

import bwie.com.jdemo.bean.Bean;
import bwie.com.jdemo.utils.OnNetLisenter;

/**
 * Created by ASUS on 2017/12/4.
 */

public interface IFristModel {
    public void login(OnNetLisenter<Bean> onNetLisenter);
}
