package bwie.com.jdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bwie.com.jdemo.R;
import bwie.com.jdemo.bean.Bean;
import bwie.com.jdemo.bean.XinXiangBean;

/**
 * Created by ASUS on 2017/12/13.
 */

public class PinDanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private   List<XinXiangBean.SkuBean> list ;
    private Context context;
    private LayoutInflater inflater;

    public PinDanAdapter(List<XinXiangBean.SkuBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pindan_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        //将创建的View注册点击事件

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        //将position保存在itemView的Tag中，以便点击时进行获取
        myViewHolder.itemView.setTag(position);
        XinXiangBean.SkuBean bean = list.get(position);
        //设置值
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(bean.getThumb_url())
                .setAutoPlayAnimations(true)
                .build();
        myViewHolder.pindan_img.setController(controller);
       myViewHolder.pindan_cha.setText(bean.getQuantity()+"");
       myViewHolder.pindan_tv.setText(bean.getGoods_id()+"");
       /*myViewHolder.pindan.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });*/
    }

    @Override
    public int getItemCount() {
        return 7;
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView pindan_img;
        private TextView pindan_tv,pindan_cha;
        private Button pindan;

        public MyViewHolder(View itemView) {
            super(itemView);
            pindan_img = itemView.findViewById(R.id.pindan_img);
            pindan_tv = itemView.findViewById(R.id.pindan_tv);
            pindan_cha = itemView.findViewById(R.id.pindan_cha);
            pindan = itemView.findViewById(R.id.pindan);

        }
    }

}