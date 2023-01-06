package com.example.mvvm_livedata_room.authentication

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateAccountViewModel: ViewModel() {
     private val authRepository = AuthRepository()

    val user = authRepository.userLiveData

    val _name = MutableLiveData<String>()
//    val name: LiveData<String>
//        get() = _name
     val _email = MutableLiveData<String>()
//    val email: LiveData<String>
//        get() = _email
     val _pw = MutableLiveData<String>()
//    val pw: LiveData<String>
//        get() = _pw

    private val _emailerror = MutableLiveData<String>()
    val emailerror: LiveData<String>
        get() = _emailerror
    private val _nameerror = MutableLiveData<String>()
    val nameerror: LiveData<String>
        get() = _nameerror
    private val _pwerror = MutableLiveData<String>()
    val pwerror: LiveData<String>
        get() = _pwerror

    fun createAccount(){
        if (!validateForm()) {
            return
        }
        authRepository.createAccount(_email.value!!, _pw.value!!, _name.value!!)
    }

    private fun validateForm(): Boolean {
        var valid = true

        if (_name.value.isNullOrEmpty()) {
            _nameerror.value = "Required."
            valid = false
        } else {
            //binding.textinputName.error = null
            _nameerror.value = ""
        }

        if (_email.value.isNullOrEmpty()) {
            _emailerror.value= "Required."
            valid = false
        } else {
            //binding.textinputEmail.error = null
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