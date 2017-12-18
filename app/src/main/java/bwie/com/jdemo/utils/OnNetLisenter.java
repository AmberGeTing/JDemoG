package bwie.com.jdemo.utils;

/**
 * Created by ASUS on 2017/12/4.
 */

public interface OnNetLisenter<T> {
    public void onSuccess(T t);
    public void onFaulare(Throwable e);
}
