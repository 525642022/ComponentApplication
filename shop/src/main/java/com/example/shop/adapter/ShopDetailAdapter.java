package com.example.shop.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.example.shop.R;
import com.example.utils.glide.ProgressTarget;
import com.example.utils.utilcode.util.ScreenUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;


import java.io.File;
import java.util.List;

public class ShopDetailAdapter extends CommonAdapter<String> {

    public ShopDetailAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
        ImageView item_shop_detail_iv = holder.getView(R.id.item_shop_detail_iv);
        Glide.with(mContext).load(s).downloadOnly(new ProgressTarget<String, File>(s, null) {
            @Override
            public void onLoadStarted(Drawable placeholder) {
                super.onLoadStarted(placeholder);
            }

            @Override
            public void onProgress(long bytesRead, long expectedLength) {
                int p = 0;
                if (expectedLength >= 0) {
                    p = (int) (100 * bytesRead / expectedLength);
                }

            }

            @Override
            public void onResourceReady(File resource, GlideAnimation<? super File> animation) {
                super.onResourceReady(resource, animation);
                Bitmap bitmap = BitmapFactory.decodeFile(resource.toString());
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) item_shop_detail_iv.getLayoutParams();
                int width = ScreenUtils.getScreenWidth();
                int height = (int) (width * ((float)bitmap.getHeight() / (float) bitmap.getWidth()));
                layoutParams.width = width;
                layoutParams.height = (int) height;
                item_shop_detail_iv.setLayoutParams(layoutParams);
                item_shop_detail_iv.setImageBitmap(bitmap);
            }
            @Override
            public void getSize(SizeReadyCallback cb) {
                cb.onSizeReady(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
            }
        });
    }
}
