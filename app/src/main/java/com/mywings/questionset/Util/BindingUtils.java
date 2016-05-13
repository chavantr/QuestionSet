package com.mywings.questionset.Util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Tatyabhau Chavan on 11/4/2015.
 */
public class BindingUtils {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, int id) {
        Picasso.with(view.getContext()).load(id).into(view);
    }

    @BindingAdapter("bind:drawableLeft")
    public static void setDrawableLeft(TextView view, Drawable drawable) {
        view.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
    }
}
