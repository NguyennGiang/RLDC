package com.example.detection.ui.authen.login

import com.example.detection.Utils.SharedPreferencesManager
import com.example.detection.bases.BaseViewModel
import com.example.detection.bases.UIEffect
import com.example.detection.bases.UIState
import com.example.detection.bases.UserEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(sharedPreferencesManager: SharedPreferencesManager): BaseViewModel<LoginVM.Event, LoginVM.State, LoginVM.Effect>(State()){
    sealed class Event: UserEvent()
    data class State(val boolean: Boolean = false): UIState()
    sealed class Effect: UIEffect()

    override fun handlerUserEvents(event: Event) {

    }
}