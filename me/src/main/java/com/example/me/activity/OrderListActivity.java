package com.example.me.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.example.me.R;
import com.example.me.R2;
import com.example.me.adapter.OrderListAdapter;
import com.example.me.api.ApiManager;
import com.example.me.bean.OrderListBean;
import com.example.net.callback.CallBackObserver;
import com.example.uilibrary.wrapper.MyLoadMoreWrapper;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderListActivity extends BaseActivity {
    @BindView(R2.id.order_list_rv)
    RecyclerView order_list_rv;
    @BindView(R2.id.order_empty)
    TextView order_empty;
    private int page = 1;
    private ArrayList<OrderListBean.DataBean> data = new ArrayList<>();
    private MyLoadMoreWrapper mMyLoadMoreWrapper;
    private OrderListAdapter orderListAdapter;
    @Override
    protected void initView() {
        setPullHead();
        setLeftIcon();
        setTitle("我的订单");
        order_list_rv.setLayoutManager(new LinearLayoutManager(this));
        orderListAdapter =  new OrderListAdapter(this, R.layout.item_order_list,data);
        mMyLoadMoreWrapper = new MyLoadMoreWrapper(orderListAdapter);
        mMyLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
        order_list_rv.setAdapter(mMyLoadMoreWrapper);
    }

    @Override
    public void reFresh() {
        super.reFresh();
        page = 1;
        getData();
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_order_list;
    }

    @Override
    protected void getData() {
        ApiManager.getOrderList(OrderListActivity.this, page, new CallBackObserver<OrderListBean>() {
            @Override
            public void onNext(OrderListBean orderListBean) {
                if (orderListBean.getStatus().getCode().equals("0")) {
                    if(page==1){
                        data.clear();
                        if(orderListBean.getData()==null||orderListBean.getData().size()==0){
                            order_empty.setVisibility(View.VISIBLE);
                        }
                    }
                    mMyLoadMoreWrapper.setTotalPage(orderListBean.getData().size());
                    data.addAll(orderListBean.getData());

                    mMyLoadMoreWrapper.setOnLoadMoreListener(new MyLoadMoreWrapper.OnLoadMoreListener() {
                        @Override
                        public void onLoadMoreRequested() {
                            getData();
                        }
                    });
                    mMyLoadMoreWrapper.notifyDataSetChanged();
                    page++;
                } else {
                    Toast.makeText(getApplicationContext(), orderListBean.getStatus().getCninfo(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void setControl() {

    }


}
