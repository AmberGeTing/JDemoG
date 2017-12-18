package bwie.com.jdemo.presenter;

import bwie.com.jdemo.bean.FenBean;
import bwie.com.jdemo.model.FenModel;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.view.IMain;
import bwie.com.jdemo.view.IMainActivity;

/**
 * Created by ASUS on 2017/12/12.
 */

public class FenPresenter {
    private FenModel fenModel;
    private IMain iMain;

    public FenPresenter(IMain iMain) {
        this.iMain = iMain;
        fenModel = new FenModel();
    }
    public void login(){
        fenModel.login(new OnNetLisenter<FenBean>() {
            @Override
            public void onSuccess(FenBean fenBean) {
                iMain.showFen(fenBean);
            }

            @Override
            public void onFaulare(Throwable e) {

            }
        });
    }
}
