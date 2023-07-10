package com.example.detection.ui.authen.register

import androidx.lifecycle.SavedStateHandle
import com.example.detection.Utils.SharedPreferencesManager
import com.example.detection.bases.BaseViewModel
import com.example.detection.bases.UIEffect
import com.example.detection.bases.UIState
import com.example.detection.bases.UserEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentVM @Inject constructor(
    sharedPreferencesManager: SharedPreferencesManager,
) : BaseViewModel<RegisterFragmentVM.Event, RegisterFragmentVM.State, RegisterFragmentVM.Effect>(
    State()
){
    sealed class Event: UserEvent(){
        data class RegisterEvent(val email: String, val password: String): Event()
    }
    data class State(val loading: Boolean = false): UIState()

    sealed class Effect: UIEffect() {
        object RegisterSuccess: Effect()
        data class Error(val error: Throwable): Effect()
    }

    override fun handlerUserEvents(event: Event) {
        if (event is Event.RegisterEvent){
            setEffect(Effect.RegisterSuccess)
        }
    }
}