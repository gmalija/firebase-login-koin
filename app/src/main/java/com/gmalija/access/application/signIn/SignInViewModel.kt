package com.gmalija.access.application.signIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gmalija.access.application.base.BaseViewModel
import com.gmalija.core.domain.entity.LoginUser
import com.gmalija.core.domain.entity.Result
import com.gmalija.core.domain.useCase.SignInUseCase
import com.gmalija.core.domain.utils.SingleLiveEvent
import com.gmalija.core.domain.utils.isEmailValid
import com.gmalija.core.domain.utils.isPasswordValid
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class SignInViewModel(private val signInUseCase: SignInUseCase) : BaseViewModel() {

    val userBinding = LoginUser()

    private val _user = SingleLiveEvent<Result<LoginUser>>()
    val user : LiveData<Result<LoginUser>>
        get() = _user

    fun onLogin() {

        // Get data
        val email = userBinding.email ?: ""
        val password = userBinding.password ?: ""

        // Check email and password to continue
        when {
            !email.isEmailValid() -> {
                _user.value = Result.errorString("Wrong email (Ex: user@gmail.com)")
            }
            !password.isPasswordValid() -> {
                _user.value = Result.errorString("Wrong password (Ex: test2020)")
            }
            else -> {
                viewModelScope.launch {
                    _user.value = Result.loading()
                    _user.value = signInUseCase.loginWithEmail(email, password)
                }
            }
        }
    }

    fun onRegisterEmail() {

        // Get data
        val email = userBinding.email ?: ""
        val password = userBinding.password ?: ""

        // Check email and password to continue
        when {
            !email.isEmailValid() -> {
                _user.value = Result.errorString("Wrong email (Ex: user@gmail.com)")
            }
            !password.isPasswordValid() -> {
                _user.value = Result.errorString("Wrong password (Ex: test2020)")
            }
            else -> {
                viewModelScope.launch {
                    _user.value = Result.loading()
                    _user.value = signInUseCase.createAccountWithEmail(email, password)
                }
            }
        }
        
    }

    fun onRegisterGoogle(account: GoogleSignInAccount) {
        viewModelScope.launch {
            _user.value = Result.loading()
            _user.value = signInUseCase.signInWithGoogleCredential(account)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}