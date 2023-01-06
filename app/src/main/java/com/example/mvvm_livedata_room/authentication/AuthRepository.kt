package com.example.mvvm_livedata_room.authentication

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.mvvm_livedata_room.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AuthRepository()  {
    private var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
    private var _userLiveData = MutableLiveData<FirebaseUser>()
    val userLiveData: LiveData<FirebaseUser>
        get() = _userLiveData



    fun createAccount(email: String, password: String, name: String) {

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success")
                val user = firebaseAuth.currentUser
                _userLiveData.value = firebaseAuth.currentUser

                val profileUpdates = userProfileChangeRequest {
                    displayName = name
                }

                user!!.updateProfile(profileUpdates)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "User profile updated.")
                            _userLiveData.value = firebaseAuth.currentUser
                        }
                    }
                createUserInFB(email, name, user.uid)
                //this.findNavController().navigate(R.id.action_createAccountFragment_to_movieListFragment)
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
            }
        }
    }

    fun signIn(email: String, password: String) {

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = firebaseAuth.currentUser
                    _userLiveData.value = firebaseAuth.currentUser

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)

                }

            }
    }

    private fun createUserInFB(email: String, name: String, uid: String){
        // Create a new user with a first and last name
        val user = hashMapOf(
            name to name,
            email to email
        )

// Add a new document with a generated ID
        db.collection("users").document(uid).set(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${uid}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }


    fun getCurrentUser(){
        _userLiveData.value = firebaseAuth.currentUser
    }
    fun logout(){
        firebaseAuth.signOut()
        //_userLiveData.value =  null
    }

    companion object {
        private const val TAG = "Create Account"
    }
}