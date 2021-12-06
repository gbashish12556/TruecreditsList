package com.example.truecreditslist

import android.app.Application
import android.content.Context
import androidx.annotation.VisibleForTesting
import com.example.truecreditslist.api.TruecreditsApi
import com.example.truecreditslist.db.TruecreditsDb
import com.example.truecreditslist.repository.TruecreditPostRepo
import com.example.truecreditslist.repository.TruecreditRepo

interface ServiceLocator {
    companion object {
        private val LOCK = Any()
        private var instance: ServiceLocator? = null
        fun instance(context: Context): ServiceLocator {
            synchronized(LOCK) {
                if (instance == null) {
                    instance = DefaultServiceLocator(
                        app = context.applicationContext as Application)
                }
                return instance!!
            }
        }

        /**
         * Allows tests to replace the default implementations.
         */
        @VisibleForTesting
        fun swap(locator: ServiceLocator) {
            instance = locator
        }
    }

    fun getRepository(): TruecreditPostRepo

    fun getTrueCreditsApi(): TruecreditsApi
}

/**
 * default implementation of ServiceLocator that uses production endpoints.
 */
open class DefaultServiceLocator(val app: Application) : ServiceLocator {
    private val db by lazy {
        TruecreditsDb.create(app)
    }

    private val api by lazy {
        TruecreditsApi.create()
    }

    override fun getRepository(): TruecreditPostRepo {
         return TruecreditRepo(
                db = db,
                truecreditsApi = getTrueCreditsApi()
            )
    }

    override fun getTrueCreditsApi(): TruecreditsApi = api
}