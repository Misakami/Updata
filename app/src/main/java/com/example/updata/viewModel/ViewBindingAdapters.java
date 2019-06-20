package com.example.updata.viewModel;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class ViewBindingAdapters {
    @BindingAdapter("imgUrl")
    public static void setimgUrl(ImageView imageView,Object url){
        Glide.with(imageView).load(url).apply(new RequestOptions().circleCrop())
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }
}
