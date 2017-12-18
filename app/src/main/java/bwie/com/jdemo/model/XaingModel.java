package bwie.com.jdemo.model;

import bwie.com.jdemo.bean.Bean;
import bwie.com.jdemo.bean.XiangBean;
import bwie.com.jdemo.comm.IFristModel;
import bwie.com.jdemo.comm.IXaingModel;
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

public class XaingModel extends MyHandler implements IXaingModel{
    @Override
    public void login(String url, final OnNetLisenter<XiangBean> onNetLisenter) {
        ServiceAPI serviceAPI = RetrofitHelper.getServiceAPI();
        Call<XiangBean> xiang = serviceAPI.xiang(url);
        xiang.enqueue(new Callback<XiangBean>() {
            @Override
            public void onResponse(Call<XiangBean> call, Response<XiangBean> response) {
                final XiangBean bean = response.body();
                handler.post(new Runnable() {
                       @Override
                       public void run() {
                           onNetLisenter.onSuccess(bean);
                       }
                   });
            }

            @Override
            public void onFailure(Call<XiangBean> call, final Throwable t) {
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
