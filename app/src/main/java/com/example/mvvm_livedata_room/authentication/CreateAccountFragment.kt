package com.example.mvvm_livedata_room.authentication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mvvm_livedata_room.R
import com.example.mvvm_livedata_room.databinding.FragmentCreateAccountBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CreateAccountFragment: Fragment() {
    private var _binding: FragmentCreateAccountBinding? = null
    private val binding: FragmentCreateAccountBinding
        get() = _binding!!

    val viewModel: CreateAccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_create_account, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        binding.createaccountBtn.setOnClickListener {
            viewModel.createAccount()
            viewModel.user.observe(viewLifecycleOwner, Observer {
                if (it != null){
                    Log.d("CreateAccount fragment", "  creating user with id ${it.uid}")
                this.findNavController()
                    .navigate(R.id.action_createAccountFragment_to_movieListFragment)
            }

            else{
                Log.d("CreateAccount fragment", "Error  creating user")
            }
            })
        }


        return binding.root
    }



}