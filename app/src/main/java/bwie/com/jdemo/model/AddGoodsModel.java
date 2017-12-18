package bwie.com.jdemo.model;

import bwie.com.jdemo.bean.Bean;
import bwie.com.jdemo.bean.GoodsBean;
import bwie.com.jdemo.bean.XiangBean;
import bwie.com.jdemo.comm.IAddGoodsModel;
import bwie.com.jdemo.comm.IFristModel;
import bwie.com.jdemo.utils.MyHandler;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.utils.RetrofitHelper;
import bwie.com.jdemo.utils.ServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ASUS on 2017/12/4.
 */

public class AddGoodsModel extends MyHandler implements IAddGoodsModel{


    @Override
    public void login(String uid, String pid, final OnNetLisenter<GoodsBean> onNetLisenter) {
        ServiceAPI serviceAPI = RetrofitHelper.getServiceAPI();
        Call<GoodsBean> add = serviceAPI.add(uid, pid);
        add.enqueue(new Callback<GoodsBean>() {
            @Override
            public void onResponse(Call<GoodsBean> call, Response<GoodsBean> response) {
                final GoodsBean bean = response.body();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetLisenter.onSuccess(bean);
                    }
                });
            }

            @Override
            public void onFailure(Call<GoodsBean> call, final Throwable t) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                            onNetLisenter.onFaulare(t);
                    }
                });
            }
        });
    }
}
