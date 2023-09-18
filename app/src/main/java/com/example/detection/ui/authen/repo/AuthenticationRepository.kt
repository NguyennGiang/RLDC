package com.example.detection.ui.authen.repo

import com.example.detection.ui.authen.remote.AuthenticationServerConnector
import com.example.detection.ui.authen.remote.models.LoginRequest
import com.example.detection.ui.authen.remote.models.LoginResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val authenticationServerConnector: AuthenticationServerConnector,
) {

    fun refreshToken(): Single<Boolean> =
        Single.just(true)

    fun getToken(): String {
        return ""
    }

    fun login(loginRequest: LoginRequest) : Single<LoginResponse> = authenticationServerConnector.login(loginRequest)

}
