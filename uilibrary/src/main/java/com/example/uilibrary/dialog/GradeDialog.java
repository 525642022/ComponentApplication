package com.example.uilibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.uilibrary.R;




public class GradeDialog {

    private Dialog mDialog;
    private TextView question_right;
    private TextView question_error;
    private TextView question_points;
    private TextView question_ok;

    public GradeDialog(@NonNull Context context) {
        if (mDialog == null) {
            mDialog = new Dialog(context, R.style.cancel_dialog);
            mDialog.setContentView(R.layout.dialog_grade);
            mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            question_right = mDialog.findViewById(R.id.question_right);
            question_error = mDialog.findViewById(R.id.question_error);
            question_points = mDialog.findViewById(R.id.question_points);
            question_ok = mDialog.findViewById(R.id.question_ok);
            //触摸外部无法取消
            mDialog.setCanceledOnTouchOutside(false);
            //点击后退键无法取消
            mDialog.setCancelable(false);
        }
    }

    public void setRightText(String right) {
        question_right.setText("正确：" + right);
    }

    public void setErrorText(String error) {
        question_error.setText("错误：" + error);
    }

    public void setPointText(String point) {
        question_points.setText("获得积分：" + point);
    }

    public void setOkClick(View.OnClickListener click) {
        question_ok.setOnClickListener(click);
    }

    public void show() {
        mDialog.show();
    }

    public void dismiss() {
        mDialog.dismiss();
    }
}
