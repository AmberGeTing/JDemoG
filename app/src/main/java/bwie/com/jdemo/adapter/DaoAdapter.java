package bwie.com.jdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bwie.com.jdemo.R;
import bwie.com.jdemo.bean.Bean;

/**
 * Created by ASUS on 2017/12/4.
 */

public class DaoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private List<Bean.DataBean> list;
    private Context context;
    private LayoutInflater inflater;

    private OnItemClickListener mOnItemClickListener = null;
    public DaoAdapter(List<Bean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View view = inflater.inflate(R.layout.dao_item,parent,false);
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
        Bean.DataBean dataBean = list.get(position);
        //设置值
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(dataBean.getIcon())
                .setAutoPlayAnimations(true)
                .build();
        myViewHolder.imageView.setController(controller);
        myViewHolder.tv.setText(dataBean.getTitle());
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
        private SimpleDraweeView imageView;
        private TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.dao_img);
            tv = itemView.findViewById(R.id.dao_tv);
        }
    }
}
