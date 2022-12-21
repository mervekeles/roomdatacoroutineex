package com.example.mvvm_livedata_room.profile

import android.util.Log
import androidx.lifecycle.*
import com.example.mvvm_livedata_room.movielist.MovieListAdapter

import com.example.mvvm_livedata_room.network.Movie
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class ProfileViewModel:ViewModel(){

    private val _user = MutableLiveData<FirebaseUser>()
    val user: LiveData<FirebaseUser> = _user
    private val _favorites = MutableLiveData<List<Movie>>()
    val favorites: LiveData<List<Movie>> = _favorites
    init {
        viewModelScope.launch {
            _user.value = Firebase.auth.currentUser
            user.value?.let { getFavorites(it.uid) }
        }
    }

    suspend fun getFavorites(uid: String) :List<Movie>  {
         val favs = mutableListOf<Movie>()
            Firebase.firestore.collection("users").document(uid)
                .collection("favorites").get()
                .addOnSuccessListener { documentSnapshots ->
                    if (!documentSnapshots.isEmpty()) {
                        Log.d("ProfileViewModel", "DocumentSnapshot data: ${documentSnapshots.documents}")

                        for (snapshot in documentSnapshots) {
                            favs.add(snapshot.toObject<Movie>())
                        }
                        _favorites.value = favs
                        return@addOnSuccessListener

                    } else {
                        Log.d("ProfileViewModel", "No such documents")
return@addOnSuccessListener
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("ProfileViewModel", "get failed with ", exception)
                    return@addOnFailureListener
                }
        }

}