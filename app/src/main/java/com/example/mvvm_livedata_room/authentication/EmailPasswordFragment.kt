package com.example.mvvm_livedata_room.authentication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mvvm_livedata_room.R
import com.example.mvvm_livedata_room.databinding.FragmentEmailPwBinding

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class EmailPasswordFragment: Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val  binding : FragmentEmailPwBinding
                = DataBindingUtil.inflate(
            inflater, R.layout.fragment_email_pw, container, false)

        binding.signInBtn.setOnClickListener {
            var email = binding.emailTextInput.editText?.text.toString()
            var pw = binding.pwTextInput.editText?.text.toString()
            signIn(email, pw)
        }

        binding.createAccountBtn2.setOnClickListener {
            this.findNavController().navigate(R.id.action_emailPasswordFragment_to_createAccountFragment)
        }
        // Initialize Firebase Auth
        auth = Firebase.auth

        return binding.root
    }


//    When initializing your Activity, check to see if the user is currently signed in.
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            //updateUI(currentUser)
        }
    }

    private fun signIn(email: String, password: String) {
        Log.d(TAG, "signIn:$email")
//        if (!validateForm()) {
//            return
//        }

        //showProgressBar()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    //updateUI(user)
                    this.findNavController().navigate(R.id.action_emailPasswordFragment_to_movieListFragment)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(context, "Authentication failed",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }

                if (!task.isSuccessful) {
                    //binding.status.setText(R.string.auth_failed)
                }
               // hideProgressBar()
            }
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}