package com.gmalija.core.domain.entity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

//data class LoginUser(val uid: String?, val name: String?, val email: String?, val password: String? = "")

class LoginUser: BaseObservable() {

    @get:Bindable
    var uid: String? = ""

    var name: String? = ""
        @Bindable
        get
        set(name) {
            field = name
            notifyPropertyChanged(BR.name)
        }

    var email: String? = ""
        @Bindable
        get
        set(email) {
            field = email
            notifyPropertyChanged(BR.email)
        }

    var password: String? = ""
        @Bindable
        get
        set(password) {
            field = password
            notifyPropertyChanged(BR.password)
        }

}