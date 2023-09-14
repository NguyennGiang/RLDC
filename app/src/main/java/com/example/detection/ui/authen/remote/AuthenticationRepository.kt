package com.example.detection.ui.authen.remote

import com.example.detection.ui.authen.remote.AuthenticationServerConnector
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

}
