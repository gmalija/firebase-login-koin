package com.gmalija.core.domain.repository

import com.gmalija.core.domain.entity.LoginUser
import com.gmalija.core.domain.entity.Result
import com.gmalija.core.infraestructure.firebase.Firebase
import com.gmalija.core.infraestructure.google.Google
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.tasks.await

class LoginRepositoryImpl(private val firebase: Firebase, private val google: Google): LoginRepository {

    override suspend fun createAccountWithEmail(email: String, password: String): Result<LoginUser> {
        return try {
            firebase.createAccountWithEmail(email, password).await()
            Result.success(currentUser())
        } catch (cause: Throwable) {
            Result.errorException(cause)
        }
    }

    override suspend fun loginWithEmail(email: String, password: String): Result<LoginUser> {
        return try {
            firebase.loginWithEmail(email, password).await()
            Result.success(currentUser())
        } catch (cause: Throwable) {
            Result.errorException(cause)
        }
    }

    override suspend fun signInWithGoogleCredential(account: GoogleSignInAccount): Result<LoginUser> {
        return try {
            firebase.signInWithGoogleCredential(account).await()
            Result.success(currentUser())
        } catch (cause: Throwable) {
            Result.errorException(cause)
        }
    }

    override fun currentUser(): LoginUser? {

        val firebaseUser = firebase.currentUser()
        return if(firebaseUser != null) {
            LoginUser().apply {
                uid = firebaseUser.uid
                name = firebaseUser.displayName
                email = firebaseUser.email
            }
        } else {
            null
        }
//        return LoginUser(
//            firebaseUser?.uid,
//            firebaseUser?.displayName,
//            firebaseUser?.email
//        )
    }

    override fun signOut() {
        google.signOut()
        firebase.signOut()
    }

}