package com.mwhive.basemvvmproject.data.remote.errors

import com.mwhive.basemvvmproject.App
import com.mwhive.basemvvmproject.R


class NoInternetConnectionException
    : Exception(App.applicationContext().getString(R.string.exception_no_internet_connection))