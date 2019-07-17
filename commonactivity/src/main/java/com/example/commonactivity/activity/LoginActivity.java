package com.example.commonactivity.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base.BaseActivity;
import com.example.bean.MyUserBean;
import com.example.bean.UserBean;
import com.example.commonactivity.R;
import com.example.commonactivity.R2;
import com.example.commonactivity.api.ApiManager;
import com.example.constants.ARouterConfig;

import com.example.net.callback.CallBackObserver;
import com.example.utils.ActivityManager;
import com.example.utils.SharedPrefUtil;
import com.example.utils.utilcode.util.ActivityUtils;

import butterknife.BindView;

@Route(path = ARouterConfig.ZXXQ_COMMON_LOGIN)
public class LoginActivity extends BaseActivity {
    @BindView(R2.id.login_logo)
    ImageView login_logo;
    @BindView(R2.id.login_bg)
    ImageView login_bg;
    @BindView(R2.id.login_title)
    ImageView login_title;
    @BindView(R2.id.login_input_phone)
    EditText login_input_phone;
    @BindView(R2.id.login_input_pwd)
    EditText login_input_pwd;
    @BindView(R2.id.login_login)
    TextView login_login;
    @BindView(R2.id.login_register)
    TextView login_register;

    @Override
    protected void initView() {
        ActivityManager.addActivity(this);
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void setControl() {
        login_register.setOnClickListener(v -> {
            ActivityUtils.startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            finish();
        });
        login_login.setOnClickListener(v ->{
             String mobile = login_input_phone.getText().toString();
             String pwd = login_input_pwd.getText().toString();
            if (TextUtils.isEmpty(mobile)) {
                Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(pwd)) {
                Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                return;
            }
            ApiManager.login(mobile, pwd, new CallBackObserver<MyUserBean>() {
                @Override
                public void onNext(MyUserBean myUserBean) {
                    if (myUserBean.getStatus().getCode().equals("0")) {
                        UserBean user = myUserBean.getData().getUser();
                        SharedPrefUtil.setUser(user,LoginActivity.this);
                        ActivityManager.finishAll();
                    }else{
                        Toast.makeText(getApplicationContext(), myUserBean.getStatus().getCninfo(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        });
    }


}
