package com.example.detection.bases.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class RetrofitServerConnector<API> {
    protected abstract fun getAPIClass(): Class<API>
    private var serviceUrl = ""
    protected val api : API
        get() = initializeRetrofit().create(getAPIClass())

    private fun initializeRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(serviceUrl)
            .client(defaultHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    protected open val defaultHttpClient: OkHttpClient
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .addNetworkInterceptor(loggingInterceptor)
            return client.build()
        }

}