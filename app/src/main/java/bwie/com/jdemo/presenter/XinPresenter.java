package bwie.com.jdemo.presenter;

import bwie.com.jdemo.bean.XinBean;
import bwie.com.jdemo.model.XinModel;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.view.IMainActivity;

/**
 * Created by ASUS on 2017/12/12.
 */

public class XinPresenter {
    private XinModel xinModel;
    private IMainActivity iMainActivity;

    public XinPresenter(IMainActivity iMainActivity) {
        this.iMainActivity = iMainActivity;
        xinModel = new XinModel();
    }
    public void login(){
        xinModel.login(new OnNetLisenter<XinBean>() {
            @Override
            public void onSuccess(XinBean xinBean) {
                iMainActivity.showXin(xinBean);
            }

            @Override
            public void onFaulare(Throwable e) {

            }
        });
    }
}
