package com.gmalija.core.infraestructure.firebase

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class FirebaseImpl(private val firebaseAuth: FirebaseAuth): Firebase {

    override fun createAccountWithEmail(email: String, password: String): Task<AuthResult> {
        return firebaseAuth.createUserWithEmailAndPassword(email, password)
    }

    override fun loginWithEmail(email: String, password: String): Task<AuthResult> {
        return firebaseAuth.signInWithEmailAndPassword(email, password)
    }

    override fun signInWithGoogleCredential(account: GoogleSignInAccount): Task<AuthResult> {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        return firebaseAuth.signInWithCredential(credential)
    }

    override fun currentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }
    
}