package com.example.me.activity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.example.bean.MyUserBean;
import com.example.bean.UserBean;

import com.example.me.R;
import com.example.me.api.ApiManager;
import com.example.me.fragment.MineFragment;
import com.example.net.callback.CallBackObserver;
import com.example.utils.SharedPrefUtil;

public class MeMainActivity extends BaseActivity {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    MineFragment mineFragment;

    @Override
    protected void initView() {

    }

    @Override
    protected int getViewId() {
        return R.layout.activity_me_main;
    }

    @Override
    protected void getData() {
        /*在activity对应java类中通过getFragmentManager()
         *获得FragmentManager，用于管理ViewGrop中的fragment
         * */
        fragmentManager=getSupportFragmentManager();
        /*FragmentManager要管理fragment（添加，替换以及其他的执行动作）
         *的一系列的事务变化，需要通过fragmentTransaction来操作执行
         */
        fragmentTransaction = fragmentManager.beginTransaction();
        //实例化要管理的fragment
        mineFragment = new MineFragment();
        //通过添加（事务处理的方式）将fragment加到对应的布局中
        fragmentTransaction.add(R.id.fragment_me,mineFragment);
        //事务处理完需要提交
        fragmentTransaction.commit();
    }

    @Override
    protected void setControl() {

    }


    @Override
    protected void onResume() {
        super.onResume();
        String token = SharedPrefUtil.getToken(MeMainActivity.this);
        ApiManager.getUserInfo(MeMainActivity.this, token, new CallBackObserver<MyUserBean>() {
            @Override
            public void onNext(MyUserBean myUserBean) {
                if (myUserBean.getStatus().getCode().equals("0")) {
                    UserBean user = myUserBean.getData().getUser();
                    SharedPrefUtil.setUser(user, MeMainActivity.this);
                    mineFragment.setViewData(user);
                } else {
                    Toast.makeText(getApplicationContext(), myUserBean.getStatus().getCninfo(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
