package  com.mwhive.basemvvmproject.utils

import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

fun setFocusableView(isFocusable: Boolean, vararg views: EditText?) {
    for (view in views)
        view?.apply {
            this.isCursorVisible = isFocusable
            this.isFocusableInTouchMode = isFocusable
        }
}

fun changeTextColorView(@ColorRes colorRes: Int, vararg views: TextView?) {
    for (view in views)
        view?.apply {
            this.setTextColor(ContextCompat.getColor(this.context, colorRes))
        }
}

fun setTextProfile(textView: TextView?, text: String?) {
    textView?.apply {
        if (text != null && text.isNotEmpty())
            this.text = text
        else
            this.text = ""
    }
}

fun setImageProfile(imageView: ImageView?, url: String, @DrawableRes drawableRes: Int) {
    imageView?.apply {
        if (url.isNotEmpty())
            Glide.with(this).load(url).into(this)
        else
            Glide.with(this).load(drawableRes).into(this)
    }
}