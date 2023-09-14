package com.example.detection.rx

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AndroidSchedulers : SchedulersProvider {

    @Inject
    fun AndroidSchedulers() {}

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun computation(): Scheduler {
        return Schedulers.computation();
    }

    override fun ui(): Scheduler {
        return io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread()
    }
}