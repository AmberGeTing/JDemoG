package bwie.com.jdemo.model;

import bwie.com.jdemo.bean.PingJiaBean;
import bwie.com.jdemo.comm.IPingJiaModel;
import bwie.com.jdemo.utils.MyHandler;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.utils.RetrofitHelper2;
import bwie.com.jdemo.utils.ServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ASUS on 2017/12/13.
 */

public class PingJiaModel extends MyHandler implements IPingJiaModel {
    @Override
    public void login(String pid, final OnNetLisenter<PingJiaBean> onNetLisenter) {
        ServiceAPI serviceAPI = RetrofitHelper2.getServiceAPI();
        Call<PingJiaBean> pingjia = serviceAPI.pingjia(pid);
        pingjia.enqueue(new Callback<PingJiaBean>() {
            @Override
            public void onResponse(Call<PingJiaBean> call, Response<PingJiaBean> response) {
                final PingJiaBean bean = response.body();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetLisenter.onSuccess(bean);
                    }
                });
            }

            @Override
            public void onFailure(Call<PingJiaBean> call, final Throwable t) {
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
