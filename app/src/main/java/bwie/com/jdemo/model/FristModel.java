package bwie.com.jdemo.model;

import java.util.List;

import bwie.com.jdemo.bean.Bean;
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

public class FristModel extends MyHandler implements IFristModel{

    @Override
    public void login(final OnNetLisenter<Bean> onNetLisenter){
        ServiceAPI serviceAPI = RetrofitHelper.getServiceAPI();
        Call<Bean> tags = serviceAPI.tags();
        tags.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {
                final Bean bean = response.body();

                handler.post(new Runnable() {
                     @Override
                     public void run() {
                         onNetLisenter.onSuccess(bean);
                     }
                 });
            }

            @Override
            public void onFailure(Call<Bean> call, final Throwable t) {
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
