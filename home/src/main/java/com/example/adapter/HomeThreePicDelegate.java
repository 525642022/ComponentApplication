package com.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.example.home.R;
import com.example.home.activity.NewsDetailActivity;
import com.example.home.bean.HomeArticleBean;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * 作者：liujingchang
 * 时间：2018/6/9
 */
public class HomeThreePicDelegate implements ItemViewDelegate<HomeArticleBean.DataBean> {
    private Context mContext;

    public HomeThreePicDelegate(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_home_three_pic;
    }

    @Override
    public boolean isForViewType(HomeArticleBean.DataBean item, int position) {
        return TextUtils.equals(item.getType(),"2");
    }

    @Override
    public void convert(ViewHolder holder, HomeArticleBean.DataBean  item, int position) {
        holder.setText(R.id.item_three_title,item.getTitle());
        holder.setText(R.id.item_three_host,item.getAuthor());
        holder.setText(R.id.item_three_time,item.getTimeDesc());
        ImageView item_three_icon1 = holder.getView(R.id.item_three_icon1);
        ImageView item_three_icon2 = holder.getView(R.id.item_three_icon2);
        ImageView item_three_icon3 = holder.getView(R.id.item_three_icon3);
        Glide.with(mContext).load(item.getImgList().get(0)).centerCrop().into(item_three_icon1);
        Glide.with(mContext).load(item.getImgList().get(1)).centerCrop().into(item_three_icon2);
        Glide.with(mContext).load(item.getImgList().get(2)).centerCrop().into(item_three_icon3);
         holder.setOnClickListener(R.id.home_three_pic_rl, v -> {
             Intent intent = new Intent(mContext, NewsDetailActivity.class);
             intent.putExtra("id",item.getId());
             mContext.startActivity(intent);
         });
    }
}
