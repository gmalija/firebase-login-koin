package com.gmalija.core.infraestructure.google

import android.content.Context
import com.firebase.ui.auth.AuthUI

class GoogleImpl(private val authUI: AuthUI, private val context: Context): Google {
    override fun signOut() {
        authUI.signOut(context)
    }
}