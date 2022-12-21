package com.example.mvvm_livedata_room.authentication

import android.os.Parcelable
import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot


data class User(val userId: String, //Document ID is actually the user id
                val name: String,
                val bio: String,
                val imageUrl: String) {

    companion object {
        fun DocumentSnapshot.toUser(): User? {
            try {
                val name = getString("name")!!
                val imageUrl = getString("profile_image")!!
                val bio = getString("user_bio")!!
                return User(id, name, bio, imageUrl)
            } catch (e: Exception) {
                Log.e(TAG, "Error converting user profile", e)
                return null
            }
        }
        private const val TAG = "User"
    }
}