package com.mwhive.basemvvmproject.data.remote


/**
 * Created by Denis Kolomiets on 08-Nov-18.
 */

interface Api{

    /**
     * Example:
     *
    @Headers("Accept-Language: en")
    @POST("user/sign-in")
    fun getSignInRequest(
    @Body signInRequest: SignInRequest
    ): Flowable<ResponseBody<List<SuccessAuthTokenModel>>>
     *
     */
}