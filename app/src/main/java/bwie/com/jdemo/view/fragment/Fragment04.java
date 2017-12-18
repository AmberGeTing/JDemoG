package bwie.com.jdemo.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Map;

import bwie.com.jdemo.R;
import bwie.com.jdemo.adapter.MyAdapter;
import bwie.com.jdemo.bean.Bean;
import bwie.com.jdemo.bean.CartBean;
import bwie.com.jdemo.bean.ChuangDingDan;
import bwie.com.jdemo.bean.FenBean;
import bwie.com.jdemo.bean.GoodsBean;
import bwie.com.jdemo.bean.PingBean;
import bwie.com.jdemo.bean.XiangBean;
import bwie.com.jdemo.bean.XinBean;
import bwie.com.jdemo.com.pay.alipay.OrderUtils;
import bwie.com.jdemo.com.pay.alipay.PayResult;
import bwie.com.jdemo.presenter.CartPresenter;
import bwie.com.jdemo.presenter.CreateDingPresenter;
import bwie.com.jdemo.utils.MessageEvent;
import bwie.com.jdemo.utils.PriceAndCountEvent;
import bwie.com.jdemo.view.DingShow;
import bwie.com.jdemo.view.IMainActivity;
import bwie.com.jdemo.view.MainActivity;

/**
 * Created by ASUS on 2017/12/4.
 */

public class Fragment04 extends Fragment implements IMainActivity,DingShow {
    private View view;
    private ExpandableListView mElv;
    private CheckBox mCheckbox2;
    /**
     * 0
     */
    private TextView mTvPrice;
    private int totalCount;
    private int totalPrice;
    /**
     * 结算(0)
     */
    private TextView mTvNum;
    private CartPresenter cartPresenter;
    private MyAdapter adapter;
    private Runnable payRunnable;
    private boolean flag=false;
    private CreateDingPresenter createDingPresenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartPresenter = new CartPresenter(this);
        createDingPresenter = new CreateDingPresenter(this);

        cartPresenter.login();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment04, container, false);
        EventBus.getDefault().register(this);
        initView(view);

        //设置点击事件
        mCheckbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.changeAllSelectState(mCheckbox2.isChecked());
            }
        });

        final String orderInfo = getorderInfo();   // 订单信息
        payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(getActivity());
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.obj = result;
                handler.sendMessage(msg);
            }
        };
        //设置点击事件,吊起支付宝
        mTvNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("确定要支付吗?")
                        .setMessage("未支付的订单会在不久之后关闭,请尽快支付!")
                        .setPositiveButton("确定",new  DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //跳转到
                                Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
                                Thread thread = new Thread(payRunnable);
                                thread.start();
                                flag=true;
                                //创建一个订单
                                createDingPresenter.login();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .show();






            }
        });
        return view;
    }
    //当暂停或者退出当前的时候,弹框放弃支付

    @Override
    public void onPause() {
        super.onPause();
       if(flag==true){

       }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
    private void initView(View view) {
        mElv = (ExpandableListView) view.findViewById(R.id.elv);
        mCheckbox2 = (CheckBox) view.findViewById(R.id.checkbox2);
        mTvPrice = (TextView) view.findViewById(R.id.tv_price);
        mTvNum = (TextView) view.findViewById(R.id.tv_num);
    }
    //支付宝
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);

            /**
             对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
             */
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
            String resultStatus = payResult.getResultStatus();
            // 判断resultStatus 为9000则代表支付成功
            if (TextUtils.equals(resultStatus, "9000")) {
                // TODO 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
            } else {
                //TODO  该笔订单真实的支付结果，需要依赖服务端的异步通知。
                Toast.makeText(getActivity(), "支付失败", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public String getorderInfo() {
        Map<String, String> params = OrderUtils.buildOrderParamMap(OrderUtils.APPID);
        String orderParam = OrderUtils.buildOrderParam(params);
        String sign = OrderUtils.getSign(params, OrderUtils.RSA_PRIVATE, false);
        final String orderInfo = orderParam + "&" + sign;
        return orderInfo;
    }

    @Override
    public void show(Bean bean) {

    }

    @Override
    public void show2(XiangBean sbean) {

    }

    @Override
    public void show3(GoodsBean gbean) {

    }

    @Override
    public String getPid() {
        return null;
    }

    @Override
    public String getUid() {
        return null;
    }

    @Override
    public String getAccount() {
        return null;
    }

    @Override
    public String getPwd() {
        return null;
    }

    @Override
    public void show4(String str, String str2) {

    }
    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        mCheckbox2.setChecked(event.isChecked());
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageEvent(PriceAndCountEvent event) {
        totalCount += event.getCount();
        totalPrice += event.getPrice();
        mTvNum.setText("结算(" + totalCount + ")");
        mTvPrice.setText(totalPrice + "");
    }
    @Override
    public void showList(List<CartBean.DataBean> groupList, List<List<CartBean.DataBean.ListBean>> childList) {
        adapter = new MyAdapter(getContext(), groupList, childList);
        mElv.setAdapter(adapter);
        mElv.setGroupIndicator(null);
        //默认让其全部展开
        for (int i = 0; i < groupList.size(); i++) {
            mElv.expandGroup(i);
        }

    }

    @Override
    public void showXin(XinBean xinBean) {

    }


    @Override
    public void show(ChuangDingDan chuangDingDan) {
           //提示是否创建成功
        String code = chuangDingDan.getCode();
        String msg = chuangDingDan.getMsg();
        if("0".equals(code)){
            Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public String price() {
        return mTvPrice.getText().toString().trim();
    }
}
