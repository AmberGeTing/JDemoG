package bwie.com.jdemo.presenter;

import bwie.com.jdemo.bean.XiangChildBean;
import bwie.com.jdemo.model.XiangChildModel;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.view.IXiangChild;

/**
 * Created by ASUS on 2017/12/13.
 */

public class XiangChildPresenter {
    private XiangChildModel xiangChildModel;
    private IXiangChild iXiangChild;

    public XiangChildPresenter(IXiangChild iXiangChild) {
        this.iXiangChild = iXiangChild;
        xiangChildModel = new XiangChildModel();
    }
    public void login(String pscid,String page){
        xiangChildModel.login(pscid, page, new OnNetLisenter<XiangChildBean>() {
            @Override
            public void onSuccess(XiangChildBean xiangChildBean) {
                iXiangChild.show(xiangChildBean);
            }

            @Override
            public void onFaulare(Throwable e) {

            }
        });
    }
}
