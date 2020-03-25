package com.gmalija.core.di

import com.gmalija.core.infraestructure.firebase.Firebase
import com.gmalija.core.infraestructure.firebase.FirebaseImpl
import com.gmalija.core.infraestructure.google.Google
import com.gmalija.core.infraestructure.google.GoogleImpl
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val firebaseModule = module {
    single { FirebaseAuth.getInstance() }
    factory<Firebase> { FirebaseImpl(firebaseAuth = get()) }
}

val googleModule = module {
    single { AuthUI.getInstance() }
    factory<Google> { GoogleImpl(authUI = get(), context = get()) }
}