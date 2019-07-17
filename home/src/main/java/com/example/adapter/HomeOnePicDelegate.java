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
public class HomeOnePicDelegate implements ItemViewDelegate<HomeArticleBean.DataBean> {
    private Context mContext;

    public HomeOnePicDelegate(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_home_one_pic;
    }

    @Override
    public boolean isForViewType(HomeArticleBean.DataBean item, int position) {
        return TextUtils.equals(item.getType(), "1");
    }

    @Override
    public void convert(ViewHolder holder, HomeArticleBean.DataBean item, int position) {
        holder.setText(R.id.item_one_title, item.getTitle());
        holder.setText(R.id.item_one_host, item.getAuthor());
        holder.setText(R.id.item_one_time, item.getTimeDesc());
        ImageView item_one_icon = holder.getView(R.id.item_one_icon);
        Glide.with(mContext).load(item.getThumbnail()).centerCrop().into(item_one_icon);
        holder.setOnClickListener(R.id.home_one_pic_rl, v -> {
            Intent intent = new Intent(mContext, NewsDetailActivity.class);
            intent.putExtra("id",item.getId());
            mContext.startActivity(intent);
        });
    }
}
