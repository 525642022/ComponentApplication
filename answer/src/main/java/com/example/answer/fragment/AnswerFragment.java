package com.example.answer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.answer.R;
import com.example.answer.R2;
import com.example.answer.activity.AnswerListActivity;
import com.example.base.BaseFragment;
import com.example.constants.ARouterConfig;
import com.example.utils.LoginUtils;
import com.example.utils.utilcode.util.ActivityUtils;


import butterknife.BindView;

/**
 * 作者：liujingchang
 * 时间：26/01/2018
 */
@Route(path = ARouterConfig.ZXXQ_ANSWER_FRAGMENT)
public class AnswerFragment extends BaseFragment {


    @BindView(R2.id.fg_answer_live)
    TextView fg_answer_live;
    @BindView(R2.id.fg_answer_law)
    TextView fg_answer_law;
    @BindView(R2.id.fg_answer_poem)
    TextView fg_answer_poem;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fg_answer;
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void setControl() {
        setTitle("答题测试");
        fg_answer_live.setOnClickListener(v -> {
            if (LoginUtils.isLogin(mContext)) {
                Intent intent = new Intent(mContext, AnswerListActivity.class);
                intent.putExtra("cid", "2");
                ActivityUtils.startActivity(intent);
            }

        });
        fg_answer_law.setOnClickListener(v -> {
            if (LoginUtils.isLogin(mContext)) {
                Intent intent = new Intent(mContext, AnswerListActivity.class);
                intent.putExtra("cid", "1");
                ActivityUtils.startActivity(intent);
            }
        });
        fg_answer_poem.setOnClickListener(v -> {
            if (LoginUtils.isLogin(mContext)) {
                Intent intent = new Intent(mContext, AnswerListActivity.class);
                intent.putExtra("cid", "3");
                ActivityUtils.startActivity(intent);
            }
        });
    }


}
