package bwie.com.jdemo.view;

import java.util.List;

import bwie.com.jdemo.bean.FenBean;
import bwie.com.jdemo.bean.FenChildBean;

/**
 * Created by ASUS on 2017/12/13.
 */

public interface IMain {
    public void show(List<FenChildBean.DataBean> groupList,List<List<FenChildBean.DataBean.ListBean>> childlist);
    public void showFen(FenBean xinBean);
}
