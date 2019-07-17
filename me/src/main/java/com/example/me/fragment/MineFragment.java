package com.example.me.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.base.BaseFragment;
import com.example.bean.UserBean;
import com.example.commonactivity.activity.LoginActivity;
import com.example.commonactivity.activity.RegisterActivity;
import com.example.constants.ARouterConfig;
import com.example.me.R;
import com.example.me.R2;

import com.example.me.activity.OrderListActivity;

import com.example.me.activity.WebViewActivity;
import com.example.uilibrary.view.RoundAngleImageView;
import com.example.utils.LoginUtils;
import com.example.utils.SharedPrefUtil;
import com.example.utils.utilcode.util.ActivityUtils;
import com.example.utils.utilcode.util.AppUtils;

import butterknife.BindView;

/**
 * 作者：liujingchang
 * 时间：26/01/2018
 */
@Route(path = ARouterConfig.ZXXQ_MINE_FRAGMENT)
public class MineFragment extends BaseFragment {

    @BindView(R2.id.mine_head_bg)
    ImageView mine_head_bg;
    @BindView(R2.id.fg_mine_icon)
    RoundAngleImageView fg_mine_icon;
    @BindView(R2.id.fg_user_name)
    TextView fg_user_name;
    @BindView(R2.id.fg_user_register)
    TextView fg_user_register;
    @BindView(R2.id.fg_user_login)
    TextView fg_user_login;
    @BindView(R2.id.fg_user_money)
    TextView fg_user_money;
    @BindView(R2.id.fg_order_list)
    RelativeLayout fg_order_list;
    @BindView(R2.id.fg_about_us)
    RelativeLayout fg_about_us;
    @BindView(R2.id.fg_version)
    TextView fg_version;
    @BindView(R2.id.fg_login_out)
    RelativeLayout fg_login_out;





    @Override
    protected int setLayoutResourceId() {
        return R.layout.fg_mine;
    }

    @Override
    protected void initData(Bundle arguments) {
        fg_version.setText("V"+ AppUtils.getAppVersionName());
    }


    public void setViewData(UserBean user) {
        fg_login_out.setVisibility(View.VISIBLE);
        fg_user_login.setVisibility(View.GONE);
        fg_user_register.setVisibility(View.GONE);
        fg_user_name.setVisibility(View.VISIBLE);
        fg_user_money.setVisibility(View.VISIBLE);
        fg_user_name.setText(user.getAccount());
        fg_user_money.setText("积分：" + user.getPoints());
        Glide.with(mContext).load(user.getHeadImg()).centerCrop().into(fg_mine_icon);
    }

    @Override
    protected void setControl() {
        fg_order_list.setOnClickListener(view -> {
            if(LoginUtils.isLogin(mContext)){
                ActivityUtils.startActivity(new Intent(mContext, OrderListActivity.class));
            }
        });
        fg_login_out.setOnClickListener(v -> {
            SharedPrefUtil.clearUser(mContext);
            setViewLogout();
        });
        fg_user_login.setOnClickListener(v -> {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    ActivityUtils.startActivity(intent);
                });
        fg_user_register.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, RegisterActivity.class);
            ActivityUtils.startActivity(intent);
        });
        fg_about_us.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, WebViewActivity.class);
            ActivityUtils.startActivity(intent);
        });
    }

    private void setViewLogout() {
        fg_login_out.setVisibility(View.GONE);
        fg_user_login.setVisibility(View.VISIBLE);
        fg_user_register.setVisibility(View.VISIBLE);
        fg_user_name.setVisibility(View.GONE);
        fg_user_money.setVisibility(View.GONE);
        fg_mine_icon.setImageResource(R.mipmap.me_touxiang_weidenglu);
    }


}
