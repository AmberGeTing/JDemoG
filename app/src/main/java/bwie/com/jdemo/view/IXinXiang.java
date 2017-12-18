package bwie.com.jdemo.view;

import bwie.com.jdemo.bean.PingJiaBean;
import bwie.com.jdemo.bean.TuiJianBean;
import bwie.com.jdemo.bean.XinXiangBean;

/**
 * Created by ASUS on 2017/12/13.
 */

public interface IXinXiang {
    public String getpdduid();
    public void show(XinXiangBean xinXiangBean);
    public void showping(PingJiaBean pingJiaBean);
    public void showTui(TuiJianBean tuiJianBean);
}
