package bwie.com.jdemo.adapter;

import android.content.Context;
import android.content.Intent;
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
import bwie.com.jdemo.bean.TuiJianBean;
import bwie.com.jdemo.view.XinXIangQingActivity;

/**
 * Created by ASUS on 2017/12/4.
 */

public class TuiJianAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private List<TuiJianBean.ListBean> list;
    private Context context;
    private LayoutInflater inflater;

    private OnItemClickListener mOnItemClickListener = null;
    public TuiJianAdapter(List<TuiJianBean.ListBean> list, Context context) {
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
         View view = inflater.inflate(R.layout.tuijian_item,parent,false);
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
        TuiJianBean.ListBean bean = list.get(position);
        //设置值
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(bean.getThumb_url())
                .setAutoPlayAnimations(true)
                .build();
        myViewHolder.tui_img.setController(controller);
        String goods_name = bean.getGoods_name();
        String substring = goods_name.substring(1, 8);
        myViewHolder.tui_tv.setText(substring);
        //设置点击事件
       /* myViewHolder.tui_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到商品详情界面
                Intent intent = new Intent(context, XinXIangQingActivity.class);

            }
        });*/
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
        private SimpleDraweeView tui_img;
        private TextView tui_tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tui_img = itemView.findViewById(R.id.tui_img);
            tui_tv = itemView.findViewById(R.id.tui_tv);
        }
    }
}
