package com.mwhive.basemvvmproject.extensions

fun <T> Array<T>.isValidIndex(index: Int? = 0): Boolean {
    if (index != null) {
        return index >= 0 && index < this.size
    }

    return false
}

fun <T> List<T>.isValidIndex(index: Int? = 0): Boolean {
    if (index != null) {
        return index >= 0 && index < this.size
    }

    return false
}