package bwie.com.jdemo.presenter;

import bwie.com.jdemo.bean.TuiJianBean;
import bwie.com.jdemo.model.TuiJianModel;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.view.IXinXiang;

/**
 * Created by ASUS on 2017/12/14.
 */

public class TuiJianPresenter {
    private TuiJianModel tuiJianModel;
    private IXinXiang iXinXiang;

    public TuiJianPresenter(IXinXiang iXinXiang) {
        this.iXinXiang = iXinXiang;
        tuiJianModel = new TuiJianModel();
    }
    public void login(){
        tuiJianModel.login(new OnNetLisenter<TuiJianBean>() {
            @Override
            public void onSuccess(TuiJianBean tuiJianBean) {
                iXinXiang.showTui(tuiJianBean);
            }

            @Override
            public void onFaulare(Throwable e) {

            }
        });
    }
}
