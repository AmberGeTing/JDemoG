package bwie.com.jdemo.utils;

import android.webkit.URLUtil;

import java.net.URL;

import bwie.com.jdemo.bean.Bean;
import bwie.com.jdemo.bean.CartBean;
import bwie.com.jdemo.bean.ChuangDingDan;
import bwie.com.jdemo.bean.FenBean;
import bwie.com.jdemo.bean.FenChildBean;
import bwie.com.jdemo.bean.GoodsBean;
import bwie.com.jdemo.bean.LoginBean;
import bwie.com.jdemo.bean.PingBean;
import bwie.com.jdemo.bean.PingJiaBean;
import bwie.com.jdemo.bean.RegisterBean;
import bwie.com.jdemo.bean.SouSuoBean;
import bwie.com.jdemo.bean.TuiJianBean;
import bwie.com.jdemo.bean.XiangBean;
import bwie.com.jdemo.bean.XiangChildBean;
import bwie.com.jdemo.bean.XinBean;
import bwie.com.jdemo.bean.XinXiangBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ASUS on 2017/12/4.
 */

public interface ServiceAPI {
    @GET(UrlUtils.SHOW)
    Call<Bean> tags();
    @GET(UrlUtils.XIANG)
    Call<XiangBean> xiang(@Query("pid") String pid);
    @GET(UrlUtils.ADD)
    Call<GoodsBean> add(@Query("uid") String uid, @Query("pid") String pid);
    @GET(UrlUtils.LOGIN)
    Call<LoginBean> login(@Query("mobile") String account, @Query("password") String pwd);
    @GET(UrlUtils.REGIST)
    Call<RegisterBean> regist(@Query("mobile") String account, @Query("password") String pwd);
    @GET(UrlUtils.GOODS)
    Call<CartBean> goods();
    @GET(UrlUtils.PING)
    Call<PingBean> ping();
    @GET(UrlUtils.XIN)
    Call<XinBean> xin();
    @GET(UrlUtils.FEN)
    Call<FenBean> fen();
    @GET(UrlUtils.FENHILD)
    Call<FenChildBean> fenChild();
    @GET(UrlUtils.XIANG_CHILD)
    Call<XiangChildBean> xiangChild(@Query("pscid") String pscid, @Query("page") String page);
    @GET(UrlUtils.XIN_XIANG)
    Call<XinXiangBean> xinXiang(@Query("pdduid") String pdduid);
    @GET(UrlUtils.PINGJIA)
    Call<PingJiaBean> pingjia(@Query("pdduid") String pdduid);
    @GET(UrlUtils.TUIJIAN)
    Call<TuiJianBean> tuijian();
    @GET(UrlUtils.SOUSUO)
    Call<SouSuoBean> sousuo(@Query("keywords") String keywords, @Query("page") String page);
    @GET(UrlUtils.CREATEDINGDAN)
    Call<ChuangDingDan> createDing(@Query("price") String price);
    /*@GET(UrlUtils.TUIJIAN)
    Call<TuiJianBean> tuijian();*/
}
