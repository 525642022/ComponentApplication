package com.example.uilibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.example.uilibrary.R;


/**
 * Created by wxwen on 2018/3/30.
 */

public class CancelDialog {

    private Dialog mDialog;

    /**
     * 标题
     */
    private AppCompatTextView titleTv;
    /**
     * 正文
     */
    private AppCompatTextView contentTv;
    /**
     * 左按钮
     */
    private AppCompatButton leftBtn;
    /**
     * 右按钮
     */
    private AppCompatButton rightBtn;


    private onLeftBtnClcikListener monLeftBtnClcikListener;
    private onRightBtnClcikListener monRightBtnClcikListener;


    public CancelDialog(@NonNull Context context) {
        if (mDialog == null) {
            mDialog = new Dialog(context, R.style.cancel_dialog);
            mDialog.setContentView(R.layout.dialog_cancel);
            mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            titleTv = (AppCompatTextView) mDialog.findViewById(R.id.title_textview_canceldialog);
            contentTv = (AppCompatTextView) mDialog.findViewById(R.id.content_textview_canceldialog);
            leftBtn = (AppCompatButton) mDialog.findViewById(R.id.cancel_botton_canceldialog);
            leftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (monLeftBtnClcikListener != null) {
                        monLeftBtnClcikListener.onLeftBtnClcik();
                    }
                    mDialog.cancel();
                }
            });
            rightBtn = (AppCompatButton) mDialog.findViewById(R.id.confirm_botton_canceldialog);
            rightBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    mDialog.cancel();
                    if (monRightBtnClcikListener != null) {
                        monRightBtnClcikListener.onRightBtnClcik();
                    }

                }
            });
            //触摸外部无法取消
            mDialog.setCanceledOnTouchOutside(false);
            //点击后退键无法取消
            mDialog.setCancelable(false);

        }
    }
   public void hasRight(boolean hasRight){
        if(hasRight){
         rightBtn.setVisibility(View.VISIBLE);
        }else{
            rightBtn.setVisibility(View.GONE);
       }
   }
    /**
     * 设置加载框中的标题文字
     *
     * @param text
     */
    public void setTitleText(String text) {
        titleTv.setText(text);
    }

    /**
     * 设置加载框中的标题文字
     *
     * @param text
     */
    public void setContentText(String text) {
        contentTv.setText(text);
    }

    public TextView getContentTv() {
        return contentTv;
    }

    public TextView getTitleTv() {
        return titleTv;
    }

    /**
     * 设置加载框中的标题文字
     *
     * @param style
     */
    public void setContentText(SpannableStringBuilder style) {
        contentTv.setText(style);
        contentTv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     * 去掉设置加载框中的标题文字焦点
     */
    public void clearContentFocus() {
//        contentTv.clearFocus();
        contentTv.setFocusable(true);
        contentTv.requestFocus();
    }

    /**
     * 设置content背景色
     *
     * @param color
     */
    public void setContentBackground(int color) {
        contentTv.setBackgroundColor(color);
    }

    public void setContentTextHtml(String text) {
        contentTv.setText(Html.fromHtml(text));
    }

    /**
     * 设置左按钮文字
     *
     * @param text
     */
    public void setLeftBtnText(String text) {
        leftBtn.setText(text);
    }

    /**
     * 设置左按钮点击事件
     *
     * @param monLeftBtnClcikListener
     */
    public void setLeftBtnClickListener(onLeftBtnClcikListener monLeftBtnClcikListener) {
        this.monLeftBtnClcikListener = monLeftBtnClcikListener;
    }

    /**
     * 设置右按钮点击事件
     *
     * @param monRightBtnClcikListener
     */
    public void setRightBtnClickListener(onRightBtnClcikListener monRightBtnClcikListener) {
        this.monRightBtnClcikListener = monRightBtnClcikListener;
    }

    /**
     * 设置右按钮点击事件
     *
     * @param str
     */
    public void setRightBtnText(String str) {
        rightBtn.setText(str);
    }

    /**
     * 设置等待框点击空白区域是否可以退出
     *
     * @param iscanCancel
     */
    public void setCanceledOnTouchOutside(boolean iscanCancel) {
        if (mDialog != null) {
            mDialog.setCanceledOnTouchOutside(iscanCancel);
        }
    }

    /**
     * 设置对话框是否可以点击后退键退出
     *
     * @param iscanCancel
     */
    public void setCancelable(boolean iscanCancel) {
        if (mDialog != null) {
            mDialog.setCancelable(iscanCancel);
        }
    }

    /**
     * 显示加载框
     */
    public void show() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    /**
     * 隐藏加载框
     */
    public void dismiss() {
        if (mDialog != null) {
            mDialog.cancel();
        }
    }
    public void setCancleClick(DialogInterface.OnCancelListener cancleClick){
        mDialog.setOnCancelListener(cancleClick);
    }

    /*
     * 设置左按钮是否可见
     */
    public void setLeftBtnVisibility(boolean canLook) {
        if (canLook) {
            leftBtn.setVisibility(View.VISIBLE);
        } else {
            leftBtn.setVisibility(View.GONE);
        }
    }


    public interface onLeftBtnClcikListener {
        void onLeftBtnClcik();
    }

    public interface onRightBtnClcikListener {
        void onRightBtnClcik();
    }
}
