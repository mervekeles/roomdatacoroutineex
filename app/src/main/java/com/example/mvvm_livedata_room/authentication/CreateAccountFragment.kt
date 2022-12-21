package com.example.mvvm_livedata_room.authentication

import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mvvm_livedata_room.R
import com.example.mvvm_livedata_room.databinding.FragmentCreateAccountBinding
import com.example.mvvm_livedata_room.databinding.FragmentEmailPwBinding

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateAccountFragment: Fragment() {

    private lateinit var auth: FirebaseAuth

    private var _binding: FragmentCreateAccountBinding? = null
    private val binding: FragmentCreateAccountBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_create_account, container, false)

        binding.createaccountBtn.setOnClickListener {
            var name = binding.textinputName.editText?.text.toString()
            var email = binding.textinputEmail.editText?.text.toString()
            var pw = binding.textinputPw.editText?.text.toString()
            createAccount(email, pw, name)
        }

        // Initialize Firebase Auth
        auth = Firebase.auth

        return binding.root
    }




    private fun createAccount(email: String, password: String, name: String) {
        if (!validateForm()) {
            return
        }
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success")
                val user = auth.currentUser
               // updateUI(user)
                val profileUpdates = userProfileChangeRequest {
                    displayName = name
                }

                user!!.updateProfile(profileUpdates)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "User profile updated.")
                        }
                    }
                createUserInFB(email, name, user.uid)
                this.findNavController().navigate(R.id.action_createAccountFragment_to_movieListFragment)
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                Toast.makeText(
                    context, "Authentication failed.",
                    Toast.LENGTH_SHORT
                ).show()
               // updateUI(null)
            }
        }
    }
    private fun createUserInFB(email: String, name: String, uid: String){
        // Create a new user with a first and last name
        val user = hashMapOf(
            name to name,
            email to email
        )
        val db = Firebase.firestore
// Add a new document with a generated ID
        db.collection("users").document(uid).set(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${uid}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
    private fun validateForm(): Boolean {
        var valid = true
        val name = binding.textinputName.editText?.text.toString()
        if (TextUtils.isEmpty(name)) {
            binding.textinputName.error = "Required."
            valid = false
        } else {
            binding.textinputName.error = null
        }
        val email = binding.textinputEmail.editText?.text.toString()
        if (TextUtils.isEmpty(email)) {
            binding.textinputEmail.error = "Required."
            valid = false
        } else {
            binding.textinputEmail.error = null
        }

        val password = binding.textinputPw.editText?.text.toString()
        if (TextUtils.isEmpty(password)) {
            binding.textinputPw.error = "Required."
            valid = false
        }else if (password.length < 6) {
            binding.textinputPw.error = "Password must have at least 6 characters."
            valid = false
        }
        else {
            binding.textinputPw.error = null
        }

        return valid
    }

    companion object {
        private const val TAG = "Create Account"
    }
}