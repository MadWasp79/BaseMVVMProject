package com.mwhive.basemvvmproject.data.remote.interceptors

import android.util.Base64
import com.mwhive.basemvvmproject.data.remote.RemoteSettings
import com.mwhive.basemvvmproject.extensions.falseIfNull

import okhttp3.*
import okio.Buffer
import okio.ByteString.decodeBase64
import timber.log.Timber
import java.io.IOException
import java.nio.charset.Charset

// TODO remove logs
class Base64Interceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val isMultipart = originalRequest.body()?.contentType()?.toString()?.contains(CONTENT_TYPE_MULTIPART)
        val url = originalRequest?.url().toString()
        val googleRequest = url.isEmpty().not() && url.contains(RemoteSettings.BASE_URL).not()
        val request = if (isMultipart.falseIfNull() || googleRequest) originalRequest else getModifiedRequest(originalRequest)
        log("originalRequest", originalRequest)
        log("modifiedRequest", request)
        val originalResponse = chain.proceed(request)
        // TODO set type application/json;
        /*val isContentTypeJson = originalResponse?.header(HEADER_PARAMETER_CONTENT_TYPE)
                .equals(CONTENT_TYPE_JSON)
        return if (isContentTypeJson && !googleRequest) {
            val modifiedResponse = decodeResponseFromBase64(originalResponse)
            log("modifiedResponse", modifiedResponse)
            modifiedResponse
        } else {
            originalResponse
        }*/
        val modifiedResponse = decodeResponseFromBase64(originalResponse)
        log("modifiedResponse", modifiedResponse)
        return modifiedResponse
    }

    // request
    private fun getModifiedRequest(originalRequest: Request): Request {
        return if (originalRequest.body() == null) {
            modifiedRequestCreator(originalRequest, originalRequest.body())
        } else {
            val modifiedRequestBody = getModifiedRequestBody(originalRequest)
            modifiedRequestCreator(originalRequest, modifiedRequestBody)
        }
    }

    // request
    private fun modifiedRequestCreator(originalRequest: Request, body: RequestBody?): Request {
        return originalRequest.newBuilder()
                .method(originalRequest.method(), body)
                .build()
    }

    // request
    private fun getModifiedRequestBody(originalRequest: Request): RequestBody? {
        val originalRequestBodyString = bodyToString(originalRequest.body())
        Timber.d("$TAG_BASE_64 originalRequestBodyString: $originalRequestBodyString")
        val data = originalRequestBodyString.toString().toByteArray(Charset.forName(CHARSET_UTF_8))
        val encodedDataBody = Base64.encodeToString(data, Base64.DEFAULT).replace("\n", "")
        Timber.d("$TAG_BASE_64 encodedDataBody: $encodedDataBody")
        return RequestBody.create(originalRequest.body()?.contentType(), encodedDataBody)
    }

    // request
    private fun bodyToString(request: RequestBody?): String? {
        try {
            val buffer = Buffer()
            if (request != null) {
                request.writeTo(buffer)
            } else {
                return null
            }
            return buffer.readUtf8()
        } catch (e: IOException) {
            Timber.d("did not work")
            return null
        }
    }

    // response
    private fun decodeResponseFromBase64(originalResponse: Response): Response {
        val source = originalResponse.body()?.source()
        source?.request(Long.MAX_VALUE)
        val buffer = source?.buffer()
        val contentType = originalResponse.body()?.contentType()
        var responseBodyString = buffer?.clone()?.readString(Charset.forName(CHARSET_UTF_8))
        Timber.d("$TAG_BASE_64 originalResponseBodyString: $responseBodyString")
        responseBodyString = decodeBase64(responseBodyString!!)
                ?.string(Charset.forName(CHARSET_UTF_8))
        Timber.d("$TAG_BASE_64 modifiedResponseBodyString: $responseBodyString")
        val newBody = ResponseBody.create(contentType, responseBodyString ?: "")
        return originalResponse.newBuilder()
                .body(newBody)
                .build()
    }

    // log
    private fun log(tag: String, request: Request?) {
        Timber.d("$TAG_BASE_64 $tag method: ${request?.method()}")
        Timber.d("$TAG_BASE_64 $tag headers: ${request?.headers()}")
    }

    // log
    private fun log(tag: String, response: Response?) {
        Timber.d("$TAG_BASE_64 $tag headers: ${response?.headers()}")
    }

    companion object {
        const val CHARSET_UTF_8 = "UTF-8"

        private const val TAG_BASE_64 = "BASE_64_INTERCEPTOR"

        private const val HEADER_PARAMETER_CONTENT_TYPE = "Content-Type"
        private const val CONTENT_TYPE_JSON = "application/json; charset=UTF-8"
        private const val CONTENT_TYPE_MULTIPART = "multipart/form-data"
    }
}