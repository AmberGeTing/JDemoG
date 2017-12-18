package bwie.com.jdemo.adapter;

import android.content.Context;
import android.service.carrier.CarrierMessagingService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import bwie.com.jdemo.R;
import bwie.com.jdemo.bean.CartBean;
import bwie.com.jdemo.utils.MessageEvent;
import bwie.com.jdemo.utils.PriceAndCountEvent;

/**
 * Created by ASUS on 2017/12/9.
 */

public class MyAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<CartBean.DataBean> groupList;
    private List<List<CartBean.DataBean.ListBean>> childList;
    private final LayoutInflater inflater;

    public MyAdapter(Context context, List<CartBean.DataBean> groupList, List<List<CartBean.DataBean.ListBean>> childList) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
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
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view;
        final GroupViewHolder holder;
        if (convertView == null) {
            holder = new GroupViewHolder();
            view = inflater.inflate(R.layout.group_item, null);
            holder.cbGroup = view.findViewById(R.id.cb_parent);
            holder.tv_number = view.findViewById(R.id.tv_number);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (GroupViewHolder) view.getTag();
        }
        final CartBean.DataBean dataBean = groupList.get(groupPosition);
        holder.cbGroup.setChecked(dataBean.isCheck());
        holder.tv_number.setText(dataBean.getSellerName());

        //给holder.cbGroup设置点击事件
        holder.cbGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (holder.cbGroup.isChecked()) {
                    //点击的一级列表中的checkbox为选中状态
                    selectChildAll(groupPosition, true);
                } else {
                    //点击的一级列表中的checkbox未选中状态
                    selectChildAll(groupPosition, false);
                }*/
                dataBean.setCheck(holder.cbGroup.isChecked());
                PriceAndCountEvent priceAndCountEvent = computePriceAndCount(holder.cbGroup.isChecked(), groupPosition);
                selectChildAll(groupPosition, holder.cbGroup.isChecked());
                //判断列表中的checkbox是否都选中
                if (holder.cbGroup.isChecked()) {
                    //点击的一节列表checkbox是选中状态
                    setPriceAndCount(true, priceAndCountEvent.getPrice(), priceAndCountEvent.getCount());
                    if (isAllGroupListChecked()) {
                        //让“全选”为选中状态
                        changeAllSelectState(true);
                    }

                } else {
                    //点击的一节列表checkbox是未选中状态
                    changeAllSelectState(false);
                    setPriceAndCount(false, priceAndCountEvent.getPrice(), priceAndCountEvent.getCount());
                }
            }
        });
        return view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view;
        final ChildViewHolder holder;
        if (convertView == null) {
            holder = new ChildViewHolder();
            view = inflater.inflate(R.layout.child_item, null);
            holder.cbChild = view.findViewById(R.id.cb_child);
            holder.tv_tel = view.findViewById(R.id.tv_tel);
            holder.tv_content = view.findViewById(R.id.tv_content);
            holder.tv_time = view.findViewById(R.id.tv_time);
            holder.tv_price = view.findViewById(R.id.tv_pri);
            holder.tv_del = view.findViewById(R.id.tv_del);
            holder.iv_add = view.findViewById(R.id.iv_add);
            holder.iv_del = view.findViewById(R.id.iv_del);
            holder.tv_num = view.findViewById(R.id.tv_num);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ChildViewHolder) view.getTag();
        }
        final CartBean.DataBean.ListBean datasBean = childList.get(groupPosition).get(childPosition);
        holder.cbChild.setChecked(datasBean.isIscheck());
        holder.tv_tel.setText(datasBean.getTitle());
        String url = datasBean.getDetailUrl();
        String substring = url.substring(1, 3);
        holder.tv_content.setText(substring);
        holder.tv_time.setText(datasBean.getCreatetime());
        holder.tv_price.setText(datasBean.getPrice() + "");
        holder.tv_num.setText(datasBean.getNum() + "");
        holder.tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除
                setPriceAndCount(false, (int) (datasBean.getPrice()*datasBean.getNum()),datasBean.getNum());
                childList.get(groupPosition).remove(childPosition);
                if (childList.get(groupPosition).size() == 0) {
                    childList.remove(groupPosition);
                    groupList.remove(groupPosition);
                }
                notifyDataSetChanged();
            }
        });

        holder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int num = datasBean.getNum();
                datasBean.setNum(++num);
                //判断当前checkbox是否选中
                if (datasBean.isIscheck()) {
                    //把当前点击的条目对应的数量和钱，发送到MainActivity进行显示
                    setPriceAndCount(true, (int) datasBean.getPrice(), 1);
                }
                notifyDataSetChanged();
            }
        });

        holder.iv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = datasBean.getNum();
                if (num == 1) {
                    return;
                }
                if (datasBean.isIscheck()) {
                    //把当前点击的条目对应的数量和钱，发送到MainActivity进行显示
                    setPriceAndCount(false, (int) datasBean.getPrice(), 1);
                }
                datasBean.setNum(--num);
                notifyDataSetChanged();
            }
        });

        //给holder.cbChild设置点击事件
        holder.cbChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断点击的checkbox是否选中
                if (holder.cbChild.isChecked()) {
                    //点击的时候，holder.cbChild是选中状态
                    datasBean.setIscheck(true);
                    //把当前点击的条目对应的数量和钱，发送到MainActivity进行显示
                    setPriceAndCount(true, (int) (datasBean.getPrice() * datasBean.getNum()), datasBean.getNum());

                    if (isAllChildListChecked(groupPosition)) {
                        CartBean.DataBean dataBean = groupList.get(groupPosition);
                        dataBean.setCheck(true);
                        //判断一下其它的一级列表中的checkbox是否都选中，如果都选中则改变全选的状态
                        if (isAllGroupListChecked()) {
                            //去改变全选的状态
                            changeAllSelectState(true);
                        }
                        notifyDataSetChanged();
                    }
                } else {
                    //点击的时候，holder.cbChild是未选中状态
                    datasBean.setIscheck(false);
                    //把当前点击的条目对应的数量和钱，发送到MainActivity进行显示
                    setPriceAndCount(false, (int) (datasBean.getPrice() * datasBean.getNum()), datasBean.getNum());
                    CartBean.DataBean dataBean = groupList.get(groupPosition);
                    dataBean.setCheck(false);
                    //因为此时一级列表checkbox为未选中状态，所以，要把全选改成未选中
                    changeAllSelectState(false);
                    notifyDataSetChanged();
                }
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupViewHolder {
        CheckBox cbGroup;
        TextView tv_number;
    }

    class ChildViewHolder {
        CheckBox cbChild;
        TextView tv_tel;
        TextView tv_content;
        TextView tv_time;
        TextView tv_price;
        TextView tv_del;
        ImageView iv_del;
        ImageView iv_add;
        TextView tv_num;
    }

    /**
     * 点击一级列表时，计算钱和数量
     */
    private PriceAndCountEvent computePriceAndCount(boolean flag, int groupPoisiton) {
        int count = 0;
        int price = 0;
        List<CartBean.DataBean.ListBean> datasBeen = childList.get(groupPoisiton);
        for (int i = 0; i < datasBeen.size(); i++) {
            CartBean.DataBean.ListBean datasBean = datasBeen.get(i);
            if (flag) {
                if (!datasBean.isIscheck()) {
                    count++;
                    price += datasBean.getPrice();
                }
            } else {
                if (datasBean.isIscheck()) {
                    count++;
                    price += datasBean.getPrice();
                }
            }
        }
        PriceAndCountEvent priceAndCountEvent = new PriceAndCountEvent();
        priceAndCountEvent.setPrice(price);
        priceAndCountEvent.setCount(count);
        return priceAndCountEvent;
    }

    /**
     * 设置MainActiivty里的钱和数量
     *
     * @param isAdd
     * @param price
     * @param count
     */
    private void setPriceAndCount(boolean isAdd, int price, int count) {
        PriceAndCountEvent priceAndCountEvent = new PriceAndCountEvent();
        priceAndCountEvent.setCount(isAdd ? count : -count);
        priceAndCountEvent.setPrice(isAdd ? price : -price);
        EventBus.getDefault().post(priceAndCountEvent);
    }

    /**
     * 改变MainActivity里的全选按钮状态
     *
     * @param isChecked
     */
    public void changeAllSelectState(boolean isChecked) {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setChecked(isChecked);
        EventBus.getDefault().postSticky(messageEvent);
    }

    /**
     * 判断一级列表是否全部选中
     *
     * @return
     */
    private boolean isAllGroupListChecked() {
        for (int i = 0; i < groupList.size(); i++) {
            CartBean.DataBean dataBean = groupList.get(i);
            if (!dataBean.isCheck()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 遍历二级列表数据，判断其它的checkbox是否也都选中
     *
     * @return
     */
    private boolean isAllChildListChecked(int groupPostion) {
        List<CartBean.DataBean.ListBean> db = childList.get(groupPostion);
        for (int i = 0; i < db.size(); i++) {
            CartBean.DataBean.ListBean datasBean = db.get(i);
            if (!datasBean.isIscheck()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 设置二级列表是否全选
     *
     * @param groupPosition
     * @param isSelectAll   true 表示全选 false 表示全不选
     */
    private void selectChildAll(int groupPosition, boolean isSelectAll) {
        List<CartBean.DataBean.ListBean> datasBeen = childList.get(groupPosition);
        for (int i = 0; i < datasBeen.size(); i++) {
            CartBean.DataBean.ListBean datasBean = datasBeen.get(i);
            datasBean.setIscheck(isSelectAll);
        }
        notifyDataSetChanged();
    }

    public void selectAllGroup(boolean flag) {
        int count = 0;
        int price = 0;
        //先把MainActivity里的数量和钱置为0，然后计算出所有的数量和钱就行了
        for (int i = 0; i < groupList.size(); i++) {
            List<CartBean.DataBean.ListBean> datasBeen = childList.get(i);
            count += datasBeen.size();
            for (int j = 0; j < datasBeen.size(); j++) {
                price += datasBeen.get(j).getPrice();
            }
        }
        setPriceAndCount(flag, price, count);

        for (int i = 0; i < groupList.size(); i++) {
            CartBean.DataBean dataBean = groupList.get(i);
            dataBean.setCheck(flag);
            selectChildAll(i, flag);
        }
        notifyDataSetChanged();
    }
}