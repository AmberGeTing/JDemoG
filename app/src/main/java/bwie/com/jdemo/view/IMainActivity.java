package bwie.com.jdemo.view;

import java.util.List;

import bwie.com.jdemo.bean.Bean;
import bwie.com.jdemo.bean.CartBean;
import bwie.com.jdemo.bean.FenBean;
import bwie.com.jdemo.bean.GoodsBean;
import bwie.com.jdemo.bean.LoginBean;
import bwie.com.jdemo.bean.PingBean;
import bwie.com.jdemo.bean.RegisterBean;
import bwie.com.jdemo.bean.XiangBean;
import bwie.com.jdemo.bean.XinBean;

/**
 * Created by ASUS on 2017/12/4.
 */

public interface IMainActivity {
    public void show(Bean bean);
    public void show2(XiangBean sbean);
    public void show3(GoodsBean gbean);
    public String getPid();
    public String getUid();
    public String getAccount();
    public String getPwd();
    public void show4(String str,String str2);
    public void showList(List<CartBean.DataBean> groupList, List<List<CartBean.DataBean.ListBean>> childList);
    public void showXin(XinBean xinBean);

}
