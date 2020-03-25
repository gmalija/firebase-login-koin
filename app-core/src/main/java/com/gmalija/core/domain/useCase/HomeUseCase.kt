package com.gmalija.core.domain.useCase

import com.gmalija.core.domain.repository.LoginRepository

class HomeUseCase(private val loginRepository: LoginRepository) {
    fun signOut() = loginRepository.signOut()
}