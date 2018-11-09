package com.mwhive.basemvvmproject.extensions
import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SimpleDateFormat")
const val HMDMY = "HH:mm dd.MM.yyyy"

// transform timestamp by template
fun Int?.toFormattedDateTime(template: String = "HH:mm dd.MM.yyyy" ): String{
    try {
        val sdf = SimpleDateFormat(template)
        val netDate = Date(this!!.toLong()*1000)
        return sdf.format(netDate)
    } catch (e: Exception) {
        return "Error. Check input data."
    }
}