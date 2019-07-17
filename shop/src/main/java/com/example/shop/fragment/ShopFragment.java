package com.example.shop.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base.BaseFragment;
import com.example.constants.ARouterConfig;
import com.example.net.callback.CallBackObserver;
import com.example.shop.R;
import com.example.shop.R2;
import com.example.shop.adapter.ShopAdapter;
import com.example.shop.api.ApiManager;
import com.example.shop.bean.ShopListBean;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：liujingchang
 * 时间：26/01/2018
 */
@Route(path = ARouterConfig.ZXXQ_SHOP_FRAGMENT)
public class ShopFragment extends BaseFragment {
    @BindView(R2.id.home_shop_rv)
    RecyclerView home_shop_rv;
    private List<String>  stringList = new ArrayList<>();
    private  List<ShopListBean.DataBean> data = new ArrayList<>();
    private ShopAdapter mShopAdapter;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fg_shop;
    }

    @Override
    protected void initData(Bundle arguments) {
        geData();
    }

    private void geData() {
        ApiManager.getShopList(new CallBackObserver<ShopListBean>() {
            @Override
            public void onNext(ShopListBean shopListBean) {
                if (shopListBean.getStatus().getCode().equals("0")) {
                    data.clear();
                    data.addAll(shopListBean.getData());
                    mShopAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(mContext, shopListBean.getStatus().getCninfo(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void setControl() {

        setTitle("积分商城");
        setPullHead();
        home_shop_rv.setLayoutManager(new LinearLayoutManager(mContext));
        mShopAdapter =new ShopAdapter(mContext,R.layout.item_shop,data);
        home_shop_rv.setAdapter(mShopAdapter);
    }

    @Override
    public void reFresh() {
        super.reFresh();
        geData();
    }
}
