package com.gmalija.access.application.home

import com.gmalija.access.application.base.BaseViewModel
import com.gmalija.core.domain.useCase.HomeUseCase

class HomeViewModel(private val homeUseCase: HomeUseCase) : BaseViewModel() {

    init {
        homeUseCase.signOut()
    }

}