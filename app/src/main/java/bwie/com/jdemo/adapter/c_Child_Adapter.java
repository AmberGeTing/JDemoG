package bwie.com.jdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bwie.com.jdemo.R;
import bwie.com.jdemo.bean.FenChildBean;
import bwie.com.jdemo.view.C_childActivity;

public class c_Child_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<FenChildBean.DataBean.ListBean> list;
    private Context context;
    private LayoutInflater inflater;


    public c_Child_Adapter(List<FenChildBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.c_child_adapter,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final FenChildBean.DataBean.ListBean listBean = list.get(position);
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        myViewHolder.title.setText(listBean.getName());
        Uri uri=Uri.parse(listBean.getIcon());
        myViewHolder.sd.setImageURI(uri);
        myViewHolder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, C_childActivity.class);
                intent.putExtra("pscid",listBean.getPscid()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
         private SimpleDraweeView sd;
         private TextView title;
         private LinearLayout linear;
        public MyViewHolder(View itemView) {
            super(itemView);
            sd=itemView.findViewById(R.id.child_imge);
            title=itemView.findViewById(R.id.child_title);
            linear=itemView.findViewById(R.id.child_linear);
        }
    }
}