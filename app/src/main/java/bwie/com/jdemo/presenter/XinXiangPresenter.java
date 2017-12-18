package bwie.com.jdemo.presenter;

import bwie.com.jdemo.bean.XinXiangBean;
import bwie.com.jdemo.model.XinXiangModel;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.view.IXinXiang;

/**
 * Created by ASUS on 2017/12/13.
 */

public class XinXiangPresenter {
    private XinXiangModel xinXiangModel;
    private IXinXiang iXinXiang;

    public XinXiangPresenter(IXinXiang iXinXiang) {
        this.iXinXiang = iXinXiang;
        xinXiangModel = new XinXiangModel();
    }
    public void login(){
        String s = iXinXiang.getpdduid();
        xinXiangModel.login(s, new OnNetLisenter<XinXiangBean>() {
            @Override
            public void onSuccess(XinXiangBean xinXiangBean) {
                iXinXiang.show(xinXiangBean);
            }

            @Override
            public void onFaulare(Throwable e) {

            }
        });
    }
}
