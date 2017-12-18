package bwie.com.jdemo.presenter;

import bwie.com.jdemo.bean.Bean;
import bwie.com.jdemo.bean.XiangBean;
import bwie.com.jdemo.model.FristModel;
import bwie.com.jdemo.model.XaingModel;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.view.IMainActivity;

/**
 * Created by ASUS on 2017/12/4.
 */

public class XiangPresenter {
    private XaingModel XaingModel;
    private IMainActivity iMainActivity;

    public XiangPresenter(IMainActivity iMainActivity) {
        this.iMainActivity = iMainActivity;
        XaingModel = new XaingModel();
    }
    public void login(){
        String pid = iMainActivity.getPid();
        XaingModel.login(pid, new OnNetLisenter<XiangBean>() {
            @Override
            public void onSuccess(XiangBean xiangBean) {
                iMainActivity.show2(xiangBean);
            }

            @Override
            public void onFaulare(Throwable e) {

            }
        });
    }
}
