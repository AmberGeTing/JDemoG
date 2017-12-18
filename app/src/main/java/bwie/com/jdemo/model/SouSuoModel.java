package bwie.com.jdemo.model;

import bwie.com.jdemo.bean.CartBean;
import bwie.com.jdemo.bean.SouSuoBean;
import bwie.com.jdemo.comm.ISouSuoModel;
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

public class SouSuoModel extends MyHandler implements ISouSuoModel{
    @Override
    public void login(String keywords, String page, final OnNetLisenter<SouSuoBean> onNetLisenter) {
        ServiceAPI serviceAPI = RetrofitHelper.getServiceAPI();
        Call<SouSuoBean> sousuo = serviceAPI.sousuo(keywords, page);
        sousuo.enqueue(new Callback<SouSuoBean>() {
            @Override
            public void onResponse(Call<SouSuoBean> call, Response<SouSuoBean> response) {
                final SouSuoBean bean = response.body();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetLisenter.onSuccess(bean);
                    }
                });
            }

            @Override
            public void onFailure(Call<SouSuoBean> call, final Throwable t) {
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
