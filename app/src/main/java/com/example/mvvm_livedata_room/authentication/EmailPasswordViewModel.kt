package com.example.mvvm_livedata_room.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EmailPasswordViewModel:ViewModel() {
    private val authRepository = AuthRepository()

    val user = authRepository.userLiveData

    val _email = MutableLiveData<String>()

    val _pw = MutableLiveData<String>()

    private val _emailerror = MutableLiveData<String>()
    val emailerror: LiveData<String>
        get() = _emailerror
    private val _pwerror = MutableLiveData<String>()
    val pwerror: LiveData<String>
        get() = _pwerror

    fun signIn(){
        if (!validateForm()) {
            return
        }
        authRepository.signIn(_email.value!!, _pw.value!!)
    }

    private fun validateForm(): Boolean {
        var valid = true

        if (_email.value.isNullOrEmpty()) {
            _emailerror.value= "Required."
            valid = false
        } else {
            _emailerror.value = ""
        }

        if (_pw.value.isNullOrEmpty()) {
            _pwerror.value = "Required."
            valid = false
        }else if (_pw.value!!.length < 6) {
            _pwerror.value = "Password must have at least 6 characters."
            valid = false
        }
        else {
            _pwerror.value = ""        }

        return valid
    }
}