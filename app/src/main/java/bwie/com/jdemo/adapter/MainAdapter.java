package bwie.com.jdemo.adapter;

import android.content.Context;
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
import bwie.com.jdemo.comm.OnClick;

/**
 * Created by ASUS on 2017/12/7.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Bean.MiaoshaBean.ListBeanX> list;
    private Context context;
    private LayoutInflater inflater;
    private OnClick onClick;

    public MainAdapter(List<Bean.MiaoshaBean.ListBeanX> list, Context context,OnClick onClick) {
        this.list = list;
        this.context = context;
        this.onClick = onClick;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.main_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        //将创建的View注册点击事件
        //view.setOnClickListener(this);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        final Bean.MiaoshaBean.ListBeanX beanX = list.get(position);
        //设置值
        String images = beanX.getImages();
        String[] split = images.split("\\|");
        String imgs = split[0];
           //设置图片
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(imgs)
                    .setAutoPlayAnimations(true)
                    .build();
            myViewHolder.img.setController(controller);
            //点击事件
        myViewHolder.la.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onClick(beanX.getPid());
        }
        });
        myViewHolder.tv.setText(beanX.getTitle());
        myViewHolder.price.setText(beanX.getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView img;
        private TextView tv,price;
        private LinearLayout la;
        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.main_img);
            tv = itemView.findViewById(R.id.main_tv);
            price = itemView.findViewById(R.id.main_price);
            la = itemView.findViewById(R.id.la);
        }
    }
}