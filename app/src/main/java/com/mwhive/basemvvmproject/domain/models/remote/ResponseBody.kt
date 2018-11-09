package com.mwhive.basemvvmproject.domain.models.remote

import com.google.gson.annotations.SerializedName

class ResponseBody<D> {

    @SerializedName("status")
    var status: String? = null

    @SerializedName("error_code")
    var errorCode: Int? = 0

    @SerializedName("error_message")
    var errorMessage: String? = null

    @SerializedName("data")
    var data: D? = null
}