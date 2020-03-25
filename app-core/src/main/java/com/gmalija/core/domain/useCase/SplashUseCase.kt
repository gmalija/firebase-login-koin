package com.gmalija.core.domain.useCase

import com.gmalija.core.domain.repository.LoginRepository

class SplashUseCase(private val loginRepository: LoginRepository) {
    fun currentUser() = loginRepository.currentUser()
}