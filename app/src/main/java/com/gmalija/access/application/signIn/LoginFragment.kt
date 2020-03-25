package com.gmalija.access.application.signIn

import android.content.Intent
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gmalija.access.R
import com.gmalija.access.application.base.BaseFragment
import com.gmalija.access.databinding.FragmentLoginBinding
import com.gmalija.core.domain.entity.LoginUser
import com.gmalija.core.domain.entity.Result
import com.gmalija.core.domain.entity.Status
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class LoginFragment: BaseFragment<FragmentLoginBinding, SignInViewModel>() {

    override val mViewModel: SignInViewModel by sharedViewModel()
    override fun getLayoutResId() = R.layout.fragment_login
    private val RC_SIGN_IN = 1000

    override fun init(view: View) {
        mViewModel.user.observe(this, Observer { render(it, view) })
        bindingObject.setVariable(BR.fragment, this)
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

    fun goToRegister() {
        val directions = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        findNavController().navigate(directions)
    }

    private fun goToHome() {
        val directions = LoginFragmentDirections.actionGlobalHomeFragment()
        findNavController().navigate(directions)
    }

    fun onLaunchRegisterGoogle() {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val googleSignInClient = context?.let { GoogleSignIn.getClient(it, gso) }

        val signInIntent = googleSignInClient?.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                account?.let { mViewModel.onRegisterGoogle(it) }
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Timber.e("Google sign in failed: ${e.message}")
            }
        }

    }

}