package net.vjet.agentplus.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.net.ConnectivityManager
import android.os.Build
import android.provider.Settings
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.mwhive.basemvvmproject.BuildConfig
import com.mwhive.basemvvmproject.R



@SuppressLint("HardwareIds")
fun Context.deviceId(): String {
    return Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)
}


fun Context.hasNetworkConnection(): Boolean {
    val activeNetworkInfo = getConnectivityManager().activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}

fun Context.getConnectivityManager() =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


fun Context.hideKeyboard() {
    val activity = this as? Activity ?: if (this is Fragment) this.activity else null
    activity ?: return

    val view = activity.currentFocus
    if (view != null) {
        (activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}

fun Context.setSystemBarLight() {
    val activity = this as? Activity ?: if (this is Fragment) this.activity else null
    activity ?: return

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val view = activity.findViewById<View>(android.R.id.content)
        var flags = view.systemUiVisibility
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        view.systemUiVisibility = flags
    }
}

fun Context.clearSystemBarLight() {
    val activity = this as? Activity ?: if (this is Fragment) this.activity else null
    activity ?: return

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val window = activity.window
        window.statusBarColor = ContextCompat.getColor(activity, R.color.colorPrimaryDark)
    }
}

fun Context.setSystemBarColor(@ColorRes color: Int) {
    val activity = this as? Activity ?: if (this is Fragment) this.activity else null
    activity ?: return

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val window = activity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = activity.resources.getColor(color)
    }
}

fun Context.getStatusBarHeight(): Int {
    val activity = this as? Activity ?: if (this is Fragment) this.activity else null
    activity ?: return 0

    val rectangle = Rect()
    val window = activity.window
    window.decorView.getWindowVisibleDisplayFrame(rectangle)
    val statusBarHeight = rectangle.top
    val contentViewTop = window.findViewById<View>(Window.ID_ANDROID_CONTENT).top
    return contentViewTop - statusBarHeight
}

fun isLollipopOrAbove(func: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        func()
    }
}

fun isKitkatOrAbove(func: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        func()
    }
}