package com.example.detection.bases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.detection.rx.SchedulersProvider
import com.example.detection.utils.SharedPreferencesManager
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<Event: UserEvent, State: UIState, Effect: UIEffect> (
    val schedulers: SchedulersProvider,
    val sharedPreferencesManager: SharedPreferencesManager
        ) : ViewModel() {

    private val initialState : State by lazy { createInitialState() }

    abstract fun createInitialState() : State

    private val compositeDisposable = CompositeDisposable()
    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _userEvent = MutableSharedFlow<Event>()
    private val userEvent = _userEvent.asSharedFlow()

    private val _effect = MutableSharedFlow<Effect>()
    val effect = _effect.asSharedFlow()

    open val currentState: State
        get() = _state.value

    init {
        viewModelScope.launch {
            userEvent.collect() {
                handlerUserEvents(it)
            }
        }
    }

     protected abstract fun handlerUserEvents(event: Event)

     fun addDisposable(disposable: Disposable) {
         compositeDisposable.add(disposable)
     }

     protected fun setState(reduce: State.() -> State){
         val newState = reduce(currentState)
         _state.value = newState
     }

    protected fun setEffect(newEffect: Effect){
        viewModelScope.launch {
            _effect.emit(newEffect)
        }
    }

    fun setUserEvent(event: Event){
        viewModelScope.launch {
            _userEvent.emit(event)
        }
    }

    override fun onCleared() {
        clearCompositeDisposable()
        super.onCleared()
    }

    private fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }

}

abstract class UIState
abstract class UserEvent
abstract class UIEffect