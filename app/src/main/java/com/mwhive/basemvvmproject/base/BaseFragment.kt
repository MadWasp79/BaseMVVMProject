package com.mwhive.basemvvmproject.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mwhive.basemvvmproject.extensions.inflate


/**
 * Created by Denis Kolomiets on 09-Nov-18.
 */

abstract class BaseFragment<VM : BaseViewModel> :Fragment(){
    protected  val viewModel: VM by lazy { setupViewModel() }

    @LayoutRes
    protected abstract fun layoutResId(): Int

    protected abstract fun viewModelClass(): Class<VM>

    protected abstract fun onChangeProgressBarVisibility(isVisible: Boolean)

    protected abstract fun onShowError(message: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        onBindLiveData()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = container?.inflate(layoutResId())

    protected open fun onBindLiveData() {
        observe(viewModel.progressBarVisibility, ::onChangeProgressBarVisibility)
        observe(viewModel.errorVisibility, ::onShowError)
    }

    protected fun <T, LD : LiveData<T>> observe(liveData: LD, onChanged: (T) -> Unit) {
        liveData.observe(this, Observer { value ->
            value?.let(onChanged)
        })
    }

    protected fun changTextColor(button: Button?, @ColorRes colorId: Int) {
        button?.setTextColor(ContextCompat.getColor(context!!, colorId))
    }

    private fun setupViewModel() = ViewModelProviders.of(this).get(viewModelClass())
}