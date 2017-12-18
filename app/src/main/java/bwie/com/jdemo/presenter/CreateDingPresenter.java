package bwie.com.jdemo.presenter;

import bwie.com.jdemo.bean.ChuangDingDan;
import bwie.com.jdemo.model.CreateDingModel;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.view.DingShow;

/**
 * Created by ASUS on 2017/12/16.
 */

public class CreateDingPresenter {
    private DingShow dingShow;
    private CreateDingModel createDingModel;

    public CreateDingPresenter(DingShow dingShow) {
        this.dingShow = dingShow;
        createDingModel = new CreateDingModel();
    }
    public void login(){
        String price = dingShow.price();
        createDingModel.login(price, new OnNetLisenter<ChuangDingDan>() {
            @Override
            public void onSuccess(ChuangDingDan chuangDingDan) {
                dingShow.show(chuangDingDan);
            }

            @Override
            public void onFaulare(Throwable e) {

            }
        });
    }
}
