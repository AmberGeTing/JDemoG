package bwie.com.jdemo.model;

import bwie.com.jdemo.bean.TuiJianBean;
import bwie.com.jdemo.bean.XinXiangBean;
import bwie.com.jdemo.comm.ITuiJianModel;
import bwie.com.jdemo.utils.MyHandler;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.utils.RetrofitHelper2;
import bwie.com.jdemo.utils.ServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ASUS on 2017/12/14.
 */

public class TuiJianModel extends MyHandler implements ITuiJianModel{
    @Override
    public void login(final OnNetLisenter<TuiJianBean> onNetLisenter) {
        ServiceAPI serviceAPI = RetrofitHelper2.getServiceAPI();
        Call<TuiJianBean> call = serviceAPI.tuijian();
        call.enqueue(new Callback<TuiJianBean>() {
            @Override
            public void onResponse(Call<TuiJianBean> call, Response<TuiJianBean> response) {
                final TuiJianBean bean = response.body();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetLisenter.onSuccess(bean);
                    }
                });
            }

            @Override
            public void onFailure(Call<TuiJianBean> call, final Throwable t) {
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
