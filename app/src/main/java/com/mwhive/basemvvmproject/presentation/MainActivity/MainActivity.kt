package com.mwhive.basemvvmproject.presentation.MainActivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mwhive.basemvvmproject.R
import com.mwhive.basemvvmproject.base.BaseActivity

class MainActivity : BaseActivity<MainActivityViewModel>() {

    override fun layoutResId(): Int = R.layout.activity_main

    override fun viewModelClass(): Class<MainActivityViewModel> = MainActivityViewModel::class.java

    override fun onChangeProgressBarVisibility(isVisible: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onShowError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
