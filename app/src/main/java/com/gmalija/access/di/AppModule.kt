package com.gmalija.access.di

import com.gmalija.access.application.home.HomeViewModel
import com.gmalija.access.application.signIn.SignInViewModel
import com.gmalija.access.application.splash.SplashViewModel
import com.gmalija.core.domain.repository.LoginRepository
import com.gmalija.core.domain.repository.LoginRepositoryImpl
import com.gmalija.core.domain.useCase.HomeUseCase
import com.gmalija.core.domain.useCase.SignInUseCase
import com.gmalija.core.domain.useCase.SplashUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    factory<LoginRepository> { LoginRepositoryImpl(firebase = get(), google = get()) }
}

val useCaseModule = module {
    single { SignInUseCase(loginRepository = get()) }
    single { SplashUseCase(loginRepository = get()) }
    single { HomeUseCase(loginRepository = get()) }
}

val viewModelModule = module {
    viewModel { SignInViewModel(signInUseCase = get()) }
    viewModel { SplashViewModel(splashUseCase = get()) }
    viewModel { HomeViewModel(homeUseCase = get()) }
}