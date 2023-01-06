package com.example.mvvm_livedata_room

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mvvm_livedata_room.authentication.AuthRepository

class MainFragmentViewModel:ViewModel() {
    private val authRepository = AuthRepository()
    private val user = authRepository.userLiveData
    init{
        authRepository.getCurrentUser()
    }

    fun checkIfUserAuthenticated() :Boolean {
        if (user!=null) {
            Log.d("In main frag VM", "uid ${user.value?.displayName}")
            return true
        }
        else{
            return false
        }
    }
    fun logout(){
        authRepository.logout()
    }
}