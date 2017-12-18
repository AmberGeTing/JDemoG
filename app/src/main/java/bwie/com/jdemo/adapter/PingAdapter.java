package bwie.com.jdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bwie.com.jdemo.R;
import bwie.com.jdemo.bean.Bean;
import bwie.com.jdemo.bean.PingJiaBean;

/**
 * Created by ASUS on 2017/12/4.
 */

public class PingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private List<PingJiaBean.DataBean> data;
    private Context context;
    private LayoutInflater inflater;

    private OnItemClickListener mOnItemClickListener = null;
    public PingAdapter(List<PingJiaBean.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View view = inflater.inflate(R.layout.ping_item,parent,false);
         MyViewHolder myViewHolder = new MyViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
         return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
         MyViewHolder myViewHolder = (MyViewHolder) holder;
        //将position保存在itemView的Tag中，以便点击时进行获取
        myViewHolder.itemView.setTag(position);
        PingJiaBean.DataBean dataBean = data.get(position);
        //设置值
        myViewHolder.ping_tv.setText(dataBean.getName());
        myViewHolder.ping_miao.setText(dataBean.getComment());
        myViewHolder.ping_tv.setText(dataBean.getStars()+"");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        mOnItemClickListener.onItemClick(v,(int)v.getTag());
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView ping_tv,ping_miao,ping_color;
        public MyViewHolder(View itemView) {
            super(itemView);
            ping_tv = itemView.findViewById(R.id.ping_tv);
            ping_miao = itemView.findViewById(R.id.ping_miao);
            ping_color = itemView.findViewById(R.id.ping_color);
        }
    }
}
