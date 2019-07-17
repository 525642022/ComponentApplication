package com.example.answer.activity;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.answer.R;
import com.example.answer.R2;
import com.example.answer.api.ApiManager;
import com.example.answer.bean.AnswerDetail;
import com.example.answer.bean.AnswerPoint;
import com.example.base.BaseActivity;
import com.example.net.callback.CallBackObserver;
import com.example.uilibrary.dialog.GradeDialog;
import com.smarttop.library.utils.LogUtil;


import java.util.List;

import butterknife.BindView;

public class AnswerDetailActivity extends BaseActivity {
    @BindView(R2.id.answer_detail_title)
    TextView answer_detail_title;
    @BindView(R2.id.answer_detail_questionA)
    TextView answer_detail_questionA;
    @BindView(R2.id.answer_detail_questionB)
    TextView answer_detail_questionB;
    @BindView(R2.id.answer_detail_questionC)
    TextView answer_detail_questionC;
    @BindView(R2.id.answer_detail_questionD)
    TextView answer_detail_questionD;
    @BindView(R2.id.correct_answer)
    TextView correct_answer;
    @BindView(R2.id.answer_detail_next)
    TextView answer_detail_next;
    @BindView(R2.id.question_num)
    TextView question_num;
    private AnswerDetail.DataBean data;
    private List<AnswerDetail.DataBean.QuestionsBean> questions;
    private StringBuilder stringBuilder;
    private  String  pid;
    @Override
    protected void initView() {
        setLeftIcon();
        String title = getIntent().getStringExtra("title");
        setTitle(title);
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_answer_detail;
    }

    @Override
    protected void getData() {
        stringBuilder = new StringBuilder();
        data = (AnswerDetail.DataBean) getIntent().getSerializableExtra("data");
        questions = data.getQuestions();
        pid = data.getPid();
        setViewData(0);
    }

    private void setViewData(int i) {
        AnswerDetail.DataBean.QuestionsBean questionsBean = questions.get(i);
        setTitleTextSize(questionsBean.getTitle(),answer_detail_title);
        answer_detail_title.setText(questionsBean.getTitle());
        question_num.setText("第"+(i+1)+"题");
        List<AnswerDetail.DataBean.QuestionsBean.OptionListBean> optionList = questionsBean.getOptionList();
        setTextSize(optionList.get(0).getDesc(),answer_detail_questionA);
        setTextSize(optionList.get(1).getDesc(),answer_detail_questionB);
        setTextSize(optionList.get(2).getDesc(),answer_detail_questionC);
        answer_detail_questionA.setText(optionList.get(0).getDesc());
        answer_detail_questionB.setText(optionList.get(1).getDesc());
        answer_detail_questionC.setText(optionList.get(2).getDesc());
        if (optionList.size() > 3) {
            setTextSize(optionList.get(3).getDesc(),answer_detail_questionD);
            answer_detail_questionD.setVisibility(View.VISIBLE);
            answer_detail_questionD.setText(optionList.get(3).getDesc());
        } else {
            answer_detail_questionD.setVisibility(View.GONE);
        }
        answer_detail_questionA.setOnClickListener(v -> {
            setAnswer(0, v, optionList, questionsBean.getId());
        });
        answer_detail_questionB.setOnClickListener(v -> {
            setAnswer(1, v, optionList, questionsBean.getId());
        });
        answer_detail_questionC.setOnClickListener(v -> {
            setAnswer(2, v, optionList, questionsBean.getId());
        });
        answer_detail_questionD.setOnClickListener(v -> {
            setAnswer(3, v, optionList, questionsBean.getId());
        });
        answer_detail_next.setOnClickListener(v -> {
            if (questionsBean.getLastFlag() != 1) {
                setViewData(i + 1);
                setAllButtonEnable(true);
                setAllButtonReset();
            } else {
                ApiManager.commitAnswer(pid, stringBuilder.toString(), AnswerDetailActivity.this, new CallBackObserver<AnswerPoint>() {
                    @Override
                    public void onNext(AnswerPoint answerPoint) {
                        if (answerPoint.getStatus().getCode().equals("0")) {
                            AnswerPoint.DataBean data = answerPoint.getData();
                            GradeDialog gradeDialog = new GradeDialog(AnswerDetailActivity.this);
                            gradeDialog.setPointText(data.getScore());
                            gradeDialog.setRightText(data.getRight());
                            gradeDialog.setErrorText(data.getWrong());
                            gradeDialog.setOkClick(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    gradeDialog.dismiss();
                                    setResult(RESULT_OK);
                                    finish();
                                }
                            });
                            gradeDialog.show();
                        } else {
                            Toast.makeText(getApplicationContext(), answerPoint.getStatus().getCninfo(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });
    }

    @Override
    protected void setControl() {


    }

    //所有按钮回复原状
    private void setAllButtonReset() {
        answer_detail_questionA.setBackgroundResource(R.drawable.answer_round_question);
        answer_detail_questionB.setBackgroundResource(R.drawable.answer_round_question);
        answer_detail_questionC.setBackgroundResource(R.drawable.answer_round_question);
        answer_detail_questionD.setBackgroundResource(R.drawable.answer_round_question);
        answer_detail_questionA.setTextColor(getResources().getColor(R.color.colorTextBlack));
        answer_detail_questionB.setTextColor(getResources().getColor(R.color.colorTextBlack));
        answer_detail_questionC.setTextColor(getResources().getColor(R.color.colorTextBlack));
        answer_detail_questionD.setTextColor(getResources().getColor(R.color.colorTextBlack));
        correct_answer.setVisibility(View.INVISIBLE);
    }
   private void  setTextSize(String option,TextView textView){
       LogUtil.e("ljc",option.length()+"");
       if(option.length()>=24){
           textView.setTextSize(10);
       }else if(option.length()>=12){
           textView.setTextSize(14);
       }else{
           textView.setTextSize(18);
       }
   }
    private void  setTitleTextSize(String option,TextView textView){
        LogUtil.e("ljc",option.length()+"");
        if(option.length()>=51){
            textView.setTextSize(10);
        }else if(option.length()>=34){
            textView.setTextSize(14);
        }else{
            textView.setTextSize(18);
        }
    }
    //判断答案是否正确
    private void setAnswer(int position, View v, List<AnswerDetail.DataBean.QuestionsBean.OptionListBean> optionList, String id) {
        TextView textView = (TextView) v;
        if (1 == optionList.get(position).getV()) {
            if(stringBuilder.toString().length()==0){
                stringBuilder.append(id);
            }else{
                stringBuilder.append(","+id);
            }
            textView.setBackgroundResource(R.drawable.answer_round_login);
            textView.setTextColor(getResources().getColor(R.color.colorWhite));
        } else {
            textView.setBackgroundResource(R.drawable.answer_round_question_fail);
            textView.setTextColor(getResources().getColor(R.color.colorWhite));
            correct_answer.setVisibility(View.VISIBLE);
            for (int i = 0; i < optionList.size(); i++) {
                if (1 == optionList.get(i).getV()) {
                    setTitleTextSize("正确答案是：" + optionList.get(i).getDesc(),correct_answer);
                    correct_answer.setText("正确答案是：" + optionList.get(i).getDesc());
                }
            }
        }
        setAllButtonEnable(false);
    }

    //设置按钮是否可以点击
    private void setAllButtonEnable(boolean b) {
        answer_detail_questionA.setEnabled(b);
        answer_detail_questionB.setEnabled(b);
        answer_detail_questionC.setEnabled(b);
        answer_detail_questionD.setEnabled(b);
    }


}
