package com.mwhive.basemvvmproject.extensions

fun Boolean?.falseIfNull() = this ?: false

fun Boolean?.toInt() = if (this == true) 1 else 0