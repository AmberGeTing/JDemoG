package bwie.com.jdemo.model;

import bwie.com.jdemo.bean.FenChildBean;
import bwie.com.jdemo.comm.IFenChildModel;
import bwie.com.jdemo.utils.MyHandler;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.utils.RetrofitHelper;
import bwie.com.jdemo.utils.ServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ASUS on 2017/12/13.
 */

public class FenChildModel extends MyHandler implements IFenChildModel {

    @Override
    public void login(String cid, final OnNetLisenter<FenChildBean> onNetLisenter) {
        ServiceAPI serviceAPI = RetrofitHelper.getServiceAPI();
        Call<FenChildBean> fenChildBeanCall = serviceAPI.fenChild();
        fenChildBeanCall.enqueue(new Callback<FenChildBean>() {
            @Override
            public void onResponse(Call<FenChildBean> call, Response<FenChildBean> response) {
                final FenChildBean body = response.body();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetLisenter.onSuccess(body);
                    }
                });
            }

            @Override
            public void onFailure(Call<FenChildBean> call, final Throwable t) {
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
