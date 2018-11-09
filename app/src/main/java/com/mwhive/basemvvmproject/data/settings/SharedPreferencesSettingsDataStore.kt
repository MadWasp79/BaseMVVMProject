package com.mwhive.basemvvmproject.data.settings

import android.content.Context
import android.content.SharedPreferences
import com.mwhive.basemvvmproject.App


object SharedPreferencesSettingsDataStore: SettingsDataStore {
    private const val SETTINGS_FILE = "SettingsFile" //add app name

    private fun getSharedPreferences(): SharedPreferences {
        val applicationContext = App.applicationContext()
        return applicationContext.getSharedPreferences(SETTINGS_FILE, Context.MODE_PRIVATE)
    }

}