package bwie.com.jdemo.comm;

import bwie.com.jdemo.bean.Bean;
import bwie.com.jdemo.bean.GoodsBean;
import bwie.com.jdemo.utils.OnNetLisenter;

/**
 * Created by ASUS on 2017/12/4.
 */

public interface IAddGoodsModel {
    public void login(String uid,String pid,OnNetLisenter<GoodsBean> onNetLisenter);
}
