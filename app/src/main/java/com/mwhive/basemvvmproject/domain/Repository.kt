package com.mwhive.basemvvmproject.domain

import com.mwhive.basemvvmproject.data.local.LocalDataStore
import com.mwhive.basemvvmproject.data.local.RealmLocalDataStore
import com.mwhive.basemvvmproject.data.remote.RetroifitRemoteDataStore
import com.mwhive.basemvvmproject.data.remote.RemoteDataStore
import com.mwhive.basemvvmproject.data.settings.SettingsDataStore
import com.mwhive.basemvvmproject.data.settings.SharedPreferencesSettingsDataStore


/**
 * Created by Denis Kolomiets on 08-Nov-18.
 */

object Repository: RemoteDataStore, LocalDataStore, SettingsDataStore {

    private val remoteDataStore: RemoteDataStore = RetroifitRemoteDataStore
    private val localDataStore: LocalDataStore = RealmLocalDataStore
    private val settingsDataStore: SettingsDataStore = SharedPreferencesSettingsDataStore

//    fun authKeyFrom():String{
//        return ""
//    }
//
//    fun getCurrentLanguageCodeForRemote():String {
//        return ""
//    }

}