package bwie.com.jdemo.model;

import android.util.Log;

import bwie.com.jdemo.bean.XinBean;
import bwie.com.jdemo.comm.IXinModel;
import bwie.com.jdemo.utils.MyHandler;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.utils.RetrofitHelper;
import bwie.com.jdemo.utils.RetrofitHelper3;
import bwie.com.jdemo.utils.ServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ASUS on 2017/12/12.
 */

public class XinModel extends MyHandler implements IXinModel{

    @Override
    public void login(final OnNetLisenter<XinBean> onNetLisenter) {
        ServiceAPI serviceAPI = RetrofitHelper3.getServiceAPI();
        Call<XinBean> xin = serviceAPI.xin();
        xin.enqueue(new Callback<XinBean>() {
            @Override
            public void onResponse(Call<XinBean> call, Response<XinBean> response) {
                final XinBean bean = response.body();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetLisenter.onSuccess(bean);
                    }
                });
            }

            @Override
            public void onFailure(Call<XinBean> call, final Throwable t) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                            onNetLisenter.onFaulare(t);
                        Log.e("TAG",t+"1231655651");
                    }
                });
            }
        });
    }
}
