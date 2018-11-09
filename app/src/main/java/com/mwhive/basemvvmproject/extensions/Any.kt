package com.mwhive.basemvvmproject.extensions

fun Any?.toBoolean(defValue: Boolean = false): Boolean {
    return when {
        this is Boolean? -> this.falseIfNull()
        this is Int -> this > 0
        else -> defValue
    }
}

fun Any?.toInt(defValue: Int = 0): Int {
    return when {
        this is Int -> this
        else -> defValue
    }
}

fun Any?.toString(defValue: String? = null): String? {
    return when {
        this is String -> this

        else -> defValue
    }
}

fun Any?.toDouble(defValue: Double = 0.0): Double {
    return when {
        this is Double -> this

        else -> defValue
    }
}

