package com.example.detection.ui.authen.remote

import com.example.detection.ui.authen.remote.models.LoginRequest
import com.example.detection.ui.authen.remote.models.LoginResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

// end point
interface AuthenticationAPI {
    @POST("/users/login")
    fun login(@Body loginRequest: LoginRequest): Single<LoginResponse>

}
