package com.example.detection.ui.authen.remote

import com.example.detection.bases.remote.RetrofitServerConnector
import javax.inject.Inject

class AuthenticationServerConnector @Inject constructor() :
    RetrofitServerConnector<AuthenticationAPI>(){
    override fun getAPIClass(): Class<AuthenticationAPI> {
        return AuthenticationAPI::class.java
    }

}
