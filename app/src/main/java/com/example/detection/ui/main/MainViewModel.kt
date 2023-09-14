package com.example.detection.ui.main

import com.example.detection.utils.SharedPreferencesManager
import com.example.detection.bases.BaseViewModel
import com.example.detection.bases.UIEffect
import com.example.detection.bases.UIState
import com.example.detection.bases.UserEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(sharedPreferencesManager: SharedPreferencesManager) :
    BaseViewModel<MainViewModel.Event, MainViewModel.State, MainViewModel.Effect>(State()) {

    class Event : UserEvent() {

    }

    class State : UIState() {

    }

    class Effect : UIEffect() {

    }

    override fun handlerUserEvents(it: Event) {

    }
}

