package bwie.com.jdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import bwie.com.jdemo.R;
import bwie.com.jdemo.bean.FenBean;
import bwie.com.jdemo.utils.OnClickApi;

/**
 * Created by ASUS on 2017/12/4.
 */

public class FenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FenBean.DataBean> list;
    private Context context;
    private OnClickApi onClickApi;
    private LayoutInflater inflater;
    private List<Boolean> isOnclick = new ArrayList<>();
    public FenAdapter(Context context, List<FenBean.DataBean> list,OnClickApi onClickApi){
        this.list=list;
        this.context=context;
        this.onClickApi = onClickApi;
        inflater = LayoutInflater.from(context);

        isOnclick = new ArrayList<>();
        for(int i = 0;i<list.size();i++){
            isOnclick.add(false);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fen_item,parent,false);
        MyViewHodler myViewHodler = new MyViewHodler(view);
        return myViewHodler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
       final MyViewHodler myViewHodler = (MyViewHodler) holder;
        final FenBean.DataBean dataBean = list.get(position);
        myViewHodler.tv.setText(dataBean.getName());
        //设置点击事件

        if(isOnclick.get(position)){
            myViewHodler.tv.setTextColor(Color.parseColor("#00a0e9"));
        }else{
            myViewHodler.tv.setTextColor(Color.parseColor("#000000"));
        }
     /*   myViewHodler.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickApi.setOnClickListener(dataBean.getCid()+"");
            }
        });*/
        //判断
        if(isOnclick!=null){
            myViewHodler.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int i = 0; i <isOnclick.size();i++){
                        isOnclick.set(i,false);
                    }
                    isOnclick.set(position,true);
                    onClickApi.setOnClickListener(dataBean.getCid()+"");
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHodler extends RecyclerView.ViewHolder {
        private TextView tv;
        private LinearLayout linearLayout;
        public MyViewHodler(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.fen_tv);
            linearLayout = itemView.findViewById(R.id.linear);
        }
    }


}