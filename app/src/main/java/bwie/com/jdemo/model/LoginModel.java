package bwie.com.jdemo.model;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import bwie.com.jdemo.bean.GoodsBean;
import bwie.com.jdemo.bean.LoginBean;
import bwie.com.jdemo.comm.ILoginModel;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.utils.RetrofitHelper;
import bwie.com.jdemo.utils.ServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by ASUS on 2017/11/8.
 */

public class LoginModel extends BaseModel implements ILoginModel {
    @Override
    public void login(String account, String pwd, final OnNetLisenter<LoginBean> netLisenter) {
        ServiceAPI serviceAPI = RetrofitHelper.getServiceAPI();
       Call<LoginBean> login = serviceAPI.login(account,pwd);
       login.enqueue(new Callback<LoginBean>() {
           @Override
           public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
               final LoginBean body = response.body();
               handler.post(new Runnable() {
                   @Override
                   public void run() {
                       netLisenter.onSuccess(body);
                   }
               });
           }

           @Override
           public void onFailure(Call<LoginBean> call, final Throwable t) {
               handler.post(new Runnable() {
                   @Override
                   public void run() {
                            netLisenter.onFaulare(t);
                   }
               });
           }
       });
    }
}
