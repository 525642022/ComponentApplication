package com.example.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bean.UserBean;
import com.example.commonlib.R;
import com.example.uilibrary.dialog.CustomProgressDialog;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * 作者：liujingchang
 * 时间：2017/10/16
 */

public abstract class BaseFragment extends Fragment {
    public View mRootView;
    CustomProgressDialog customDialog;
    public PtrClassicFrameLayout mPtrFrameLayout;
    public Context mContext ;
    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayoutResourceId(), container, false);
        ButterKnife.bind(this, mRootView);
        mContext= getActivity();
        initData(getArguments());
        setControl();
        return mRootView;
    }

    protected abstract int setLayoutResourceId();

    protected abstract void initData(Bundle arguments);

    protected abstract void setControl();

    public void setViewData(UserBean user) {

    }
    public void showProgressBar() {
        if (customDialog == null) {
            customDialog = CustomProgressDialog.createDialog(getActivity(),"");
        }
        customDialog.show();


    }
    public void setTitle(String title) {
        TextView tvTitle = (TextView) mRootView.findViewById(R.id.title_text);
        tvTitle.setText(title);
    }
    public void hideProgressBar() {
        if (customDialog != null && customDialog.isShowing()) {
            customDialog.dismiss();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
    public void setPullHead() {
        mPtrFrameLayout = (PtrClassicFrameLayout) mRootView.findViewById(R.id.material_style_ptr_frame);
        MaterialHeader materialHeader = new MaterialHeader(mContext);
        materialHeader.setColorSchemeColors(new int[]{Color.RED, Color.GREEN, Color.BLUE});
        mPtrFrameLayout.setHeaderView(materialHeader);
        mPtrFrameLayout.addPtrUIHandler(materialHeader);
        mPtrFrameLayout.setLoadingMinTime(1000);
        mPtrFrameLayout.setDurationToCloseHeader(1500);
        //左右滑动时刷新控件禁止掉
        mPtrFrameLayout.disableWhenHorizontalMove(true);
        mPtrFrameLayout.autoRefresh(false);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reFresh();
                        frame.refreshComplete();
                    }
                }, 1000);
            }
        });
    }

    public void reFresh() {
    }

}
