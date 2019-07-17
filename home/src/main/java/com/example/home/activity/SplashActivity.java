package com.example.home.activity;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.example.bean.MyUserBean;
import com.example.bean.UserBean;
import com.example.home.R;
import com.example.home.api.ApiManager;
import com.example.net.callback.CallBackObserver;
import com.example.utils.SharedPrefUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

public class SplashActivity extends BaseActivity {
    private static final int REQUEST_CODE_PERMISSION_MULTI = 2000;

    @Override
    protected void initView() {

    }

    @Override
    protected int getViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void getData() {
        requestPermissions();
    }

    @Override
    protected void setControl() {

    }

    private void requestPermissions() {
        AndPermission.with(getApplicationContext()).permission(
                Permission.STORAGE,
                new String[]{
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.CALL_PHONE,
                },
                Permission.CAMERA)
                .requestCode(REQUEST_CODE_PERMISSION_MULTI)
                // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框；
                // 这样避免用户勾选不再提示，导致以后无法申请权限。
                // 你也可以不设置。
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                        AndPermission.rationaleDialog(SplashActivity.this, rationale).show();
                    }
                }).callback(this).start();
    }

    @PermissionYes(REQUEST_CODE_PERMISSION_MULTI)
    private void getMultiYes(@NonNull List<String> grantedPermissions) {
        String uid = SharedPrefUtil.getUserId(SplashActivity.this);
        if(TextUtils.isEmpty(uid)){
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            ApiManager.autoLogin(SplashActivity.this,new CallBackObserver<MyUserBean>() {
                @Override
                public void onNext(MyUserBean myUserBean) {
                    if (myUserBean.getStatus().getCode().equals("0")) {
                        UserBean user = myUserBean.getData().getUser();
                        SharedPrefUtil.setUser(user, SplashActivity.this);
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), myUserBean.getStatus().getCninfo(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @PermissionNo(REQUEST_CODE_PERMISSION_MULTI)
    private void getMultiNo(@NonNull List<String> deniedPermissions) {
        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {
            AndPermission.defaultSettingDialog(this, REQUEST_CODE_PERMISSION_MULTI)
                    .setTitle(R.string.title_dialog)
                    .setMessage(R.string.message_permission_failed)
                    .setPositiveButton(R.string.ok)
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .show();
            // 更多自定dialog，请看上面。
        } else {
            finish();
        }
    }
}
