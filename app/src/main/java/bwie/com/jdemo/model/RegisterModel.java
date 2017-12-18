package bwie.com.jdemo.model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import bwie.com.jdemo.bean.LoginBean;
import bwie.com.jdemo.bean.RegisterBean;
import bwie.com.jdemo.comm.IRegisterModel;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.utils.RetrofitHelper;
import bwie.com.jdemo.utils.ServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by ASUS on 2017/11/8.
 */

public class RegisterModel extends BaseModel implements IRegisterModel {

    @Override
    public void register(String account, String pwd, final OnNetLisenter<RegisterBean> onNetLisenter) {
        ServiceAPI serviceAPI = RetrofitHelper.getServiceAPI();
        Call<RegisterBean> regist = serviceAPI.regist(account,pwd);
        regist.enqueue(new Callback<RegisterBean>() {
            @Override
            public void onResponse(Call<RegisterBean> call, Response<RegisterBean> response) {
                final RegisterBean body = response.body();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetLisenter.onSuccess(body);
                    }
                });
            }

            @Override
            public void onFailure(Call<RegisterBean> call, final Throwable t) {
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
