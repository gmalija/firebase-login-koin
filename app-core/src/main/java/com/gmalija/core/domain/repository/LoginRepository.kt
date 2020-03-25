package com.gmalija.core.domain.repository

import com.gmalija.core.domain.entity.LoginUser
import com.gmalija.core.domain.entity.Result
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface LoginRepository {
    suspend fun createAccountWithEmail(email: String, password: String): Result<LoginUser>
    suspend fun loginWithEmail(email: String, password: String): Result<LoginUser>
    suspend fun signInWithGoogleCredential(account: GoogleSignInAccount): Result<LoginUser>
    fun currentUser(): LoginUser?
    fun signOut()
}