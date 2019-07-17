package com.example.home.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;


import com.example.adapter.HomeOnePicDelegate;
import com.example.adapter.HomeThreePicDelegate;
import com.example.base.BaseFragment;
import com.example.home.R;
import com.example.home.api.ApiManager;
import com.example.home.bean.HomeArticleBean;
import com.example.net.callback.CallBackObserver;
import com.example.uilibrary.wrapper.MyLoadMoreWrapper;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;



public class TabFragment extends BaseFragment {

    RecyclerView home_list_rv;
    private int page = 1;
    private List<HomeArticleBean.DataBean> mHomeArticleBeanList = new ArrayList<>();
    private MyLoadMoreWrapper mMyLoadMoreWrapper;
    private String cid;

    public TabFragment() {
        // Required empty public constructor
    }


    @Override
    protected int setLayoutResourceId() {
        return R.layout.fg_home_list;
    }

    @Override
    protected void initData(Bundle arguments) {
        home_list_rv = mRootView.findViewById(R.id.home_list_rv);
        cid = arguments.getString("cid");
        getNetData();
    }

    public void getNetData() {
        page = 1;
        getData();
    }

    public void getData() {
        ApiManager.getArticle(cid, page, new CallBackObserver<HomeArticleBean>() {
            @Override
            public void onNext(HomeArticleBean homeArticleBean) {
                if (homeArticleBean.getStatus().getCode().equals("0")) {
                    if (page == 1) {
                        mHomeArticleBeanList.clear();
                    }
                    List<HomeArticleBean.DataBean> data = homeArticleBean.getData();
                    mMyLoadMoreWrapper.setTotalPage(data.size());
                    mHomeArticleBeanList.addAll(data);
                    mMyLoadMoreWrapper.setOnLoadMoreListener(new MyLoadMoreWrapper.OnLoadMoreListener() {
                        @Override
                        public void onLoadMoreRequested() {
                            getData();
                        }
                    });
                    mMyLoadMoreWrapper.notifyDataSetChanged();
                    page++;
                } else {
                    Toast.makeText(mContext, homeArticleBean.getStatus().getCninfo(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void setControl() {
        home_list_rv.setLayoutManager(new LinearLayoutManager(mContext));
        MultiItemTypeAdapter multiItemTypeAdapter = new MultiItemTypeAdapter<HomeArticleBean.DataBean>(getActivity(), mHomeArticleBeanList);
        HomeThreePicDelegate homeThreePicDelegate = new HomeThreePicDelegate(mContext);
        HomeOnePicDelegate homeOnePicDelegate = new HomeOnePicDelegate(mContext);
        multiItemTypeAdapter.addItemViewDelegate(homeThreePicDelegate);
        multiItemTypeAdapter.addItemViewDelegate(homeOnePicDelegate);
        mMyLoadMoreWrapper = new MyLoadMoreWrapper(multiItemTypeAdapter);
        mMyLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
        home_list_rv.setAdapter(mMyLoadMoreWrapper);
    }


}
