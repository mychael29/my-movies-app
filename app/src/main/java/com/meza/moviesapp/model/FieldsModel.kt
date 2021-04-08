package com.meza.moviesapp.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.meza.moviesapp.BR

data class FieldsModel(
    private val _username: String,
    private val _password: String,
) : BaseObservable() {

    @Bindable
    var password: String = _password
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }

    @Bindable
    var username: String = _username
        set(value) {
            field = value
            notifyPropertyChanged(BR.username)
        }
}