package com.example.detection.ui.main

import com.example.detection.utils.SharedPreferencesManager
import com.example.detection.bases.BaseViewModel
import com.example.detection.bases.UIEffect
import com.example.detection.bases.UIState
import com.example.detection.bases.UserEvent
import com.example.detection.rx.SchedulersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(schedulers: SchedulersProvider,
                                        sharedPreferencesManager: SharedPreferencesManager) :
    BaseViewModel<MainViewModel.Event, MainViewModel.State, MainViewModel.Effect>(schedulers, sharedPreferencesManager) {

    class Event : UserEvent() {

    }

    class State : UIState() {

    }

    class Effect : UIEffect() {

    }

    override fun handlerUserEvents(it: Event) {

    }

    override fun createInitialState(): State {
        return State()
    }
}

