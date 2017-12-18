package bwie.com.jdemo.presenter;

import bwie.com.jdemo.bean.Bean;
import bwie.com.jdemo.bean.GoodsBean;
import bwie.com.jdemo.model.AddGoodsModel;
import bwie.com.jdemo.model.FristModel;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.view.IMainActivity;

/**
 * Created by ASUS on 2017/12/4.
 */

public class AddGoodsPresenter {
    private AddGoodsModel AddGoodsModel;
    private IMainActivity iMainActivity;

    public AddGoodsPresenter(IMainActivity iMainActivity) {
        this.iMainActivity = iMainActivity;
        AddGoodsModel = new AddGoodsModel();
    }
    public void login(){
        String pid = iMainActivity.getPid();
        String uid = iMainActivity.getUid();
        AddGoodsModel.login(uid, pid, new OnNetLisenter<GoodsBean>() {
            @Override
            public void onSuccess(GoodsBean goodsBean) {
                iMainActivity.show3(goodsBean);
            }

            @Override
            public void onFaulare(Throwable e) {

            }
        });

    }
}
