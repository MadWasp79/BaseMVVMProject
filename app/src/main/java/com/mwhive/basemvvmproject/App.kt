package com.mwhive.basemvvmproject

import android.app.Application
import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber


class App : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
//        todo: if we don't need database - comment:
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    companion object {
        private var instance: App? = null

        fun applicationContext(): Context = instance!!.applicationContext
    }
}