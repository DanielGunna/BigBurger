package com.gunna.bigburger.androidapp.app;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import com.gunna.bigburger.androidapp.R;
import com.squareup.picasso.Picasso;

public class DataBinder {

    @BindingAdapter("visibility")
    public static void setVisibility(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView view, String url) {
        Picasso.get()
                .load(url)
                .centerCrop()
                .fit()
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_image)
                .into(view);
    }
}
