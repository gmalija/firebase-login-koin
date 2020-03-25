package com.gmalija.access.application.signIn

import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gmalija.access.R
import com.gmalija.access.application.base.BaseFragment
import com.gmalija.access.databinding.FragmentRegisterBinding
import com.gmalija.core.domain.entity.LoginUser
import com.gmalija.core.domain.entity.Result
import com.gmalija.core.domain.entity.Status
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.sharedViewModel

class RegisterFragment: BaseFragment<FragmentRegisterBinding, SignInViewModel>() {

    override val mViewModel: SignInViewModel by sharedViewModel()
    override fun getLayoutResId() = R.layout.fragment_register

    override fun init(view: View) {
        mViewModel.user.observe(this, Observer { render(it, view) })
        bindingObject.setVariable(BR.user, mViewModel.userBinding)
    }

    private fun render(result: Result<LoginUser>, view: View) {

        when(result.status) {
            Status.SUCCESS -> {
                hideProgress()
                Snackbar.make(view, "Welcome ${result.data?.email}", Snackbar.LENGTH_LONG).show()
                goToHome()
            }
            Status.LOADING -> {
                showProgress()
            }
            else -> {
                hideProgress()
                Snackbar.make(view, result.message ?: "Error", Snackbar.LENGTH_LONG).show()
            }
        }
        hideKeyboard()

    }

    private fun goToHome() {
        val directions = RegisterFragmentDirections.actionGlobalHomeFragment()
        findNavController().navigate(directions)
    }

}