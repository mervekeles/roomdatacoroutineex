package com.example.mvvm_livedata_room.authentication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.mvvm_livedata_room.R
import com.example.mvvm_livedata_room.databinding.FragmentEmailPwBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class EmailPasswordFragment: Fragment() {

    val viewModel: EmailPasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val  binding : FragmentEmailPwBinding
                = DataBindingUtil.inflate(
            inflater, R.layout.fragment_email_pw, container, false)


        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        binding.signInBtn.setOnClickListener {
            viewModel.signIn()
            viewModel.user.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    Log.d("EmailPW fragment", "  signing user with id ${it.uid}")
                    this.findNavController()
                        .navigate(R.id.action_emailPasswordFragment_to_movieListFragment)
                } else {
                    Log.d("Email PW fragment", "Error  signing user")
                }
            })
        }

        binding.createAccountBtn2.setOnClickListener {
            this.findNavController().navigate(R.id.action_emailPasswordFragment_to_createAccountFragment)
        }

        return binding.root
    }


}