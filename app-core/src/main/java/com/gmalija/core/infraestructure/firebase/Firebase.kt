package com.gmalija.core.infraestructure.firebase

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface Firebase {
    fun createAccountWithEmail(email: String, password: String): Task<AuthResult>
    fun loginWithEmail(email: String, password: String): Task<AuthResult>
    fun signInWithGoogleCredential(account: GoogleSignInAccount): Task<AuthResult>
    fun currentUser(): FirebaseUser?
    fun signOut()
}