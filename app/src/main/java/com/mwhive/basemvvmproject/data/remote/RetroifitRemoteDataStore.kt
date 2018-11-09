package com.mwhive.basemvvmproject.data.remote


/**
 * Created by Denis Kolomiets on 08-Nov-18.
 */
object RetroifitRemoteDataStore: RemoteDataStore {
    private val api: Api by lazy { RetrofitCreator.initApi() }

    /**
     * example:
     * override fun getUserFromApi(userId: Int) = api.getUser(userId)
     * override fun getFAQData() = api.getFAQData()
     */
}