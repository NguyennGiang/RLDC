package com.example.detection.rx

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RxSchedulersModule {
    @Binds
    abstract fun bindSchedulersProvider(schedulers: AndroidSchedulers?): SchedulersProvider?
}