package com.gmalija.access.application.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_login.*

abstract class BaseFragment<binding : ViewDataBinding, viewModel : BaseViewModel> : Fragment() {

    protected abstract val mViewModel: viewModel
    protected lateinit var bindingObject: binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingObject = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        return bindingObject.root
    }

    /**
     * Get layout resource id which inflate in onCreateView.
     */
    abstract fun getLayoutResId(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doDataBinding(view)
    }

    /**
     * Do your other stuff in init after binding layout.
     */
    abstract fun init(view: View)

    private fun doDataBinding(view: View) {
        // it is extra if you want to set life cycle owner in binding
        bindingObject.lifecycleOwner = viewLifecycleOwner

        // Here your viewModel and binding variable imlementation
        bindingObject.setVariable(BR.viewModel, mViewModel)

        // In all layout the variable name should be "viewModel"
        bindingObject.executePendingBindings()

        init(view)
    }

    fun hideProgress(){
        progress.visibility = GONE
    }

    fun showProgress(){
        progress.visibility = VISIBLE
    }

    fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }

}