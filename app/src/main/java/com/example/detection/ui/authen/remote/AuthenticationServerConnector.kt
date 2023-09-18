package com.example.detection.ui.authen.remote

import com.example.detection.bases.remote.RetrofitServerConnector
import com.example.detection.ui.authen.remote.models.LoginRequest
import com.example.detection.ui.authen.remote.models.LoginResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AuthenticationServerConnector @Inject constructor() :
    RetrofitServerConnector<AuthenticationAPI>(){
    override fun getAPIClass(): Class<AuthenticationAPI> {
        return AuthenticationAPI::class.java
    }

    fun login(loginRequest: LoginRequest): Single<LoginResponse> {
        return api.login(loginRequest)
    }

}
