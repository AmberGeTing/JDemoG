package bwie.com.jdemo.utils;

import android.content.Context;

import bwie.com.jdemo.bean.DaoMaster;
import bwie.com.jdemo.bean.DaoSession;

/**
 * Created by ASUS on 2017/12/13.
 */

public class GreenDao {
    private Context context;
    public void create(){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "student.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoMaster.newSession();
    }
}
