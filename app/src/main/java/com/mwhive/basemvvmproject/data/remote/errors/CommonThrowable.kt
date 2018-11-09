package com.mwhive.basemvvmproject.data.remote.errors

class CommonThrowable(
        message: String,
        val errorsMap: Map<String, String>? = null
) : Throwable(message)