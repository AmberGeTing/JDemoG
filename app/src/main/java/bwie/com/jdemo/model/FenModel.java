package bwie.com.jdemo.model;

import bwie.com.jdemo.bean.FenBean;
import bwie.com.jdemo.comm.IFenModel;
import bwie.com.jdemo.utils.MyHandler;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.utils.RetrofitHelper;
import bwie.com.jdemo.utils.ServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ASUS on 2017/12/12.
 */

public class FenModel extends MyHandler implements IFenModel{

    @Override
    public void login(final OnNetLisenter<FenBean> onNetLisenter) {
        ServiceAPI serviceAPI = RetrofitHelper.getServiceAPI();
        Call<FenBean> fen = serviceAPI.fen();
        fen.enqueue(new Callback<FenBean>() {
            @Override
            public void onResponse(Call<FenBean> call, Response<FenBean> response) {
                final FenBean bean = response.body();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetLisenter.onSuccess(bean);
                    }
                });
            }

            @Override
            public void onFailure(Call<FenBean> call, final Throwable t) {
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
