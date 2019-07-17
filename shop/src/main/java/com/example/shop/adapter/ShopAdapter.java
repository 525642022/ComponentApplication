package com.example.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.example.net.callback.CallBackObserver;
import com.example.shop.R;
import com.example.shop.activity.ShopDetialActivity;
import com.example.shop.api.ApiManager;
import com.example.shop.bean.ShopDetail;
import com.example.shop.bean.ShopListBean;
import com.example.utils.LoginUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;


import java.util.List;

/**
 * 作者：liujingchang
 * 时间：2018/6/14
 */
public class ShopAdapter extends CommonAdapter<ShopListBean.DataBean> {

    public ShopAdapter(Context context, int layoutId, List<ShopListBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ShopListBean.DataBean dataBean, int position) {
        ImageView item_shop_icon = holder.getView(R.id.item_shop_icon);
        Glide.with(mContext).load(dataBean.getPic()).centerCrop().into(item_shop_icon);
        holder.setText(R.id.item_shop_title, dataBean.getTitle());
        holder.setText(R.id.item_shop_money, dataBean.getPoints() + "");
        TextView item_shop_ori_money = holder.getView(R.id.item_shop_ori_money);
        item_shop_ori_money.setText("原价："+dataBean.getPrice());
        item_shop_ori_money.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        holder.setOnClickListener(R.id.item_shop_rl, view -> {
            if(LoginUtils.isLogin(mContext)){
                ApiManager.getShopDetail(dataBean.getId(), new CallBackObserver<ShopDetail>() {
                    @Override
                    public void onNext(ShopDetail shopDetail) {
                        if (shopDetail.getStatus().getCode().equals("0")) {
                            Intent intent =  new Intent(mContext, ShopDetialActivity.class);
                            intent.putExtra("shopDetail",shopDetail);
                            mContext.startActivity(intent);
                        } else {
                            Toast.makeText(mContext, shopDetail.getStatus().getCninfo(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
