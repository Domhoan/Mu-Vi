package com.example.muvi.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.muvi.R
import com.example.muvi.utils.showToast

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    @get: LayoutRes
    protected abstract val layoutResource: Int

    abstract val viewModel: RxViewModel

    private var _binding: T? = null

    protected val binding: T
        get() = _binding ?: throw IllegalStateException(EXCEPTION)

    protected abstract fun initData()

    private var bottomNavigationListener: BottomNavigationListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BottomNavigationListener) bottomNavigationListener = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        DataBindingUtil
            .inflate<T>(inflater, layoutResource, container, false)
            .apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.errorException.observe(viewLifecycleOwner, Observer {
            view.context.showToast(getString(R.string.err_default))
        })
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun showToast(msg: String) = requireContext().showToast(msg)

    open fun onBackPress() {
        bottomNavigationListener?.showNav()
    }

    companion object {
        private const val EXCEPTION = "Binding only is valid after onCreateView"
    }
}
