package bwie.com.jdemo.presenter;

import bwie.com.jdemo.bean.Bean;
import bwie.com.jdemo.model.FristModel;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.view.IMainActivity;

/**
 * Created by ASUS on 2017/12/4.
 */

public class FristPresenter {
    private FristModel fristModel;
    private IMainActivity iMainActivity;

    public FristPresenter(IMainActivity iMainActivity) {
        this.iMainActivity = iMainActivity;
        fristModel = new FristModel();
    }
    public void login(){
        fristModel.login(new OnNetLisenter<Bean>() {
            @Override
            public void onSuccess(Bean bean) {
                 iMainActivity.show(bean);
            }

            @Override
            public void onFaulare(Throwable e) {

            }
        });
    }
}
