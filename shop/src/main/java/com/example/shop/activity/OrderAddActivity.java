package com.example.shop.activity;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.base.BaseActivity;
import com.example.bean.CommonResult;
import com.example.constants.ARouterConfig;
import com.example.dialog.BottomDialog;
import com.example.net.callback.CallBackObserver;
import com.example.shop.R;
import com.example.shop.R2;
import com.example.shop.api.ApiManager;
import com.example.shop.bean.ShopDetail;
import com.example.utils.ARouterUtils;

import butterknife.BindView;

public class OrderAddActivity extends BaseActivity {
    @BindView(R2.id.address_tv)
    TextView address_tv;
    @BindView(R2.id.order_add_icon)
    ImageView order_add_icon;
    @BindView(R2.id.order_add_title)
    TextView order_add_title;
    @BindView(R2.id.order_add_money)
    TextView order_add_money;
    @BindView(R2.id.order_input_name)
    EditText order_input_name;
    @BindView(R2.id.order_input_phone)
    EditText order_input_phone;
    @BindView(R2.id.order_input_address_detail)
    EditText order_input_address_detail;
    @BindView(R2.id.commit_order)
    TextView commit_order;

    private ShopDetail.DataBean.DetailBean detail;
    private String province;
    private String city;
    private String district;
    private BottomDialog bottomDialog;

    @Override
    protected void initView() {
        setLeftIcon();
        setTitle("我的订单");
        if (!TextUtils.isEmpty(detail.getPic())) {
            Glide.with(OrderAddActivity.this).load(detail.getPic()).fitCenter().into(order_add_icon);
        }
        order_add_title.setText(detail.getTitle());
        order_add_money.setText(detail.getPoints() + "");
        bottomDialog = new BottomDialog(OrderAddActivity.this);
        bottomDialog.setOnPickerOnClick(new BottomDialog.OnPickerOnClick() {
            @Override
            public void pickerClick(String address, String provinceCode, String cityCode, String districtCode) {
                province = provinceCode;
                city = cityCode;
                district = districtCode;
                address_tv.setTextColor(getResources().getColor(R.color.colorTextBlack));
                address_tv.setText(address);
            }
        });
        commit_order.setOnClickListener(v -> {
            String name = order_input_name.getText().toString().trim();
            String phone = order_input_phone.getText().toString().trim();
            String addressDetail = order_input_address_detail.getText().toString().trim();
            String address = address_tv.getText().toString().trim();
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(this, "请输入收货人姓名", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(phone)) {
                Toast.makeText(this, "请输入收货人手机号", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(address)) {
                Toast.makeText(this, "请输入收货人地址", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(addressDetail)) {
                Toast.makeText(this, "请输入收货人详细地址", Toast.LENGTH_SHORT).show();
                return;
            }
            ApiManager.commitOrder(OrderAddActivity.this, detail.getId(), name, phone, province, city, district, addressDetail, new CallBackObserver<CommonResult>() {
                @Override
                public void onNext(CommonResult commonResult) {
                    if (commonResult.getStatus().getCode().equals("0")) {
                        ARouterUtils.getActivity(ARouterConfig.ZXXQ_MAIN_ACTIVITY);
                    } else {
                        Toast.makeText(OrderAddActivity.this, commonResult.getStatus().getCninfo(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_order_add;
    }

    @Override
    protected void getData() {
        detail = (ShopDetail.DataBean.DetailBean) getIntent().getSerializableExtra("detail");

    }

    @Override
    protected void setControl() {
        address_tv.setOnClickListener(v -> {
            showAddressPickerPop();
        });
    }


    /**
     * 显示地址选择的pop
     */
    private void showAddressPickerPop() {
        bottomDialog.show();
    }


}
