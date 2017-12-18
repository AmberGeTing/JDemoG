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
import bwie.com.jdemo.bean.XinBean;
import bwie.com.jdemo.comm.OnClick;

/**
 * Created by ASUS on 2017/12/4.
 */

public class XinAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private  List<XinBean.GoodsListBean> list;
    private Context context;
    private LayoutInflater inflater;
    private OnClick onClick;


    public XinAdapter(List<XinBean.GoodsListBean> list, Context context,OnClick onClick) {
        this.list = list;
        this.context = context;
        this.onClick = onClick;
        inflater = LayoutInflater.from(context);

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case 0:
                view =inflater.inflate(R.layout.xin_item, parent, false);
                holder = new MyViewHolder1(view);
                break;
            case 1:
                view =inflater.inflate(R.layout.xin_item2, parent, false);
                holder = new MyViewHolder2(view);
                break;
            case 2:
                view =inflater.inflate(R.layout.xin_item3, parent, false);
                holder = new MyViewHolder3(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final XinBean.GoodsListBean bean = list.get(position);
        switch (getItemViewType(position)){
            case 0:
                final MyViewHolder1 holder1 = (MyViewHolder1) holder;
                //设置值
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(bean.getImage_url())
                        .setAutoPlayAnimations(true)
                        .build();
                holder1.xin_item_img.setController(controller);
                holder1.xin_item_tv.setText(bean.getGoods_name());
                holder1.xin_price.setText("￥"+bean.getNormal_price());
                //设置点击事件
                holder1.li.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClick.onClick(bean.getGoods_id()+"");
                    }
                });
                break;
            case 1:
                MyViewHolder2 holder2 = (MyViewHolder2) holder;
                //设置值
                DraweeController controller2 = Fresco.newDraweeControllerBuilder()
                        .setUri(bean.getImage_url())
                        .setAutoPlayAnimations(true)
                        .build();
                holder2.xin_item_img1.setController(controller2);
                //设置下面的头像
                DraweeController controller3 = Fresco.newDraweeControllerBuilder()
                        .setUri(bean.getHd_thumb_url())
                        .setAutoPlayAnimations(true)
                        .build();
                holder2.xinimg1.setController(controller3);
                holder2.xin_item_tv1.setText(bean.getGoods_name());
                holder2.xin_price1.setText("￥"+bean.getNormal_price());
                //设置点击事件
                holder2.li.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClick.onClick(bean.getGoods_id()+"");
                    }
                });
                break;
            case 2:
                MyViewHolder3 holder3 = (MyViewHolder3) holder;
                DraweeController controller4 = Fresco.newDraweeControllerBuilder()
                        .setUri(bean.getHd_thumb_url())
                        .setAutoPlayAnimations(true)
                        .build();
                holder3.xin_item_img2.setController(controller4);
                //设置下面的头像
                DraweeController controller5 = Fresco.newDraweeControllerBuilder()
                        .setUri(bean.getThumb_url())
                        .setAutoPlayAnimations(true)
                        .build();
                holder3.xinimg2.setController(controller5);
                DraweeController controller6 = Fresco.newDraweeControllerBuilder()
                        .setUri(bean.getHd_thumb_url())
                        .setAutoPlayAnimations(true)
                        .build();
                holder3.xinimg3.setController(controller6);
                holder3.xin_item_tv2.setText(bean.getGoods_name());
                holder3.xin_price2.setText("￥"+bean.getNormal_price());
                //设置点击事件
                holder3.li.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClick.onClick(bean.getGoods_id()+"");
                    }
                });
                break;
        }

       /* //设置值
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(dataBean.getIcon())
                .setAutoPlayAnimations(true)
                .build();
        myViewHolder.imageView.setController(controller);
        myViewHolder.tv.setText(dataBean.getTitle());*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (position%3 == 0){
            return 0;
        } else if (position%3 == 1){
            return 1;
        } else {
            return 2;
        }
    }

    class MyViewHolder1 extends RecyclerView.ViewHolder{
        private SimpleDraweeView xin_item_img;
        private TextView xin_item_tv,xin_price;
        private LinearLayout li;
        public MyViewHolder1(View itemView) {
            super(itemView);
            xin_item_img = itemView.findViewById(R.id.xin_item_img);
            xin_item_tv = itemView.findViewById(R.id.xin_item_tv);
            xin_price = itemView.findViewById(R.id.xin_price);
            li = itemView.findViewById(R.id.li);
        }
    }
    class MyViewHolder2 extends RecyclerView.ViewHolder{
        private SimpleDraweeView xin_item_img1,xinimg1;
        private TextView xin_item_tv1,xin_price1;
        private LinearLayout li;
        public MyViewHolder2(View itemView) {
            super(itemView);
            xin_item_img1 = itemView.findViewById(R.id.xin_item_img1);
            xinimg1= itemView.findViewById(R.id.xinimg1);
            xin_item_tv1 = itemView.findViewById(R.id.xin_item_tv1);
            xin_price1 = itemView.findViewById(R.id.xin_price1);
            li = itemView.findViewById(R.id.li);
        }
    }
    class MyViewHolder3 extends RecyclerView.ViewHolder{
        private SimpleDraweeView xin_item_img2,xinimg2,xinimg3;
        private TextView xin_item_tv2,xin_price2;
        private LinearLayout li;
        public MyViewHolder3(View itemView) {
            super(itemView);
            xin_item_img2 = itemView.findViewById(R.id.xin_item_img2);
            xinimg2 = itemView.findViewById(R.id.xinimg2);
            li = itemView.findViewById(R.id.li);
            xinimg3 = itemView.findViewById(R.id.xinimg3);
            xin_item_tv2 = itemView.findViewById(R.id.xin_item_tv2);
            xin_price2 = itemView.findViewById(R.id.xin_price2);
        }
    }
}
