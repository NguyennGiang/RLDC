package com.example.detection.ui.authen.register

import com.example.detection.utils.SharedPreferencesManager
import com.example.detection.bases.BaseViewModel
import com.example.detection.bases.UIEffect
import com.example.detection.bases.UIState
import com.example.detection.bases.UserEvent
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentVM @Inject constructor(
    sharedPreferencesManager: SharedPreferencesManager,
) : BaseViewModel<RegisterFragmentVM.Event, RegisterFragmentVM.State, RegisterFragmentVM.Effect>(
    State()
) {

    sealed class Event : UserEvent() {
        data class RegisterEvent(val email: String, val password: String) : Event()
    }

    data class State(val loading: Boolean = false) : UIState()

    sealed class Effect : UIEffect() {
        object RegisterSuccess : Effect()
        data class Error(val error: Throwable) : Effect()
        data class ShowMessage(val message: String) : Effect()
    }

    override fun handlerUserEvents(event: Event) {
        if (event is Event.RegisterEvent) {
        }
    }
}