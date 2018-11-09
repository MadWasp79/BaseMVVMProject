package com.mwhive.basemvvmproject.data.remote.interceptors

import com.mwhive.basemvvmproject.domain.Repository
import okhttp3.Interceptor
import okhttp3.Response

// TODO remove logs
class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        val newRequest = chain?.request()?.newBuilder()
//            ?.addHeader(TOKEN, Repository.authKeyFrom())
//            ?.addHeader(ACCEPT_LANGUAGE, Repository.getCurrentLanguageCodeForRemote())
            ?.build()
        return chain?.proceed(newRequest!!)!!
    }

    companion object {
        private const val TOKEN = "token"
        private const val ACCEPT_LANGUAGE = "Accept-Language"
    }
}