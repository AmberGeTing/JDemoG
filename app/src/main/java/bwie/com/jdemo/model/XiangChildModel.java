package bwie.com.jdemo.model;

import bwie.com.jdemo.bean.FenChildBean;
import bwie.com.jdemo.bean.XiangChildBean;
import bwie.com.jdemo.comm.IXiangChildModel;
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

public class XiangChildModel extends MyHandler implements IXiangChildModel {
    @Override
    public void login(String pscid, String page, final OnNetLisenter<XiangChildBean> onNetLisenter) {
        ServiceAPI serviceAPI = RetrofitHelper.getServiceAPI();
        Call<XiangChildBean> call = serviceAPI.xiangChild(pscid, page);
        call.enqueue(new Callback<XiangChildBean>() {
            @Override
            public void onResponse(Call<XiangChildBean> call, Response<XiangChildBean> response) {
                final XiangChildBean bean = response.body();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetLisenter.onSuccess(bean);
                    }
                });
            }

            @Override
            public void onFailure(Call<XiangChildBean> call, final Throwable t) {
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
