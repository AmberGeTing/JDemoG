package bwie.com.jdemo.model;

import bwie.com.jdemo.bean.ChuangDingDan;
import bwie.com.jdemo.comm.ICreateDingModel;
import bwie.com.jdemo.utils.MyHandler;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.utils.RetrofitHelper;
import bwie.com.jdemo.utils.ServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ASUS on 2017/12/16.
 */

public class CreateDingModel extends MyHandler implements ICreateDingModel{

    @Override
    public void login(String price, final OnNetLisenter<ChuangDingDan> onNetLisenter) {
        ServiceAPI serviceAPI = RetrofitHelper.getServiceAPI();
        Call<ChuangDingDan> ding = serviceAPI.createDing(price);
        ding.enqueue(new Callback<ChuangDingDan>() {
            @Override
            public void onResponse(Call<ChuangDingDan> call, Response<ChuangDingDan> response) {
                final ChuangDingDan bean = response.body();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetLisenter.onSuccess(bean);
                    }
                });
            }

            @Override
            public void onFailure(Call<ChuangDingDan> call, final  Throwable t) {
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
