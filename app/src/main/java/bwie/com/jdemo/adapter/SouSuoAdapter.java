package bwie.com.jdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bwie.com.jdemo.R;
import bwie.com.jdemo.bean.Bean;
import bwie.com.jdemo.bean.SouSuoBean;
import bwie.com.jdemo.view.XaingQingActivity;

/**
 * Created by ASUS on 2017/12/4.
 */

public class SouSuoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private List<SouSuoBean.DataBean> list;
    private Context context;
    private LayoutInflater inflater;

    private OnItemClickListener mOnItemClickListener = null;
    public SouSuoAdapter(List<SouSuoBean.DataBean> list, Context context) {
        this.list = list;
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
         View view = inflater.inflate(R.layout.sou_item,parent,false);
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
        SouSuoBean.DataBean dataBean = list.get(position);
        //设置值
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(dataBean.getImages())
                .setAutoPlayAnimations(true)
                .build();
        myViewHolder.sou_img.setController(controller);
        myViewHolder.sou_price.setText(dataBean.getPrice()+"");
        myViewHolder.sou_tv.setText(dataBean.getTitle()+"");
        //点击跳转到详情页面
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public void onClick(View v) {
        mOnItemClickListener.onItemClick(v,(int)v.getTag());
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private SimpleDraweeView sou_img;
        private LinearLayout soumain;
        private TextView sou_price,sou_tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            sou_img = itemView.findViewById(R.id.sou_img);
            sou_price = itemView.findViewById(R.id.sou_price);
            sou_tv = itemView.findViewById(R.id.sou_tv);
            soumain = itemView.findViewById(R.id.soumain);
        }
    }
}
