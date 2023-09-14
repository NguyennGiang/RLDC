package com.example.detection.bases.remote

import TokenManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.HttpURLConnection
import java.util.*

class AuthInterceptor(private val tokenManager: TokenManager): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val requestBuilder = chain.request().newBuilder()
        requestBuilder.header("Accept-Language", Locale.getDefault().language)
        val request = requestBuilder.build()

        val bearerToken = tokenManager.bearerToken
        val response = chain.proceed(request.withToken(bearerToken))

        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            // Double check if token has been updated in meanwhile
            val newToken = tokenManager.bearerToken
            if (newToken == bearerToken) {
                tokenManager.forceRefreshToken().blockingGet()
            }

            return chain.proceed(request.withToken(tokenManager.bearerToken))
        }

        return response
    }

    private fun Request.withToken(token: String): Request =
        newBuilder().header("Authorization", token).build()
}
