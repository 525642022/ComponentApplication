package com.example.uilibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uilibrary.R;




public class CustomProgressDialog extends Dialog {
    private Context context = null;
    private static CustomProgressDialog customProgressDialog = null;

    public CustomProgressDialog(Context context){  
        super(context);  
        this.context = context;  
    }  
      
    public CustomProgressDialog(Context context, int theme) {  
        super(context, theme);  
    }  
      
    public static CustomProgressDialog createDialog(Context context,String title){
        customProgressDialog = new CustomProgressDialog(context, R.style.CustomProgressDialog);
        customProgressDialog.setContentView(R.layout.customprogressdialog);
        customProgressDialog.setCancelable(true);
        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;  
        Window window = customProgressDialog.getWindow();  
        WindowManager.LayoutParams params = window.getAttributes();  
        params.dimAmount = 0f;  
        window.setAttributes(params);
        ImageView imageView = (ImageView) customProgressDialog.findViewById(R.id.loadingImageView);
        TextView tv = (TextView) customProgressDialog.findViewById(R.id.pro_txt);
        tv.setText(title);
        imageView.setImageResource(R.drawable.loading);
        Animation myAlphaAnimation=AnimationUtils.loadAnimation(context, R.anim.loading);
        myAlphaAnimation.setInterpolator(new LinearInterpolator());
        imageView.startAnimation(myAlphaAnimation);
        return customProgressDialog;  
    }  
   
    public void onWindowFocusChanged(boolean hasFocus){  
          
        if (customProgressDialog == null){  
            return;  
        }


    }  
}  