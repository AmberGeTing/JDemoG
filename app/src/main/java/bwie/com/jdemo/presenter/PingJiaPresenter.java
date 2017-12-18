package bwie.com.jdemo.presenter;

import bwie.com.jdemo.bean.PingJiaBean;
import bwie.com.jdemo.model.PingJiaModel;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.view.IXinXiang;

/**
 * Created by ASUS on 2017/12/13.
 */

public class PingJiaPresenter {
    private PingJiaModel pingJiaModel;
    private IXinXiang iXinXiang;

    public PingJiaPresenter(IXinXiang iXinXiang) {
        this.iXinXiang = iXinXiang;
        pingJiaModel = new PingJiaModel();
    }
    public void login(){
        String s = iXinXiang.getpdduid();
        pingJiaModel.login(s, new OnNetLisenter<PingJiaBean>() {
            @Override
            public void onSuccess(PingJiaBean pingJiaBean) {
                iXinXiang.showping(pingJiaBean);
            }

            @Override
            public void onFaulare(Throwable e) {

            }
        });
    }

}
