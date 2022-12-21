package com.example.mvvm_livedata_room.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.mvvm_livedata_room.R
import com.example.mvvm_livedata_room.databinding.FragmentMyfavoritesBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment: Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val  binding : FragmentMyfavoritesBinding
                = DataBindingUtil.inflate(
            inflater, R.layout.fragment_myfavorites, container, false)

        return binding.root
    }
}