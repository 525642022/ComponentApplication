package com.example.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.commonlib.R;
import com.example.utils.utilcode.util.SizeUtils;
import com.google.gson.Gson;
import com.ywp.addresspickerlib.AddressPickerView;
import com.ywp.addresspickerlib.YwpAddressBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by smartTop on 2016/10/19.
 */

public class BottomDialog extends Dialog {
   private Context context;
    private AddressPickerView addressView;
    public BottomDialog(Context context) {
        super(context, R.style.bottom_dialog);
        init(context);
        this.context =context;
    }

    public BottomDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    public BottomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {

        setContentView(R.layout.layout_dia_pick);
        addressView= findViewById(R.id.apvAddress);
        addressView.setOnAddressPickerSure(new AddressPickerView.OnAddressPickerSureListener() {
            @Override
            public void onSureClick(String address, String provinceCode, String cityCode, String districtCode) {
                if(onPickerOnClick!=null){
                    onPickerOnClick.pickerClick(address,provinceCode,cityCode,districtCode);
                }

                dismiss();
            }
        });
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = SizeUtils.dp2px(533);
        window.setAttributes(params);

        window.setGravity(Gravity.BOTTOM);
    }

    private OnPickerOnClick onPickerOnClick;

    public void setOnPickerOnClick(OnPickerOnClick onPickerOnClick) {
        this.onPickerOnClick = onPickerOnClick;
    }

    public interface OnPickerOnClick {
        void pickerClick(String address, String provinceCode, String cityCode, String districtCode);
    }

    public void initDataBean(){
        StringBuilder jsonSB = new StringBuilder();
        try {
            BufferedReader addressJsonStream = new BufferedReader(new InputStreamReader(context.getAssets().open("address.json")));
            String line;
            while ((line = addressJsonStream.readLine()) != null) {
                jsonSB.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 将数据转换为对象
        YwpAddressBean mYwpAddressBean = new Gson().fromJson(jsonSB.toString(), YwpAddressBean.class);
        addressView.initData(mYwpAddressBean);

    }


}
