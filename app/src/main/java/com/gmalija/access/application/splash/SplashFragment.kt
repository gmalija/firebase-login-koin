package com.gmalija.access.application.splash

import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gmalija.access.R
import com.gmalija.access.application.base.BaseFragment
import com.gmalija.access.databinding.FragmentSplashBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment: BaseFragment<FragmentSplashBinding, SplashViewModel>() {
    override val mViewModel: SplashViewModel by viewModel()
    override fun getLayoutResId() = R.layout.fragment_splash

    override fun init(view: View) {
        mViewModel.isCurrentUser.observe(this, Observer {
            if(it) {
                goToHome()
            } else {
                goToSignIn()
            }
        })
    }

    private fun goToSignIn() {
        val directions = SplashFragmentDirections.actionSplashFragmentToSignInNavigation()
        findNavController().navigate(directions)
    }

    private fun goToHome() {
        val directions = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
        findNavController().navigate(directions)
    }

}