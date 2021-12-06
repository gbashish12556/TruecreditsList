package com.example.truecreditslist

import android.app.Application
import com.example.truecreditslist.api.TruecreditsApi
import com.example.truecreditslist.repository.TruecreditPostRepo


class TruecreditsApplication : Application() {

    val serviceLocator:ServiceLocator
       get() = DefaultServiceLocator(this);

    val prRepository: TruecreditPostRepo
        get() = serviceLocator.getRepository()


    override fun onCreate() {
        super.onCreate()
//        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

}