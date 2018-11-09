package com.mwhive.basemvvmproject.extensions

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mwhive.basemvvmproject.BuildConfig


/**
 * Created by Denis Kolomiets on 09-Nov-18.
 */

fun Context.toastD(message: String?) {
    if (BuildConfig.DEBUG)
        message?.let {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
}

fun Context.toast(message: String?) {
    message?.let {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

fun Fragment.toastD(message: String?) {
    if (BuildConfig.DEBUG)
        message?.let {
            Toast.makeText(this.activity, message, Toast.LENGTH_SHORT).show()
        }
}

fun Fragment.toast(message: String?) {
    message?.let {
        Toast.makeText(this.activity, message, Toast.LENGTH_SHORT).show()
    }
}