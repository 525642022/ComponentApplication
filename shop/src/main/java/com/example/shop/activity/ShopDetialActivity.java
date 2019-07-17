package com.example.shop.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.base.BaseActivity;
import com.example.shop.R;
import com.example.shop.R2;
import com.example.shop.adapter.ShopDetailAdapter;
import com.example.shop.bean.ShopDetail;
import com.example.utils.SharedPrefUtil;
import com.example.utils.utilcode.util.ActivityUtils;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ShopDetialActivity extends BaseActivity {
    @BindView(R2.id.shop_detail_to_get)
    TextView shop_detail_to_get;
    @BindView(R2.id.shop_detail_pay)
    TextView shop_detail_pay;
    @BindView(R2.id.shop_detail_rv)
    RecyclerView shop_detail_rv;
    private View headView;
    private HeaderAndFooterWrapper headerAndFooterWrapper;
    private ShopDetailAdapter shopDetailAdapter;
    private ShopDetail.DataBean.DetailBean detail;
    private List<String> stringList = new ArrayList<>();
    private ShopDetail shopDetail;
    private ImageView shop_detail_iv;
    private TextView shop_detail_money;
    private TextView shop_detail_ori_money;
    private TextView item_shop_detail_send;
    private TextView shop_detail_title;
    private TextView shop_shengyu;

    @Override
    protected void initView() {
        setLeftIcon();
        setTitle("积分商城");
        shop_detail_rv.setLayoutManager(new LinearLayoutManager(this));
        headView = LayoutInflater.from(this).inflate(R.layout.layout_shop_detial_head, null);
        shop_detail_iv = headView.findViewById(R.id.shop_detail_iv);
        shop_detail_money = headView.findViewById(R.id.shop_detail_money);
        shop_detail_ori_money = headView.findViewById(R.id.shop_detail_ori_money);
        item_shop_detail_send = headView.findViewById(R.id.item_shop_detail_send);
        shop_shengyu = headView.findViewById(R.id.shop_shengyu);
        shop_detail_title = headView.findViewById(R.id.shop_detail_title);
        shop_detail_money.setText(detail.getPoints() + "");
        shop_detail_ori_money.setText("原价：" + detail.getPrice());
        shop_detail_title.setText(detail.getTitle());
        shop_detail_ori_money.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        if (!TextUtils.isEmpty(detail.getPic())) {
            Glide.with(ShopDetialActivity.this).load(detail.getPic()).fitCenter().into(shop_detail_iv);
        }
        shop_shengyu.setText("剩余："+detail.getStore()+"件");
        shopDetailAdapter = new ShopDetailAdapter(this, R.layout.item_shop_detail_image, stringList);
        headerAndFooterWrapper = new HeaderAndFooterWrapper(shopDetailAdapter);
        headerAndFooterWrapper.addHeaderView(headView);
        shop_detail_rv.setAdapter(headerAndFooterWrapper);
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_shop_detail;
    }

    @Override
    protected void getData() {
        shopDetail = (ShopDetail) getIntent().getSerializableExtra("shopDetail");
        detail = shopDetail.getData().getDetail();
        stringList = detail.getImgList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        shop_detail_pay.setText(SharedPrefUtil.getPoint(ShopDetialActivity.this));
    }

    @Override
    protected void setControl() {
        shop_detail_to_get.setOnClickListener(view -> {
            String point = SharedPrefUtil.getPoint(ShopDetialActivity.this);
            if(detail.getPoints()>Integer.parseInt(point)){
               Toast.makeText(this, "积分不足", Toast.LENGTH_SHORT).show();
               return;
            }
            Intent intent =new Intent(ShopDetialActivity.this, OrderAddActivity.class);
            intent.putExtra("detail",detail);
            ActivityUtils.startActivity(intent);
        });
    }


}
