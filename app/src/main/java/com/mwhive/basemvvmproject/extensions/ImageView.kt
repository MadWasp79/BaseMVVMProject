package com.mwhive.basemvvmproject.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide


/**
 * Created by Denis Kolomiets on 09-Nov-18.
 */

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}