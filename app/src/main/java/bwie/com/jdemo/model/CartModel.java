package bwie.com.jdemo.model;

import bwie.com.jdemo.bean.Bean;
import bwie.com.jdemo.bean.CartBean;
import bwie.com.jdemo.comm.ICartModel;
import bwie.com.jdemo.utils.MyHandler;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.utils.RetrofitHelper;
import bwie.com.jdemo.utils.ServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ASUS on 2017/12/9.
 */

public class CartModel extends MyHandler implements ICartModel{
    @Override
    public void login(final OnNetLisenter<CartBean> onNetLisenter) {
        ServiceAPI serviceAPI = RetrofitHelper.getServiceAPI();
        Call<CartBean> goods = serviceAPI.goods();
        goods.enqueue(new Callback<CartBean>() {
            @Override
            public void onResponse(Call<CartBean> call, Response<CartBean> response) {
                final CartBean bean = response.body();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetLisenter.onSuccess(bean);
                    }
                });
            }

            @Override
            public void onFailure(Call<CartBean> call, final Throwable t) {
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
