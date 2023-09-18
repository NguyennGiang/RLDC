package com.example.detection.ui.authen.login

import com.example.detection.bases.BaseViewModel
import com.example.detection.bases.UIEffect
import com.example.detection.bases.UIState
import com.example.detection.bases.UserEvent
import com.example.detection.rx.SchedulersProvider
import com.example.detection.ui.authen.remote.models.LoginRequest
import com.example.detection.ui.authen.repo.AuthenticationRepository
import com.example.detection.utils.SharedPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(
    schedulers: SchedulersProvider,
    sharedPreferencesManager: SharedPreferencesManager,
    private val authenticationRepository: AuthenticationRepository
) : BaseViewModel<LoginVM.Event, LoginVM.State, LoginVM.Effect>(
    schedulers,
    sharedPreferencesManager
) {

    sealed class Event : UserEvent() {
        data class Login(val email: String, val password: String) : Event()
    }

    data class State(val isLoginSuccess: Boolean = false, val loading: Boolean = false) : UIState()
    sealed class Effect : UIEffect() {
        data class ShowMessage(val message: String) : Effect()
        object GoToHomePage : Effect()
    }

    override fun handlerUserEvents(event: Event) {
        if (event is Event.Login) {

            addDisposable(authenticationRepository.login(LoginRequest(event.email, event.password))
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .doOnSubscribe { _ -> setState { copy(loading = true) } }
                .doOnTerminate { setState { copy(loading = false) } }
                .subscribe({ setState { copy(isLoginSuccess = true) } }, { })
            )
        }
    }

    override fun createInitialState(): State {
        return State()
    }
}