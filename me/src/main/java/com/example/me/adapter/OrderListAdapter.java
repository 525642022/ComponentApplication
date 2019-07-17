package com.example.me.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.me.R;
import com.example.me.bean.OrderListBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 作者：liujingchang
 * 时间：2018/6/14
 */
public class OrderListAdapter extends CommonAdapter<OrderListBean.DataBean> {

    public OrderListAdapter(Context context, int layoutId, List<OrderListBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, OrderListBean.DataBean dataBean, int position) {
        holder.setText(R.id.item_order_id, dataBean.getCodeInfo());
        holder.setText(R.id.item_order_title, dataBean.getTitle());
        holder.setText(R.id.item_order_address, dataBean.getAddressDetail());
        holder.setText(R.id.item_order_money, "合计：" + dataBean.getPoints() + "积分");
        holder.setText(R.id.item_order_num, "共计" + dataBean.getNum() + "件商品");
        ImageView item_order_icon = holder.getView(R.id.item_order_icon);
        TextView order_state = holder.getView(R.id.order_state);
        Glide.with(mContext).load(dataBean.getPic()).centerCrop().into(item_order_icon);
        if (dataBean.getType() == 1) {
            order_state.setBackgroundResource(R.drawable.me_round_gray_12);
            order_state.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            order_state.setText("待发货");
        } else {
            order_state.setBackgroundResource(R.drawable.me_round_register);
            order_state.setTextColor(mContext.getResources().getColor(R.color.color_orange));
            order_state.setText("已发货");
        }

    }
}
