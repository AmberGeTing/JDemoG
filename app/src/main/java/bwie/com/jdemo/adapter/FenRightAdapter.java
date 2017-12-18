package bwie.com.jdemo.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import bwie.com.jdemo.R;
import bwie.com.jdemo.bean.FenChildBean;

/**
 * Created by ASUS on 2017/12/13.
 */

public class FenRightAdapter extends BaseExpandableListAdapter {
    private List<FenChildBean.DataBean> grouplist;
    private    List<List<FenChildBean.DataBean.ListBean>> childlist;
    private Context context;
    private LayoutInflater inflater;

    public FenRightAdapter(List<FenChildBean.DataBean> grouplist, List<List<FenChildBean.DataBean.ListBean>> childlist, Context context) {
        this.grouplist = grouplist;
        this.childlist = childlist;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return grouplist.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return  1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return  grouplist.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return  childlist.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder;
        if(convertView==null){
            holder=new GroupViewHolder();
            convertView=inflater.inflate(R.layout.c_groupitem,null);
            holder.g_tv=convertView.findViewById(R.id.g_title);
            convertView.setTag(holder);
        }else{
            holder= (GroupViewHolder) convertView.getTag();
        }
        FenChildBean.DataBean dataBean = grouplist.get(groupPosition);

        holder.g_tv.setText(dataBean.getName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder;
        if(convertView==null){
            holder=new ChildViewHolder();
            convertView=inflater.inflate(R.layout.c_childitem,null);
            holder.rv=convertView.findViewById(R.id.child_rv);
            convertView.setTag(holder);
        }else{
            holder= (ChildViewHolder) convertView.getTag();
        }
        List<FenChildBean.DataBean.ListBean> listBeans = childlist.get(childPosition);
        c_Child_Adapter adapter=new c_Child_Adapter(listBeans,context );
        holder.rv.setLayoutManager(new GridLayoutManager(context,4));
        holder.rv.setAdapter(adapter);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class GroupViewHolder{
        private TextView g_tv;
    }
    class ChildViewHolder{
        private RecyclerView rv;
    }
}
