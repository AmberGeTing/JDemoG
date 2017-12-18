package bwie.com.jdemo.adapter;

import android.content.Context;
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
import bwie.com.jdemo.bean.XiangChildBean;
import bwie.com.jdemo.utils.OnListenerClickApi;

public class ProducrtsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<XiangChildBean.DataBean> list;
    private LayoutInflater inflater;
    private OnListenerClickApi onListenerClickApi;

    public ProducrtsAdapter(Context context,List<XiangChildBean.DataBean> list, OnListenerClickApi onListenerClickApi) {
        this.context = context;
        this.list = list;
        this.onListenerClickApi = onListenerClickApi;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.f_child_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
      MyViewHolder myViewHolder= (MyViewHolder) holder;
        final XiangChildBean.DataBean dataBean = list.get(position);

        myViewHolder.title.setText(dataBean.getTitle());
        myViewHolder.price.setText("￥"+dataBean.getPrice())
        ;
        myViewHolder.time.setText(dataBean.getCreatetime());
        String images = dataBean.getImages();
        String[] split = images.split("\\|");
        String s = split[0];
        Uri uri = Uri.parse(s);
        myViewHolder.sd.setImageURI(uri);
       /* myViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onListenerClickApi.setOnClieck(dataBean.getPid()+"");
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private SimpleDraweeView sd;
        private TextView title;
        private TextView time;
        private TextView price;
        private LinearLayout linearLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            price=itemView.findViewById(R.id.pi_price);
            sd=itemView.findViewById(R.id.pi_dw);
            title=itemView.findViewById(R.id.pi_title);
            time=itemView.findViewById(R.id.pi_time);
            linearLayout=itemView.findViewById(R.id.pi_linear);
        }
    }
}
