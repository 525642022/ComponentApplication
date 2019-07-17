package com.example.answer.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.answer.R;
import com.example.answer.R2;
import com.example.answer.adapter.HomeAnswerAdapter;
import com.example.answer.api.ApiManager;
import com.example.answer.bean.AnswerList;
import com.example.base.BaseActivity;
import com.example.net.callback.CallBackObserver;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AnswerListActivity extends BaseActivity {
    @BindView(R2.id.home_answer_rv)
    RecyclerView homeAnswerRv;
    public static int TO_ANSWER = 0x1001;
    private List<String> stringList = new ArrayList<>();
    private String cid;
    private HomeAnswerAdapter homeAnswerAdapter;
    private List<AnswerList.DataBean> data = new ArrayList<>();

    @Override
    protected void initView() {
        if ("1".equals(cid)) {
            setTitle("法律法规");
        } else if ("2".equals(cid)) {
            setTitle("生活常识");
        } else if ("3".equals(cid)) {
            setTitle("诗词名著");
        }
        setPullHead();
        setLeftIcon();
        homeAnswerRv.setLayoutManager(new LinearLayoutManager(this));
        homeAnswerAdapter = new HomeAnswerAdapter(this, R.layout.item_home_answer, data);
        homeAnswerRv.setAdapter(homeAnswerAdapter);
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_answer_list;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TO_ANSWER) {
            if (resultCode == RESULT_OK) {
                getData();
            }
        }
    }

    @Override
    public void reFresh() {
        super.reFresh();
        getData();
    }

    @Override
    protected void getData() {
        cid = getIntent().getStringExtra("cid");
        ApiManager.getAnswerList(cid, AnswerListActivity.this, new CallBackObserver<AnswerList>() {
            @Override
            public void onNext(AnswerList answerList) {

                if (answerList.getStatus().getCode().equals("0")) {
                    data.clear();
                    data.addAll(answerList.getData());
                    homeAnswerAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), answerList.getStatus().getCninfo(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        for (int i = 0; i < 10; i++) {
            stringList.add("aa");
        }
    }

    @Override
    protected void setControl() {
//        setPullHead();
    }

}
