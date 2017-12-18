package bwie.com.jdemo.presenter;

import bwie.com.jdemo.bean.SouSuoBean;
import bwie.com.jdemo.comm.ISouSuo;
import bwie.com.jdemo.model.SouSuoModel;
import bwie.com.jdemo.utils.OnNetLisenter;

/**
 * Created by ASUS on 2017/12/16.
 */

public class SouSuoPresenter {
    private ISouSuo iSouSuo;
    private SouSuoModel souSuoModel;

    public SouSuoPresenter(ISouSuo iSouSuo) {
        this.iSouSuo = iSouSuo;
        souSuoModel = new SouSuoModel();
    }
    public void login(){
        String keywords = iSouSuo.keyWords();
        String page = iSouSuo.page();
        souSuoModel.login(keywords, page, new OnNetLisenter<SouSuoBean>() {
            @Override
            public void onSuccess(SouSuoBean souSuoBean) {
                iSouSuo.show(souSuoBean);
            }

            @Override
            public void onFaulare(Throwable e) {

            }
        });
    }
}
