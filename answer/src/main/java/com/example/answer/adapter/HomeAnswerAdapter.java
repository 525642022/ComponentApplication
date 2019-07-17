package com.example.answer.adapter;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.answer.R;
import com.example.answer.activity.AnswerDetailActivity;
import com.example.answer.activity.AnswerListActivity;
import com.example.answer.api.ApiManager;
import com.example.answer.bean.AnswerDetail;
import com.example.answer.bean.AnswerList;
import com.example.net.callback.CallBackObserver;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 作者：liujingchang
 * 时间：2018/6/14
 */
public class HomeAnswerAdapter extends CommonAdapter<AnswerList.DataBean> {

    public HomeAnswerAdapter(Context context, int layoutId, List<AnswerList.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, AnswerList.DataBean dataBean, int position) {
        holder.setText(R.id.item_answer_title, dataBean.getTitle());
        holder.setText(R.id.item_answer_point, "全部答对可获得" + dataBean.getPoints() + "积分");
        TextView start_answer = holder.getView(R.id.start_answer);
        if (dataBean.getSuccessed() == 0) {
            start_answer.setBackgroundResource(R.drawable.answer_round_register);
            start_answer.setTextColor(mContext.getResources().getColor(R.color.color_orange));
            start_answer.setEnabled(true);
            start_answer.setText("开始");
            holder.setOnClickListener(R.id.start_answer, v -> {
                ApiManager.getAnswerDetail(dataBean.getId(), new CallBackObserver<AnswerDetail>() {
                    @Override
                    public void onNext(AnswerDetail answerDetail) {
                        if (answerDetail.getStatus().getCode().equals("0")) {
                            AnswerDetail.DataBean data = answerDetail.getData();
                            if(answerDetail.getData().getQuestions().size()==0){
                              Toast.makeText(mContext, "暂时没有题目", Toast.LENGTH_SHORT).show();
                              return;
                            }
                            Intent intent = new Intent(mContext, AnswerDetailActivity.class);
                            intent.putExtra("data", data);
                            intent.putExtra("title", dataBean.getTitle());
                            ((AnswerListActivity) mContext).startActivityForResult(intent, AnswerListActivity.TO_ANSWER);
                        } else {
                            Toast.makeText(mContext, answerDetail.getStatus().getCninfo(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            });
        } else {
            start_answer.setBackgroundResource(R.drawable.answer_round_question_fail);
            start_answer.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            start_answer.setText("已答");
            start_answer.setEnabled(false);
        }
    }

}
