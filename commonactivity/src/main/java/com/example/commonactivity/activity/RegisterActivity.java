package com.example.commonactivity.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.example.bean.CommonResult;
import com.example.bean.MyUserBean;
import com.example.bean.UserBean;
import com.example.commonactivity.R;
import com.example.commonactivity.R2;
import com.example.commonactivity.api.ApiManager;
import com.example.net.callback.CallBackObserver;
import com.example.utils.ActivityManager;
import com.example.utils.SharedPrefUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class RegisterActivity extends BaseActivity {
    @BindView(R2.id.register_bg)
    ImageView register_bg;
    @BindView(R2.id.register_logo)
    ImageView register_logo;
    @BindView(R2.id.register_title)
    ImageView register_title;
    @BindView(R2.id.register_input_phone)
    EditText register_input_phone;
    @BindView(R2.id.register_input_pwd)
    EditText register_input_pwd;
    @BindView(R2.id.register_input_code)
    EditText register_input_code;
    @BindView(R2.id.send_code)
    TextView send_code;
    @BindView(R2.id.register_code_rl)
    RelativeLayout register_code_rl;
    @BindView(R2.id.register_login)
    TextView register_login;
    @BindView(R2.id.register_register)
    TextView register_register;
    private int count = 60;

    @Override
    protected void initView() {
        ActivityManager.addActivity(this);
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void setControl() {
        send_code.setOnClickListener(v -> {
            sendCode();
        });
        register_login.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
        register_register.setOnClickListener(v -> {
            String mobile = register_input_phone.getText().toString();
            String pwd = register_input_pwd.getText().toString();
            String code = register_input_code.getText().toString();
            if (TextUtils.isEmpty(mobile)) {
                Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(pwd)) {
                Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(code)) {
                Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                return;
            }
            ApiManager.register(mobile, pwd, code, new CallBackObserver<MyUserBean>() {
                @Override
                public void onNext(MyUserBean userBean) {
                    if (userBean.getStatus().getCode().equals("0")) {
                        UserBean user = userBean.getData().getUser();
                        SharedPrefUtil.setUser(user, RegisterActivity.this);
                        ActivityManager.finishAll();
                    } else {
                        Toast.makeText(getApplicationContext(), userBean.getStatus().getCninfo(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }

    public void sendCode() {
        ApiManager.getCode(new CallBackObserver<CommonResult>() {
            @Override
            public void onNext(CommonResult commonResult) {
                if (commonResult.getStatus().getCode().equals("0")) {
                    Toast.makeText(RegisterActivity.this, "验证码发送成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, commonResult.getStatus().getCninfo(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(count + 1)
                .map(aLong -> {
                    return count - aLong; // 由于是倒计时，需要将倒计时的数字反过来
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> setClickable(false))
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onNext(Long aLong) {
                        if (send_code != null)
                            send_code.setText(aLong + "s后重发");
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        if (send_code != null) {
                            setClickable(true);
                            count = 60;
                        }
                    }
                });
    }

    private void setClickable(boolean isClick) {
        if (isClick) {
            send_code.setTextColor(getResources().getColor(R.color.color_orange));
            send_code.setEnabled(true);
            send_code.setText(R.string.send_code);
        } else {
            send_code.setTextColor(getResources().getColor(R.color.colorEditHint));
            send_code.setEnabled(false);
        }
    }
}
