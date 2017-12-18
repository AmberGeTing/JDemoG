package bwie.com.jdemo.presenter;

import java.util.ArrayList;
import java.util.List;

import bwie.com.jdemo.bean.FenChildBean;
import bwie.com.jdemo.model.FenChildModel;
import bwie.com.jdemo.utils.OnNetLisenter;
import bwie.com.jdemo.view.IMain;

/**
 * Created by ASUS on 2017/12/13.
 */

public class FenChildPresenter {
    private FenChildModel fenChildModel;
    private IMain iMain;

    public FenChildPresenter(IMain iMain) {
        this.iMain = iMain;
        fenChildModel = new FenChildModel();
    }
    public void login(String cid){
        fenChildModel.login(cid, new OnNetLisenter<FenChildBean>() {
            @Override
            public void onSuccess(FenChildBean fenChildBean) {
                List<FenChildBean.DataBean> data = fenChildBean.getData();
                List<List<FenChildBean.DataBean.ListBean>> childlist = new ArrayList<>();
                for(int i=0;i<data.size();i++){
                    FenChildBean.DataBean dataBean = data.get(i);
                    childlist.add(dataBean.getList());
                }
                iMain.show(data,childlist);
            }

            @Override
            public void onFaulare(Throwable e) {

            }
        });
    }
}
