package com.mwhive.basemvvmproject.data.remote

import com.google.gson.GsonBuilder
import com.mwhive.basemvvmproject.data.remote.interceptors.Base64Interceptor
import com.mwhive.basemvvmproject.data.remote.interceptors.HeaderInterceptor
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Denis Kolomiets on 08-Nov-18.
 */

object RetrofitCreator {

    private val moshi by lazy {
        Moshi.Builder()
            //add here any custom interceptors
            .build()
    }

    private val gson by lazy {
        GsonBuilder()
            .setLenient()
            .create()
    }

    fun initApi(): Api {
        val retrofit = initRetrofit()
        return retrofit.create(Api::class.java)
    }

    private fun initRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val base64Interceptor = Base64Interceptor()
        val headerInterceptor = HeaderInterceptor()

        val client = OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
            addInterceptor(base64Interceptor)
            addInterceptor(headerInterceptor)
            readTimeout(RemoteSettings.READ_TIMEOUT, TimeUnit.SECONDS)
            connectTimeout(RemoteSettings.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(RemoteSettings.WRITE_TIMEOUT, TimeUnit.SECONDS)
        }

        return Retrofit.Builder()
            .baseUrl(RemoteSettings.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(MoshiConverterFactory.create(moshi)) //todo select moshi or gson
            //.addConverterFactory(GsonConverterFactory.create(gson))
            .client(client.build())
            .build()
    }

}


