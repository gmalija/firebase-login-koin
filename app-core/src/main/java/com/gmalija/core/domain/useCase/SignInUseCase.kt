package com.gmalija.core.domain.useCase

import com.gmalija.core.domain.repository.LoginRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class SignInUseCase(private val loginRepository: LoginRepository) {
    suspend fun createAccountWithEmail(name: String, email: String) = loginRepository.createAccountWithEmail(name, email)
    suspend fun loginWithEmail(name: String, email: String) = loginRepository.loginWithEmail(name, email)
    suspend fun signInWithGoogleCredential(account: GoogleSignInAccount) = loginRepository.signInWithGoogleCredential(account)
}