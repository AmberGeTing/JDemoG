package bwie.com.jdemo.presenter;

import java.util.ArrayList;
import java.util.List;

import bwie.com.jdemo.bean.CartBean;
import bwie.com.jdemo.model.CartModel;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.view.IMainActivity;

/**
 * Created by ASUS on 2017/12/9.
 */

public class CartPresenter {
    private CartModel cartModel;
    private IMainActivity iMainActivity;

    public CartPresenter(IMainActivity iMainActivity) {
        this.iMainActivity = iMainActivity;
        cartModel = new CartModel();
    }
    public void login(){
        cartModel.login(new OnNetLisenter<CartBean>() {
            @Override
            public void onSuccess(CartBean cartBean) {
                List<CartBean.DataBean> data = cartBean.getData();
                List<List<CartBean.DataBean.ListBean>> childList = new ArrayList<>();
                for(int i=0;i<data.size();i++){
                    List<CartBean.DataBean.ListBean> list = data.get(i).getList();
                    childList.add(list);
                }
                iMainActivity.showList(data,childList);
            }

            @Override
            public void onFaulare(Throwable e) {

            }
        });
    }
}
