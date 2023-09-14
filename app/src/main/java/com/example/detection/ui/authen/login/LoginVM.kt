package com.example.detection.ui.authen.login

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
class LoginVM @Inject constructor(sharedPreferencesManager: SharedPreferencesManager): BaseViewModel<LoginVM.Event, LoginVM.State, LoginVM.Effect>(State()){

    sealed class Event: UserEvent() {
        data class Login(val email: String, val password: String): Event()
    }
    data class State(val loading: Boolean = false): UIState()
    sealed class Effect: UIEffect() {
        data class ShowMessage(val message: String): Effect()
        object GoToHomePage : Effect()
    }

    override fun handlerUserEvents(event: Event) {
        if (event is Event.Login){
            setState { copy(loading = true) }

            setState { copy(loading = false) }
        }
    }
}