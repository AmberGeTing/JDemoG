package bwie.com.jdemo.model;

import bwie.com.jdemo.bean.XinXiangBean;
import bwie.com.jdemo.comm.IXinXiangModel;
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

public class XinXiangModel extends MyHandler implements IXinXiangModel {

    @Override
    public void login(String pdduid, final OnNetLisenter<XinXiangBean> onNetLisenter) {
        ServiceAPI serviceAPI = RetrofitHelper2.getServiceAPI();
        Call<XinXiangBean> call = serviceAPI.xinXiang(pdduid);
        call.enqueue(new Callback<XinXiangBean>() {
            @Override
            public void onResponse(Call<XinXiangBean> call, Response<XinXiangBean> response) {
                final XinXiangBean bean = response.body();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetLisenter.onSuccess(bean);
                    }
                });
            }

            @Override
            public void onFailure(Call<XinXiangBean> call, final Throwable t) {
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
